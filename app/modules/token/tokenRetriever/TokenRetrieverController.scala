package modules.token.tokenRetriever

import javax.inject._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class TokenRetrieverController @Inject()(cc: ControllerComponents,
                                         tokenRetrieverService: TokenRetrieverService,
                                         ws: WSClient)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def acquireToken = Action.async {
    val result = tokenRetrieverService.acquireToken()

    result.map {
      case Right(data) => Ok(Json.toJson(data))
      case Left(fail) => Ok(fail)
    }
  }

}
