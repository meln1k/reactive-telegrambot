package com.github.meln1k.reactive.telegrambot.api

import java.util.UUID
import com.github.meln1k.reactive.telegrambot.models.Response

import scala.util.Try

/**
 * Class representing response from api
 * @param id unique id of the response
 * @param response response result
 */
case class ApiResponse(id: UUID, response: Try[Response])
