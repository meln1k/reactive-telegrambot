package com.github.meln1k.reactive.telegrambot.api

import java.util.UUID

import com.github.meln1k.reactive.telegrambot.methods.ApiMethod

/**
 * Class representing request to api
 * @param id unique id of the request
 * @param method request method
 */
case class ApiRequest(method: ApiMethod, id: UUID = UUID.randomUUID())
