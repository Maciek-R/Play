package users.model

object UserRoles extends Enumeration {
  type UserRole = Value
  val SuperAdmin, Admin, User = Value
}
