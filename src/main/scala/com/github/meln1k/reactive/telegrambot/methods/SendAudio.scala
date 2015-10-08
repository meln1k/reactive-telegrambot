package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models._

/**
 * Use this method to send audio files, if you want Telegram clients to display them in the music player.
 * Your audio must be in the .mp3 format. On success, the sent Message is returned.
 * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
 *
 * For backward compatibility, when the fields title and performer are both empty and the mime-type of the file
 * to be sent is not audio/mpeg, the file will be sent as a playable voice message.
 * For this to work, the audio must be in an .ogg file encoded with OPUS.
 * This behavior will be phased out in the future. For sending voice messages, use the sendVoice method instead.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already
 *              on the Telegram servers, or upload a new audio file.
 * @param duration Duration of the audio in seconds
 * @param performer Performer
 * @param title Track name
 * @param reply_to_message_id If the message is a reply, ID of the original message
 * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard,
 *                     instructions to hide keyboard or to force a reply from the user.
 */
case class SendAudio(chat_id: Long,
                audio: FileEntity,
                duration: Option[Int] = None,
                performer: Option[String] = None,
                title: Option[String] = None,
                reply_to_message_id: Option[Long] = None,
                reply_markup: Option[ReplyMarkup] = None)
  extends ApiMethod
  with HasChatId
  with HasReplyTo
  with HasCustomKeyboard{

  override def name: String = "sendAudio"

  override def methodParams: Map[String, Any] = Map(
    "audio" -> audio,
    "duration" -> duration,
    "performer" -> performer,
    "title" -> title
  )
}
