package com.github.meln1k.reactive.telegrambot.models

sealed trait ReplyMarkup

/**
 * This object represents a custom keyboard with reply options.
 * @param keyboard Array of button rows, each represented by an Array of Strings
 * @param resize_keyboard Requests clients to resize the keyboard vertically for optimal fit
 *                        (e.g., make the keyboard smaller if there are just two rows of buttons).
 *                        Defaults to false, in which case the custom keyboard is always of the same height as
 *                        the app's standard keyboard.
 * @param one_time_keyboard Requests clients to hide the keyboard as soon as it's been used. Defaults to false.
 * @param selective Use this parameter if you want to show the keyboard to specific users only.
 *                  Targets: 1) users that are @mentioned in the text of the Message object;
 *                  2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 *
 *                  Example: A user requests to change the bot‘s language,
 *                  bot replies to the request with a keyboard to select the new language.
 *                  Other users in the group don’t see the keyboard.
 */
case class ReplyKeyboardMarkup(keyboard: Seq[Seq[String]],
                               resize_keyboard: Boolean = false,
                               one_time_keyboard: Boolean = false,
                               selective: Boolean = false) extends ReplyMarkup

/**
 * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard
 * and display the default letter-keyboard.
 * By default, custom keyboards are displayed until a new keyboard is sent by a bot.
 * An exception is made for one-time keyboards that are hidden immediately after
 * the user presses a button (see ReplyKeyboardMarkup).
 * @param selective Use this parameter if you want to hide keyboard for specific users only.
 *                  Targets:
 *                  1) users that are @mentioned in the text of the Message object;
 *                  2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 *
 *                  Example: A user votes in a poll, bot returns confirmation message in reply to the vote and
 *                  hides keyboard for that user, while still showing the keyboard with poll options
 *                  to users who haven't voted yet.
 */
case class ReplyKeyboardHide(selective: Boolean = false, hide_keyboard: Boolean = true) extends ReplyMarkup

/**
 * Upon receiving a message with this object, Telegram clients will display a reply interface to the user
 * (act as if the user has selected the bot‘s message and tapped ’Reply').
 * This can be extremely useful if you want to create user-friendly step-by-step interfaces
 * without having to sacrifice privacy mode.
 * @param selective Use this parameter if you want to force reply from specific users only.
 *                  Targets:
 *                  1) users that are @mentioned in the text of the Message object;
 *                  2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 */
case class ForceReply(selective: Boolean = false, force_reply: Boolean = true) extends ReplyMarkup