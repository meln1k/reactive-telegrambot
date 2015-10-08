package com.github.meln1k.reactive.telegrambot.models

case class UserProfilePhotos(total_count: Int, photos: Seq[Seq[PhotoSize]]) extends ResponseEntity
