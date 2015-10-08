package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a file ready to be downloaded.
 * The file can be downloaded via the link https://api.telegram.org/file/bot<token>/<file_path>.
 * It is guaranteed that the link will be valid for at least 1 hour.
 * When the link expires, a new one can be requested by calling getFile.
 * @param file_id Unique identifier for this file
 * @param file_size File size, if known
 * @param file_path File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.
 */
case class TelegramFile(file_id: String,
                file_size: Option[Int],
                file_path: Option[String]) extends ResponseEntity
