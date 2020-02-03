package modules.jsChecker

import controllers.AssetsFinder
import play.api.mvc._

class JsCheckerController (cc: ControllerComponents)(implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def checkJavascript = Action { _ =>
    Ok(views.html.javascriptChecker())
  }

}
