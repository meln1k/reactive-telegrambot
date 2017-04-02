package com.github.meln1k.reactive.telegrambot.api

import akka.actor.{ActorLogging, FSM, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.stream.actor.ActorPublisher
import com.github.meln1k.reactive.telegrambot.models._

import scala.annotation.tailrec
import ApiHelper._
import akka.pattern.pipe
import akka.stream.ActorMaterializer

private [api] class UpdateReceiver(token: String)
  extends FSM[UpdateReceiver.State, UpdateReceiver.Data]
  with ActorPublisher[Message]
  with ActorLogging {

  import akka.stream.actor.ActorPublisherMessage._
  import UpdateReceiver._
  import Data._
  import State._
  import TelegramBotJsonProtocol._

  implicit val system = context.system

  implicit val materializer = ActorMaterializer()

  import context.dispatcher

  val UpdateMethodName = "getUpdates"
  val BufferLength = 100


  def requestUpdates(lastProcessedUpdate: Long) = {
    val updateUri = apiUri(methodName = UpdateMethodName, token = token)

    def query = Query("timeout" -> "30", "offset" -> lastProcessedUpdate.toString)

    val request = HttpRequest(
      method = POST,
      uri = updateUri.withQuery(query)
    )

    Http()
      .singleRequest(request)
      .flatMap { response => Unmarshal(response.entity).to[Response] }
  }

  
  @tailrec
  final def deliverBuf(buf: Vector[Message]): Vector[Message] =
    if (totalDemand > 0) {
      /*
       * totalDemand is a Long and could be larger than
       * what buf.splitAt can accept
       */
      if (totalDemand <= Int.MaxValue) {
        val (use, keep) = buf.splitAt(totalDemand.toInt)
        use foreach onNext
        keep
      } else {
        val (use, keep) = buf.splitAt(Int.MaxValue)
        use foreach onNext
        deliverBuf(keep)
      }
    } else {
      buf
    }
  
  def updateData(buffer: Vector[Message], lastProcessedUpdate: Long): BufferedUpdates = {
    val remainingBuffer = deliverBuf(buffer)
    BufferedUpdates(remainingBuffer, lastProcessedUpdate)
  }

  startWith(Idle, BufferedUpdates(Vector.empty, 0))

  when(Idle) {
    case Event(Request(_), BufferedUpdates(buffer, lastUpdate)) =>
      
      val newData = updateData(buffer, lastUpdate)

      if (newData.buffer.length < BufferLength) {
        goto(State.MakingRequest) using newData
      } else {
        stay using newData
      }

    case Event(LoadUpdates, _) =>
      goto(MakingRequest)
  }

  when(MakingRequest) {
    case Event(Request(_), BufferedUpdates(buffer, lastUpdate)) =>

      val newData = updateData(buffer, lastUpdate)

      stay using newData

    case Event(SuccessfulResponseWithUpdates(result: Seq[Update]), BufferedUpdates(buffer, lastUpdate)) =>

      val lastUpdateNumber = result.lastOption.map(_.update_id + 1).getOrElse(lastUpdate)
      val newMessages = result.flatMap(_.message)
      val newData = updateData(buffer ++ newMessages, lastUpdateNumber)

      goto(Idle) using newData

    case Event(LoadUpdates, _) =>
      stay() //ignore

  }
  
  whenUnhandled {
    case Event(Cancel, _) =>
      context.stop(self)
      stay()

    case Event(value, stateData) ⇒
      log.warning("unhandled event " + value + " in state " + stateName)
      stay()
  }
  
  onTransition {
    case Idle -> MakingRequest =>
      nextStateData match {
        case BufferedUpdates(buf, lastUpdate) =>
          requestUpdates(lastUpdate) pipeTo self
      }

    case MakingRequest -> Idle =>
      nextStateData match {
        case BufferedUpdates(buf, lastUpdate) =>
          if (buf.length < BufferLength) {
            self ! LoadUpdates
          }
      }
  }
}

object UpdateReceiver {

  def props(token: String) = Props(classOf[UpdateReceiver], token)

  trait State

  object State {

    case object Idle extends State

    case object MakingRequest extends State

  }

  trait Data

  object Data {

    case class BufferedUpdates(buffer: Vector[Message], lastProcessedUpdate: Long) extends Data

  }

  case object LoadUpdates

}
