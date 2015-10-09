# Reactive Telegram Bot API

[Reactive Streams](http://www.reactive-streams.org) wrapper
for [Telegram Bots API](https://core.telegram.org/bots) written in Scala with Akka Streams.


Dependencies
------------
To include the latest release of the Telegram API wrapper into your sbt project,
add the following lines to your build.sbt file:

```Scala
resolvers += "nmelkozerov at bintray" at "http://dl.bintray.com/nmelkozerov/maven"

libraryDependencies += "com.github.meln1k" %% "reactive-telegrambot" % "1.0"
```
This version of `reactive-telegrambot` depends on Scala 2.11.6.

Example usage
-------------

```Scala

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import com.github.meln1k.reactive.telegrambot.api.{TelegramRequestConsumer, ApiRequest, TelegramUpdatePublisher}
import com.github.meln1k.reactive.telegrambot.methods.SendMessage
import com.github.meln1k.reactive.telegrambot.models.TextMessage

object SimpleBot extends App {
  implicit val system = ActorSystem("reactive-telegrambot")
  implicit val materializer = ActorMaterializer()

  val token = "bot_token"

  val source = TelegramUpdatePublisher(token).source

  val flow = TelegramRequestConsumer(token).flow

  source
    .collect { case tm: TextMessage => ApiRequest(SendMessage(chat_id = tm.chat.id, text = tm.text.toUpperCase)) }
    .via(flow)
    .to(Sink.ignore)
    .run

}
```

Features
--------
* All API [methods](https://core.telegram.org/bots/api#available-methods) and available
  [types](https://core.telegram.org/bots/api#available-types) are fully supported.
* Compatible with reactive-streams API.

Caveats
-------
* Webhook isn't supported yet, but it's likely to be resolved in future versions of this library.
