package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.{ReplyMarkup, FileEntity}

/**
 * Use this method to send photos. On success, the sent Message is returned.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already
 *              on the Telegram servers, or upload a new photo using.
 * @param caption Photo caption (may also be used when resending photos by file_id).
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendPhoto(chat_id: Long,
                     photo: FileEntity,
                     caption: Option[String] = None,
                     reply_to_message_id: Option[Long] = None,
                     reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendPhoto"

  def methodParams: Map[String, Any] = Map(
    "photo" -> photo,
    "caption" -> caption
  )
}
