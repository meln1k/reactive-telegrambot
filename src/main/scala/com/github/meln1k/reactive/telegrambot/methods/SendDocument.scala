package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.{ReplyMarkup, FileEntity}

/**
 * Use this method to send general files. On success, the sent Message is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param document File to send. You can either pass a file_id as String to resend a file that is already
 *                 on the Telegram servers, or upload a new file.
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendDocument(chat_id: Long,
                        document: FileEntity,
                        reply_to_message_id: Option[Long] = None,
                        reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendDocument"

  override def methodParams: Map[String, Any] = Map(
    "document" -> document
  )
}