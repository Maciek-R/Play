package users

import users.model.User
import play.api.db.DBApi

import anorm._
import play.api.db._

trait UserService {
  def saveUser(user: User): Unit

  def getAllUsers(): List[User]
}

class UserServiceImpl(val userDao: UserDao, val database: Database) extends UserService {
  override def saveUser(user: User): Unit = {
    database.withConnection{ implicit conn =>
        userDao.saveUser(user)
    }
  }

  override def getAllUsers(): List[User] = {
    database.withConnection{implicit conn =>
      userDao.list()
    }
  }
}