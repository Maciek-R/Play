package users

import java.sql.Connection

import users.model.{User, UserRoles}
import anorm._
import anorm.SqlParser._


trait UserDao {
  def saveUser(user: User)(implicit c: Connection): Unit
  def list()(implicit c: Connection): List[User]
}

class UserDaoImpl extends UserDao {
  override def saveUser(user: User)(implicit c: Connection): Unit = {

    SQL(
      s"""INSERT INTO users(usr_name, usr_last_name)
         |           VALUES({name}, {lastName})
       """.stripMargin
    ).on(
      "name" -> user.name,
      "lastName" -> user.lastName)
      .execute()
  }

  override def list()(implicit c: Connection): List[User] = {
    SQL(
      """
        |SELECT * from users
      """.stripMargin
    ).as(userParser.*)
  }

  val userParser: RowParser[User] =
    str("usr_name") ~
      str("usr_last_name") map {
      case name ~ lastName => User(name, lastName, 18, UserRoles.Admin)
    }
}