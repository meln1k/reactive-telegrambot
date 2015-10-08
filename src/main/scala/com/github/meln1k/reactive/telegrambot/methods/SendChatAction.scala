package com.github.meln1k.reactive.telegrambot.methods

/**
 * Use this method when you need to tell the user that something is happening on the bot's side.
 * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param action Type of action to broadcast.
 *               Choose one, depending on what the user is about to receive:
 *               typing for text messages, upload_photo for photos,
 *               record_video or upload_video for videos,
 *               record_audio or upload_audio for audio files,
 *               upload_document for general files,
 *               find_location for location data.
 */
case class SendChatAction(chat_id: Long, action: String) extends ApiMethod with HasChatId {
  override def name: String = "sendChatAction"

  override protected def methodParams: Map[String, Any] = Map("action" -> action)
}