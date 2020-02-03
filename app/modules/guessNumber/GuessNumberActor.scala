package modules.guessNumber

import akka.actor.{Actor, ActorRef, Props}

import scala.util.{Random, Try}

class GuessNumberActor(out: ActorRef) extends Actor {

  override def preStart(): Unit = init()

  private var numberToGuess = 0
  private var totalShots = 0
  private var guessedNumbers = 0

  def receive = {
    case msg: String =>
      Try(msg.toInt).toOption match {
        case Some(number) =>
          totalShots = totalShots + 1
          if(number > numberToGuess) {
            out ! "Too big number! Try again!"
          }
          else if(number < numberToGuess) {
            out ! "Too small number! Try again!"
          }
          else {
            guessedNumbers = guessedNumbers + 1
            out ! "Congratulations! You won!"
            out ! s"Number of guessed numbers: $guessedNumbers with total shots $totalShots!"
            init()
          }
        case None =>
          out ! "Give number!"
      }
  }

  def init() = {
    numberToGuess = Random.nextInt(30)
    out ! "Guess number!"
  }
}

object GuessNumberActor {
  def props(out: ActorRef) = Props(new GuessNumberActor(out))
}