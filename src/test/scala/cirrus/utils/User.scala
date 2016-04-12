package cirrus.utils

import argonaut.Argonaut._
import argonaut._
import play.api.libs.json.OFormat
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
 * Created by Abim on 07/03/2016.
 */
case class User(username: String, gender: String, mobile: String, email: String)

object User {
  implicit val userSprayFormat: RootJsonFormat[User] = jsonFormat4(User.apply)

  implicit val userCodec: CodecJson[User] = casecodec4(User.apply, User.unapply)("username", "gender", "mobile", "email")
  
  implicit val userPlayFormat: OFormat[User] = play.api.libs.json.Json.format[User]
}

