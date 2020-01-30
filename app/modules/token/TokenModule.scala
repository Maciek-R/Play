package modules.token

import com.softwaremill.macwire.wire
import modules.token.tokenGenerator.TokenGeneratorController
import modules.token.tokenRetriever.{TokenRetrieverController, TokenRetrieverService, TokenRetrieverServiceImpl}
import play.api.libs.ws.WSClient
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait TokenModule {
  val tokenGeneratorController: TokenGeneratorController
  val tokenRetrieverService: TokenRetrieverService
  val tokenRetrieverController: TokenRetrieverController
}

trait TokenModuleDependencies {
  val controllerComponents: ControllerComponents
  implicit val executionContext: ExecutionContext
  val wsClient: WSClient
}

trait TokenModuleImpl extends TokenModule with TokenModuleDependencies {

  lazy val tokenGeneratorController = wire[TokenGeneratorController]

  lazy val tokenRetrieverService: TokenRetrieverService = wire[TokenRetrieverServiceImpl]
  lazy val tokenRetrieverController = wire[TokenRetrieverController]

}