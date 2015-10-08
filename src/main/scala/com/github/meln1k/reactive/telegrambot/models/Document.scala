package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a general file (as opposed to photos, voice messages and audio files).
 * @param file_id Unique file identifier
 * @param thumb Document thumbnail as defined by sender
 * @param file_name Original filename as defined by sender
 * @param mime_type MIME type of the file as defined by sender
 * @param file_size File size
 */
case class Document(file_id: String,
                    thumb: Option[PhotoSize],
                    file_name: Option[String],
                    mime_type: Option[String],
                    file_size: Option[Int])
