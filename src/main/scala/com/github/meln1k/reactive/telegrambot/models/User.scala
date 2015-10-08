package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a Telegram user or bot.
 * @param id Unique identifier for this user or bot
 * @param first_name User‘s or bot’s first name
 * @param last_name User‘s or bot’s last name
 * @param username User‘s or bot’s username
 */
case class User(id: Long,
                first_name: String,
                last_name: Option[String],
                username: Option[String]) extends WithId
