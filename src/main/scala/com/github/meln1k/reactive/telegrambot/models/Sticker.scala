package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a sticker.
 * @param file_id Unique identifier for this file
 * @param width Sticker width
 * @param height Sticker height
 * @param thumb Sticker thumbnail in .webp or .jpg format
 * @param file_size File size
 */
case class Sticker(file_id: String,
                   width: Int,
                   height: Int,
                   thumb: Option[PhotoSize],
                   file_size: Option[Int])
