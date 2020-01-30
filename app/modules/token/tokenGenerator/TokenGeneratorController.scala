package modules.token.tokenGenerator

import javax.inject.{Inject, Singleton}
import modules.token.Data
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class TokenGeneratorController @Inject()(cc: ControllerComponents, ws: WSClient)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def generateToken = Action { request =>

    val result = for {
      _ <- checkHeaders(request.headers)
      token <- Right(handleRequest(request))
    } yield token

    result match {
      case Right(token) => Ok(Json.toJson(Data("Pomyślne żądanie o token", token)))
      case Left(fail) => Unauthorized(fail)
    }
  }

  def checkHeaders(headers: Headers) = {
    for {
      login <- headers.get("login").toRight("Missing login in header")
      password <- headers.get("password").toRight("Missing password in header")
      _ <- if(login == "newsApi") Right(()) else Left("Wrong login")
      _ <- if(password == "newsSecret") Right(()) else Left("Wrong password")
    } yield ()
  }

  def handleRequest(request: Request[AnyContent]) = {
    request.getQueryString("command") match {
      case Some("Hello") => "Hello...qwertyuiop"
      case _ => "qwertyuiop"
    }
  }
}
