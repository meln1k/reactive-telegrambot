package com.github.meln1k.reactive.telegrambot.api

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import akka.stream.scaladsl.{Keep, Sink, Source, Flow}
import com.github.meln1k.reactive.telegrambot.models._
import TelegramBotJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json._
import ApiHelper._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class TelegramRequestConsumer(private val token: String)(implicit actorSystem: ActorSystem, materializer: Materializer) {

  import actorSystem.dispatcher

  private val availableProcessors = Runtime.getRuntime.availableProcessors()

  private def createEntity(params: Map[String, Any]): Future[RequestEntity] = {
    def stringBodyPart(name: String, value: Any) = Multipart.FormData.BodyPart(
      name,
      HttpEntity(value.toString)
    )
    val formData =
      Multipart.FormData(
        params.toSeq.map { case (name, value) =>
          value match {
            case l: Long => stringBodyPart(name, l)
            case s: String => stringBodyPart(name, s)
            case b: Boolean => stringBodyPart(name, b)
            case d: Double => stringBodyPart(name, d)
            case rm: ReplyMarkup => stringBodyPart(name, rm.toJson.compactPrint)
            case InputFile(file) =>
              Multipart.FormData.BodyPart.fromFile(
                name,
                MediaTypes.`application/octet-stream`,
                file,
                100000
              )
            case StreamedInputFile(fileName, contentType, length, dataBytes) =>
              Multipart.FormData.BodyPart(
                name,
                HttpEntity(
                  contentType,
                  length,
                  dataBytes
                ),
                Map("filename" -> fileName)
              )
          }
        }: _*
      )
    Marshal(formData).to[RequestEntity]
  }

  private val apiRequestToHttpRequest: Flow[ApiRequest, (HttpRequest, UUID), Unit] =
    Flow[ApiRequest].mapAsyncUnordered(4) { case ApiRequest(method, id) =>
      val filteredParams = method.allParams.filterNot(_._2 == None).map {
        case (name, Some(value)) => name -> value
        case e@(name, value) => e
      }
      val entity = createEntity(filteredParams)
      val Uri = apiUri(method.name, token)
      entity.map { e =>
        HttpRequest(method = POST, uri = Uri, entity = e) -> id
      }
    }

  private val http = Http().superPool[UUID]()


  private val httpResponseToApiResponse: Flow[(Try[HttpResponse], UUID), ApiResponse, Unit] =
    Flow[(Try[HttpResponse], UUID)]
      .mapAsync(availableProcessors) { case (tryResponse, id) =>
      tryResponse.map { response =>
        Unmarshal(response.entity).to[Response].map { resp =>
          ApiResponse(id, Success(resp))
        }
      }.recover{
        case e => Future.successful(ApiResponse(id, Failure(e)))
      }.get
    }

  private val apiFlow: Flow[ApiRequest, ApiResponse, Unit] =
    Flow[ApiRequest]
      .via(apiRequestToHttpRequest)
      .via(http)
      .via(httpResponseToApiResponse)

  def singleRequest(request: ApiRequest): Future[ApiResponse] =
    Source.single(request)
      .via(apiFlow)
      .toMat(Sink.head)(Keep.right)
      .run()

  def flow: Flow[ApiRequest, ApiResponse, Unit] = apiFlow

  def processor = apiFlow.toProcessor.run()

}

object TelegramRequestConsumer {
  def apply(token: String)(implicit actorSystem: ActorSystem, materializer: Materializer) = new TelegramRequestConsumer(token)
}