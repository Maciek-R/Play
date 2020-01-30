package modules.token.tokenRetriever

import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory
import javax.inject.{Inject, Singleton}
import modules.token.Data
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.FiniteDuration

trait TokenRetrieverService {
  def acquireToken(): Future[Either[String, Data]]
}

class TokenRetrieverServiceImpl (ws: WSClient)(implicit ec: ExecutionContext) extends TokenRetrieverService {

  def acquireToken() = {
    val config = ConfigFactory.load("application.conf")

    val newsLogin = config.getString("news.apiLogin")
    val newsPassword = config.getString("news.apiPassword")
    // val newsUrl = config.getString("news.apiAddress")

    val request = ws
      .url("http://localhost:9001/generateToken")
      .addHttpHeaders("login" -> newsLogin, "password" -> newsPassword)
      .addQueryStringParameters("command" -> "Hello")
      .withRequestTimeout(FiniteDuration(5, TimeUnit.SECONDS))
      .get()

    for {
      response <- request
      data <- Future.successful(parseResponse(response))
    } yield data
  }

  private def parseResponse(wsResponse: WSResponse): Either[String, Data] = {
    wsResponse.status match {
      case 200 => Right(wsResponse.json.as[Data])
      case _ => Left(wsResponse.body)
    }
  }
}
