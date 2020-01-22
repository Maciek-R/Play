package controllers

import javax.inject._
import play.api.db._
import play.api.libs.json.Json
import play.api.mvc._
import users.{UserDao, UserDaoImpl, UserService, UserServiceImpl}
import users.model.{User, UserRoles}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database,
                               cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def main = Action {
    val user = User("aaa", "bbb", 18, UserRoles.SuperAdmin)

    val userDao: UserDao = new UserDaoImpl
    val userService: UserService = new UserServiceImpl(userDao, db)
    userService.saveUser(user)

    Ok(views.html.index("Your new application is ready."))
  }

  def listUsers = Action {
    val userDao: UserDao = new UserDaoImpl
    val userService: UserService = new UserServiceImpl(userDao, db)

    val users = userService.getAllUsers()
    import _root_.users.model.User.fmt
   // userService.saveUser(user)
    Ok(Json.toJson(users))
  }

}
