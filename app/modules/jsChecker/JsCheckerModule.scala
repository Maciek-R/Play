package modules.jsChecker

import com.softwaremill.macwire.wire
import controllers.AssetsFinder
import play.api.mvc.ControllerComponents

trait JsCheckerModule {
  val jsCheckerController: JsCheckerController
}

trait JsCheckerModuleDependencies {
  implicit val assetsFinder: AssetsFinder
  val controllerComponents: ControllerComponents
}

trait JsCheckerModuleImpl extends JsCheckerModule with JsCheckerModuleDependencies {

  override lazy val jsCheckerController = wire[JsCheckerController]

}