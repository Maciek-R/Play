package app

import controllers.{InitialModule, InitialModuleImpl}
import modules.guessNumber.GuessNumberModuleImpl
import modules.token.{TokenModule, TokenModuleImpl}
import modules.jsChecker.JsCheckerModuleImpl

trait MainModule
  extends TokenModule with TokenModuleImpl
  with InitialModule with InitialModuleImpl
  with JsCheckerModuleImpl with GuessNumberModuleImpl