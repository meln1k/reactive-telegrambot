package com.github.meln1k.reactive.telegrambot.methods

/**
 * Use this method to forward messages of any kind.
 * On success, the sent Message is returned.
 * @param chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param from_chat_id Unique identifier for the message recipient — User or GroupChat id
 * @param message_id Unique identifier for the message recipient — User or GroupChat id
 */
case class ForwardMessage(chat_id: Long,
                          from_chat_id: Long,
                          message_id: Long) extends ApiMethod with HasChatId {

  override def name: String = "forwardMessage"

  override def methodParams: Map[String, Any] = Map(
    "from_chat_id" -> from_chat_id,
    "message_id" -> message_id
  )
}
