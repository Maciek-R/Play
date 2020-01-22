package users.model

import play.api.libs.json.{Format, Json}
import users.model.UserRoles.UserRole

case class User (name: String, lastName: String, age: Int, role: UserRole)

object User {
  implicit val userRoleFmt: Format[UserRole] = Json.formatEnum(UserRoles)
  implicit val fmt: Format[User] = Json.format[User]
}