package com.github.meln1k.reactive.telegrambot.api

import akka.http.scaladsl.model.Uri

private [api] object ApiHelper {

  def apiUri(methodName: String, token: String) = Uri(s"https://api.telegram.org/bot$token/$methodName")

}
