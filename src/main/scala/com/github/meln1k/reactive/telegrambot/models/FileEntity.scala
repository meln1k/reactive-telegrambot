package com.github.meln1k.reactive.telegrambot.models

import java.io.File

import akka.http.scaladsl.model.ContentType
import akka.stream.scaladsl.Source
import akka.util.ByteString

/**
 * Trait representing a file entity, which can be send in a message.
 */
sealed trait FileEntity

case class FileId(id: String) extends FileEntity

/**
 * This object represents the contents of a file to be uploaded.
 * Must be posted using multipart/form-data in the usual way that files are uploaded via the browser.
 * @param file File to be uploaded
 */
case class InputFile(file: File) extends FileEntity

/**
 * This object represents the contents of a file to be uploaded.
 * Must be posted using multipart/form-data in the usual way that files are uploaded via the browser.
 * Useful when file represented as source of ByteStrings.
 * @param fileName name of the file. Should contain extension
 * @param contentType content-type of the file
 * @param contentLength length in bytes
 * @param dataBytes source of ByteString
 */
case class StreamedInputFile(fileName: String,
                             contentType: ContentType,
                             contentLength: Long,
                             dataBytes: Source[ByteString, Any]) extends FileEntity
