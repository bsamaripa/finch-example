import Dependencies._

lazy val root = (project in file("."))
	.enablePlugins(JavaAppPackaging)
  .settings(
    name := "Example App",
    scalaVersion := "2.12.5",
    version      := "0.1.0",
    organization := "com.bsamaripa",
    libraryDependencies ++= backendDeps,
    scalacOptions ++= scalacFlags,
    addCompilerPlugin(
      "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
    )
  )
