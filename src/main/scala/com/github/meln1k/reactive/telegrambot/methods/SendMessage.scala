package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.ReplyMarkup

/**
 * Use this method to send text messages. On success,
 * the sent Message is returned.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param text Text of the message to be sent
 * @param parse_mode Send Markdown, if you want Telegram apps to show bold, italic and
 *                   inline URLs in your bot's message.
 * @param disable_web_page_preview Disables link previews for links in this message
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendMessage(chat_id: Long,
                       text: String,
                       parse_mode: Option[String] = None,
                       disable_web_page_preview: Option[Boolean] = None,
                       reply_to_message_id: Option[Long] = None,
                       reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendMessage"

  override def methodParams: Map[String, Any] = Map(
    "text" -> text,
    "parse_mode" -> parse_mode,
    "disable_web_page_preview" -> disable_web_page_preview
  )
}