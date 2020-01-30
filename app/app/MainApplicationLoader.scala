package app

import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader}

class MainApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    val mainApplication = new MainApplication(context)

    mainApplication.application
  }
}