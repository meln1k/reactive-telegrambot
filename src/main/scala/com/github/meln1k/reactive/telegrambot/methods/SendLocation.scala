package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.ReplyMarkup

/**
 * Use this method to send point on the map. On success, the sent Message is returned.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param latitude Latitude of location
 * @param longitude Longitude of location
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendLocation(chat_id: Long,
                        latitude: Double,
                        longitude: Double,
                        reply_to_message_id: Option[Long] = None,
                        reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendLocation"

  override protected def methodParams: Map[String, Any] = Map(
    "latitude" -> latitude,
    "longitude" -> longitude
  )
}
