package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents an audio file to be treated as music by the Telegram clients.
 * @param file_id Unique identifier for this file
 * @param duration Duration of the audio in seconds as defined by sender
 * @param performer Performer of the audio as defined by sender or by audio tags
 * @param title Title of the audio as defined by sender or by audio tags
 * @param mime_type MIME type of the file as defined by sender
 * @param file_size File size
 */
case class Audio(file_id: String,
                 duration: String,
                 performer: Option[String],
                 title: Option[String],
                 mime_type: Option[String],
                 file_size: Option[Int])
