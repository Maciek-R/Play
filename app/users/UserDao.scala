package users

import java.sql.Connection

import users.database.UserDatabase
import users.model.{User, UserRoles}
import anorm._
import anorm.SqlParser._


trait UserDao {
  def saveUser(user: User)(implicit c: Connection): Unit
  def getUserByName(name: String): Option[User]
  def list()(implicit c: Connection): List[User]
}

class UserDaoImpl extends UserDao {
  override def saveUser(user: User)(implicit c: Connection): Unit = {
    UserDatabase.saveUser(user)


    SQL(
      s"""INSERT INTO users(usr_name, usr_last_name)
         |           VALUES('name', 'lastname')
       """.stripMargin
    ).execute()
  }

  override def getUserByName(name: String): Option[User] = {
    UserDatabase.findUser(name)
  }

  override def list()(implicit c: Connection): List[User] = {
    SQL(
      """
        |SELECT * from users
      """.stripMargin
    ).as(userParser.*)
    /*List(
      User("aaa", "bbb", 18, UserRoles.SuperAdmin),
      User("ccc", "ddd", 19, UserRoles.Admin)
    )*/
  }

  val userParser: RowParser[User] =
    str("usr_name") ~
      str("usr_last_name") map {
      case name ~ lastName => User(name, lastName, 18, UserRoles.Admin)
    }
}