package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a voice note.
 * @param file_id Unique identifier for this file
 * @param duration Duration of the audio in seconds as defined by sender
 * @param mime_type MIME type of the file as defined by sender
 * @param file_size File size
 */
case class Voice(file_id: String,
                 duration: Int,
                 mime_type: Option[String],
                 file_size: Option[Int])