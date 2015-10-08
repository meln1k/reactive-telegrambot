package com.github.meln1k.reactive.telegrambot.models

trait ResponseEntity

/**
 * This object represents an incoming update.
 * @param update_id The update‘s unique identifier.
 *                  Update identifiers start from a certain positive number and increase sequentially.
 *                  This ID becomes especially handy if you’re using Webhooks,
 *                  since it allows you to ignore repeated updates or to restore the correct update sequence,
 *                  should they get out of order.
 * @param message New incoming message of any kind — text, photo, sticker, etc.
 */
case class Update(update_id: Long, message: Option[Message]) extends ResponseEntity








