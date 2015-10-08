package com.github.meln1k.reactive.telegrambot.methods

import com.github.meln1k.reactive.telegrambot.models.ReplyMarkup


trait Params {
  def allParams: Map[String, Any]
}

abstract class ApiMethod extends Params {
  def name: String

  protected def methodParams: Map[String, Any]

  override def allParams: Map[String, Any] = methodParams
}

trait HasChatId extends Params {

  def chat_id: Long

  abstract override def allParams: Map[String, Any] = super.allParams + ("chat_id" -> chat_id)

}

trait HasReplyTo extends Params {

  def reply_to_message_id: Option[Long]

  abstract override def allParams: Map[String, Any] = super.allParams + ("reply_to_message_id" -> reply_to_message_id)
}

trait HasCustomKeyboard extends Params {

  def reply_markup: Option[ReplyMarkup]

  abstract override def allParams: Map[String, Any] = super.allParams + ("reply_markup" -> reply_markup)
}
