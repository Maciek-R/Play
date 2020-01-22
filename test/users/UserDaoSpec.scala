package users

import java.sql.Connection

import database.DatabaseSupport
import org.scalatestplus.play.PlaySpec
import play.api.db._
import users.model.{User, UserRoles}


class UserDaoSpec extends PlaySpec with DatabaseSupport {
  "UserDaoSpec" should {
    "provide 'saveUser' and 'list' methods" which {
      "insert user data and check if added correctly" in {
        withConnection { implicit connection =>
          val userDao: UserDao = new UserDaoImpl
          val user = User("name", "lastName", 18, UserRoles.Admin)

          userDao.saveUser(user)
          val users = userDao.list()
          users mustBe Symbol("nonEmpty")
          users.size mustBe 1
          users.head.name mustBe user.name
          users.head.lastName mustBe user.lastName
        }
      }
    }
  }
}
