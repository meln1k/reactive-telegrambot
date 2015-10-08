package com.github.meln1k.reactive.telegrambot.models

import spray.json._
import spray.json.DefaultJsonProtocol


private [telegrambot] object TelegramBotJsonProtocol extends DefaultJsonProtocol {

  implicit object ResponseFormat extends RootJsonFormat[Response] {
    def write(r: Response) = throw new UnsupportedOperationException

    def read(value: JsValue) = value.asJsObject.getFields("ok") match {
      case Seq(JsBoolean(ok)) if ok =>
        value.asJsObject.getFields("result") match {
          case Seq(JsArray(results)) => SuccessfulResponseWithUpdates(results.map(_.convertTo[Update]))
          case Seq(singleEntity) => SuccessfulResponse(singleEntity.convertTo[ResponseEntity])
        }
      case Seq(JsBoolean(ok)) if !ok => value.asJsObject.convertTo[FailedResponse]
      case _ => throw new DeserializationException("Response expected")
    }
  }

  implicit object ResponseEntityFormat extends RootJsonFormat[ResponseEntity] {
    def write(r: ResponseEntity) = throw new UnsupportedOperationException

    def read(value: JsValue) = value.asJsObject.fields.keys.collectFirst {
      case "update_id" => value.convertTo[Update]
      case "message_id" => value.convertTo[Message]
    }.getOrElse(throw new DeserializationException("ResponseEntity expected"))
  }

  implicit object MessageFormat extends RootJsonFormat[Message] {
    def write(m: Message) = throw new UnsupportedOperationException

    def read(value: JsValue) = value.asJsObject.fields.keys.collectFirst {
      case "text" => value.convertTo[TextMessage]
      case "audio" => value.convertTo[AudioMessage]
      case "document" => value.convertTo[DocumentMessage]
      case "photo" => value.convertTo[PhotoMessage]
      case "sticker" => value.convertTo[StickerMessage]
      case "video" => value.convertTo[VideoMessage]
      case "voice" => value.convertTo[VoiceMessage]
      case "contact" => value.convertTo[ContactMessage]
      case "location" => value.convertTo[LocationMessage]
      case "new_chat_participant" => value.convertTo[MemberAddedToGroup]
      case "left_chat_participant" => value.convertTo[MemberRemovedFromGroup]
      case "new_chat_title" => value.convertTo[GroupTitleChanged]
      case "new_chat_photo" => value.convertTo[GroupPhotoChanged]
      case "delete_chat_photo" => value.convertTo[GroupPhotoDeleted]
      case "group_chat_created" => value.convertTo[GroupChatCreated]
    }.getOrElse(throw new DeserializationException(s"Message expected, but got $value"))
  }

  implicit object ChatFormat extends RootJsonFormat[WithId] {
    def write(c: WithId) = throw new UnsupportedOperationException

    def read(value: JsValue) = value.asJsObject.fields.keys.collectFirst {
      case "first_name" => value.convertTo[User]
      case "title" => value.convertTo[GroupChat]
    }.getOrElse(throw new DeserializationException("User or GroupChat expected"))
  }

  implicit object ReplyMarkupFormat extends RootJsonFormat[ReplyMarkup] {
    def write(replyMarkup: ReplyMarkup) = replyMarkup match {
      case rkm: ReplyKeyboardMarkup => rkm.toJson
      case rkh: ReplyKeyboardHide => rkh.toJson
      case fr: ForceReply => fr.toJson
    }

    def read(value: JsValue) = throw new UnsupportedOperationException
  }

  //root objects

  implicit val failedResponseFormat = jsonFormat1(FailedResponse)

  implicit val updateFormat = jsonFormat2(Update)

  //entities

  implicit val groupChatFormat = jsonFormat2(GroupChat)

  implicit val userFormat = jsonFormat4(User)

  implicit val photoSizeFormat = jsonFormat4(PhotoSize)

  implicit val documentFormat = jsonFormat5(Document)

  implicit val videoFormat = jsonFormat7(Video)

  implicit val audioFormat = jsonFormat6(Audio)

  implicit val stickerFormat = jsonFormat5(Sticker)

  implicit val voiceFormat = jsonFormat4(Voice)

  implicit val contactFormat = jsonFormat4(Contact)

  implicit val locationFormat = jsonFormat2(Location)

  implicit val rkmFormat = jsonFormat4(ReplyKeyboardMarkup)

  implicit val rkhFormat = jsonFormat2(ReplyKeyboardHide)

  implicit val frFormat = jsonFormat2(ForceReply)

  //messages formats

  implicit val textMessageFormat = jsonFormat8(TextMessage)

  implicit val audioMessageFormat = jsonFormat8(AudioMessage)

  implicit val documentMessageFormat = jsonFormat8(DocumentMessage)

  implicit val photoMessageFormat = jsonFormat9(PhotoMessage)

  implicit val stickerMessageFormat = jsonFormat8(StickerMessage)

  implicit val videoMessageFormat = jsonFormat9(VideoMessage)

  implicit val voiceMessageFormat = jsonFormat8(VoiceMessage)

  implicit val contactMessageFormat = jsonFormat8(ContactMessage)

  implicit val locationMessageFormat = jsonFormat8(LocationMessage)

  implicit val memberAddedFormat = jsonFormat8(MemberAddedToGroup)

  implicit val memberRemovedFormat = jsonFormat8(MemberRemovedFromGroup)

  implicit val groupTitleChangedFormat = jsonFormat8(GroupTitleChanged)

  implicit val groupPhotoChangedFormat = jsonFormat8(GroupPhotoChanged)

  implicit val groupPhotoDeletedFormat = jsonFormat7(GroupPhotoDeleted)

  implicit val groupChatCreatedFormat = jsonFormat7(GroupChatCreated)
}
