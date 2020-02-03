package modules.guessNumber

import akka.actor.ActorSystem
import play.api.libs.streams.ActorFlow
import play.api.mvc._

class GuessNumberController(cc: ControllerComponents)(implicit actorSystem: ActorSystem) extends AbstractController(cc) {

  //available at ws://localhost:9000/guessNumber
  def guessNumber = WebSocket.accept[String, String]{ requestHeader =>
    ActorFlow.actorRef { actorRef =>
      GuessNumberActor.props(actorRef)
    }
  }

}
