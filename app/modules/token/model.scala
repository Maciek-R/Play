package modules.token

import play.api.libs.json.{Format, Json}

case class Data(infoText: String, token: String)

object Data {
  implicit val fmt: Format[Data] = Json.format[Data]
}