package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.{ReplyMarkup, FileEntity}

/**
 * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as Document).
 * On success, the sent Message is returned. Bots can currently send video files of up to 50 MB in size,
 * this limit may be changed in the future.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param video Video to send. You can either pass a file_id as String to resend a video that is
 *              already on the Telegram servers, or upload a new video file.
 * @param duration Duration of sent video in seconds
 * @param caption Video caption (may also be used when resending videos by file_id).
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendVideo(chat_id: Long,
                     video: FileEntity,
                     duration: Option[Int] = None,
                     caption: Option[String] = None,
                     reply_to_message_id: Option[Long] = None,
                     reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard {

  override def name: String = "sendVideo"

  override protected def methodParams: Map[String, Any] = Map(
    "video" -> video,
    "duration" -> duration,
    "caption" -> caption
  )
}
