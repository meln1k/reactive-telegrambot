package com.github.meln1k.reactive.telegrambot.api

import akka.actor.{ActorSystem, ActorRef}
import akka.stream.Materializer
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl.Source
import com.github.meln1k.reactive.telegrambot.models.Message
import org.reactivestreams.Publisher


class TelegramUpdatePublisher(private val token: String)(implicit actorSystem: ActorSystem, materializer: Materializer) {

  def publisher: Publisher[Message] =
    ActorPublisher[Message](actorSystem.actorOf(UpdateReceiver.props(token)))

  def source: Source[Message, ActorRef] = Source.actorPublisher[Message](UpdateReceiver.props(token))

}

object TelegramUpdatePublisher {
  def apply(token: String)(implicit actorSystem: ActorSystem, materializer: Materializer) = new TelegramUpdatePublisher(token)
}

