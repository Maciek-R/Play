package controllers

import akka.actor.ActorSystem
import com.softwaremill.macwire.wire
import play.api.db.Database
import play.api.mvc.ControllerComponents
import services.AtomicCounter

import scala.concurrent.ExecutionContext

trait InitialModule {
  val countController: CountController
  val asyncController: AsyncController
  val homeController: HomeController
}

trait InitialModuleDependencies {
  val controllerComponents: ControllerComponents
  val actorSystem: ActorSystem
  implicit val assetsFinder: AssetsFinder
  implicit val executionContext: ExecutionContext
  val db: Database
}

trait InitialModuleImpl extends InitialModule with InitialModuleDependencies {
  lazy val counter = wire[AtomicCounter]
  lazy val countController = wire[CountController]

  lazy val asyncController = wire[AsyncController]

  lazy val homeController = wire[HomeController]
}