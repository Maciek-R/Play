package users

import org.scalatest.{FlatSpec, Matchers}
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.stubControllerComponents
import play.api.db._
import users.model.{User, UserRoles}
import akka.actor.ActorSystem
import controllers.{AsyncController, CountController}
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.FakeRequest
import services.Counter

class UserDaoSpec extends PlaySpec{
  "UserDaoSpec" should {
    "provide 'saveUser' and 'list' methods" which {
      "insert data" in {

        val database = Databases(
          driver = "org.postgresql.Driver",
          url = "jdbc:postgresql://localhost:5432/test"
        )

        Databases.withDatabase(
          driver = "org.postgresql.Driver",
          url = "jdbc:postgresql://localhost:5432/test",
          config = Map(
            "user" -> "postgres",
            "password" -> "qwerty"
          )
        ) {
          database =>
            implicit val connection = database.getConnection()

            val userDao: UserDao = new UserDaoImpl
            val user = User("name", "lastName", 18, UserRoles.Admin)


            userDao.saveUser(user)
            val users = userDao.list()
            users mustBe Symbol("nonEmpty")
            users.size mustBe 1
        }


      }
    }
  }
}
