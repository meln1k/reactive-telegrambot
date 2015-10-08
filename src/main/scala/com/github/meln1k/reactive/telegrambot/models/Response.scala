package com.github.meln1k.reactive.telegrambot.models

/**
 * An API response.
 */
sealed trait Response {
  def ok: Boolean
}

/**
 * This object represents a successful API response.
 * @param result Requested object
 */
case class SuccessfulResponse(result: ResponseEntity) extends Response {
  def ok = true
}


case class SuccessfulResponseWithUpdates(result: Seq[Update]) extends Response {
  def ok = true
}

/**
 * This object represents a failed API response.
 * @param description Error explanation
 */
case class FailedResponse(description: String) extends Response {
  def ok = false
}