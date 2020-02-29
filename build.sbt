name := """play-scala-myapp"""
organization := "com.tuyano.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.3"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
// libraryDependencies += evolutions
// libraryDependencies += jdbc
// libraryDependencies += "org.playframework.anorm" %% "anorm" %  "2.6.4"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tuyano.play.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tuyano.play.binders._"
