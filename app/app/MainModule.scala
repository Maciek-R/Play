package app

import controllers.{InitialModule, InitialModuleImpl}
import modules.token.{TokenModule, TokenModuleImpl}

trait MainModule
  extends TokenModule with TokenModuleImpl
  with InitialModule with InitialModuleImpl