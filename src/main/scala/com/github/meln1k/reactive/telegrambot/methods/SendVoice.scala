package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.{ReplyMarkup, FileEntity}

/**
 * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message.
 * For this to work, your audio must be in an .ogg file encoded with OPUS
 * (other formats may be sent as Audio or Document). On success, the sent Message is returned.
 * Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param voice Audio file to send. You can either pass a file_id as String to resend an audio that is
 *              already on the Telegram servers, or upload a new audio file.
 * @param duration Duration of sent audio in seconds
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. An object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendVoice(chat_id: Long,
                     voice: FileEntity,
                     duration: Option[Int] = None,
                     reply_to_message_id: Option[Long] = None,
                     reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendVoice"

  override protected def methodParams: Map[String, Any] = Map(
    "voice" -> voice,
    "duration" -> duration
  )
}