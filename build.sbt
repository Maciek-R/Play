lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-app""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "1.4.199",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      jdbc,
      jdbc % Test,
      javaJdbc,
      "org.playframework.anorm" %% "anorm" % "2.6.5",
      "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
      ws,
      "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )