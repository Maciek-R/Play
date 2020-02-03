package modules.guessNumber

import akka.actor.ActorSystem
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents

trait GuessNumberModule {
  val guessNumberController: GuessNumberController
}

trait GuessNumberModuleDependencies {
  implicit val actorSystem: ActorSystem
  val controllerComponents: ControllerComponents
}

trait GuessNumberModuleImpl extends GuessNumberModule with GuessNumberModuleDependencies {

  override lazy val guessNumberController = wire[GuessNumberController]

}