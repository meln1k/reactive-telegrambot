package com.github.meln1k.reactive.telegrambot.methods

/**
 * A simple method for testing your bot's auth token.
 * Requires no parameters. Returns basic information about the bot in form of a User object.
 */
case object GetMe extends ApiMethod {
  override def name: String = "getMe"

  override def methodParams: Map[String, Any] = Map.empty
}
