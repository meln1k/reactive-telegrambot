package com.github.meln1k.reactive.telegrambot.methods

/**
 * Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
 * @param user_id Unique identifier of the target user
 * @param offset Sequential number of the first photo to be returned. By default, all photos are returned.
 * @param limit Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
 */
case class GetUserProfilePhotos(user_id: Long,
                                offset: Option[Int] = None,
                                limit: Option[Int] = None) extends ApiMethod {
  override def name: String = "getUserProfilePhotos"

  override protected def methodParams: Map[String, Any] = Map(
    "user_id" -> user_id,
    "offset" -> offset,
    "limit" -> limit
  )
}
