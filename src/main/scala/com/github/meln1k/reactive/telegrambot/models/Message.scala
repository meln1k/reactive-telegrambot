package com.github.meln1k.reactive.telegrambot.models

/**
 * This object represents a message.
 */
sealed trait Message extends ResponseEntity {
  def message_id: Long

  def from: User

  def date: Long

  def chat: WithId

  def forward_from: Option[User]

  def forward_date: Option[Long]

  def reply_to_message: Option[Message]
}

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param text The actual UTF-8 text of the message
 */
case class TextMessage(message_id: Long,
                       from: User,
                       date: Long,
                       chat: WithId,
                       forward_from: Option[User],
                       forward_date: Option[Long],
                       reply_to_message: Option[Message],
                       text: String) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param audio Information about the file
 */
case class AudioMessage(message_id: Long,
                        from: User,
                        date: Long,
                        chat: WithId,
                        forward_from: Option[User],
                        forward_date: Option[Long],
                        reply_to_message: Option[Message],
                        audio: Audio) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param document Information about the file
 */
case class DocumentMessage(message_id: Long,
                           from: User,
                           date: Long,
                           chat: WithId,
                           forward_from: Option[User],
                           forward_date: Option[Long],
                           reply_to_message: Option[Message],
                           document: Document) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param caption Caption for the video
 * @param photo Available sizes of the photo
 */
case class PhotoMessage(message_id: Long,
                        from: User,
                        date: Long,
                        chat: WithId,
                        forward_from: Option[User],
                        forward_date: Option[Long],
                        reply_to_message: Option[Message],
                        caption: Option[String],
                        photo: Seq[PhotoSize]) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param sticker Information about the sticker
 */
case class StickerMessage(message_id: Long,
                          from: User,
                          date: Long,
                          chat: WithId,
                          forward_from: Option[User],
                          forward_date: Option[Long],
                          reply_to_message: Option[Message],
                          sticker: Sticker) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param caption Caption for the video
 * @param video Information about the video
 */
case class VideoMessage(message_id: Long,
                        from: User,
                        date: Long,
                        chat: WithId,
                        forward_from: Option[User],
                        forward_date: Option[Long],
                        reply_to_message: Option[Message],
                        caption: Option[String],
                        video: Video) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param voice Information about the file
 */
case class VoiceMessage(message_id: Long,
                        from: User,
                        date: Long,
                        chat: WithId,
                        forward_from: Option[User],
                        forward_date: Option[Long],
                        reply_to_message: Option[Message],
                        voice: Voice) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param contact Information about the contact
 */
case class ContactMessage(message_id: Long,
                          from: User,
                          date: Long,
                          chat: WithId,
                          forward_from: Option[User],
                          forward_date: Option[Long],
                          reply_to_message: Option[Message],
                          contact: Contact) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param location Information about the location
 */
case class LocationMessage(message_id: Long,
                           from: User,
                           date: Long,
                           chat: WithId,
                           forward_from: Option[User],
                           forward_date: Option[Long],
                           reply_to_message: Option[Message],
                           location: Location) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param new_chat_participant A new member was added to the group, information about them (this member may be bot itself)
 */
case class MemberAddedToGroup(message_id: Long,
                              from: User,
                              date: Long,
                              chat: WithId,
                              forward_from: Option[User],
                              forward_date: Option[Long],
                              reply_to_message: Option[Message],
                              new_chat_participant: User) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param left_chat_participant A member was removed from the group, information about them (this member may be bot itself)
 */
case class MemberRemovedFromGroup(message_id: Long,
                                  from: User,
                                  date: Long,
                                  chat: WithId,
                                  forward_from: Option[User],
                                  forward_date: Option[Long],
                                  reply_to_message: Option[Message],
                                  left_chat_participant: User) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param new_chat_title A group title was changed to this value
 */
case class GroupTitleChanged(message_id: Long,
                             from: User,
                             date: Long,
                             chat: WithId,
                             forward_from: Option[User],
                             forward_date: Option[Long],
                             reply_to_message: Option[Message],
                             new_chat_title: String) extends Message

/**
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @param new_chat_photo A group photo was change to this value
 */
case class GroupPhotoChanged(message_id: Long,
                             from: User,
                             date: Long,
                             chat: WithId,
                             forward_from: Option[User],
                             forward_date: Option[Long],
                             reply_to_message: Option[Message],
                             new_chat_photo: Seq[PhotoSize]) extends Message

/**
 * Informs that the group photo was deleted
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 */
case class GroupPhotoDeleted(message_id: Long,
                             from: User,
                             date: Long,
                             chat: WithId,
                             forward_from: Option[User],
                             forward_date: Option[Long],
                             reply_to_message: Option[Message]) extends Message

/**
 * Informs that the group has been created
 * @param message_id Unique message identifier
 * @param from Sender
 * @param date Date the message was sent in Unix time
 * @param chat Conversation the message belongs to — user in case of a private message, GroupChat in case of a group
 * @param forward_from For forwarded messages, sender of the original message
 * @param forward_date For forwarded messages, date the original message was sent in Unix time
 * @param reply_to_message For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 */
case class GroupChatCreated(message_id: Long,
                            from: User,
                            date: Long,
                            chat: WithId,
                            forward_from: Option[User],
                            forward_date: Option[Long],
                            reply_to_message: Option[Message]) extends Message