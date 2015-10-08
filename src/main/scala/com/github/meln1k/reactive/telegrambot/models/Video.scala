package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a video file.
 * @param file_id Unique identifier for this file
 * @param width Video width as defined by sender
 * @param height Video height as defined by sender
 * @param duration Duration of the video in seconds as defined by sender
 * @param thumb Video thumbnail
 * @param mime_type Mime type of a file as defined by sender
 * @param file_size File size
 */
case class Video(file_id: String,
                 width: Int,
                 height: Int,
                 duration: Int,
                 thumb: Option[PhotoSize],
                 mime_type: Option[String],
                 file_size: Option[Int])
