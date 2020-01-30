package app

import com.softwaremill.macwire._
import controllers._
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.db.{DBComponents, HikariCPComponents}
import play.api.libs.ws.ahc.AhcWSComponents
import play.filters.HttpFiltersComponents
import router.Routes


class MainApplication(context: Context)
  extends BuiltInComponentsFromContext(context)
    with AhcWSComponents
    with DBComponents
    with HikariCPComponents
    with AssetsComponents
    with HttpFiltersComponents
    with MainModule {

  override implicit lazy val assetsFinder: AssetsFinder = this.assetsMetadata.finder

  lazy val db = this.dbApi.database("default")

  lazy val router = wire[Routes]
}
