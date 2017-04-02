organization := "com.github.meln1k"

name := """reactive-telegrambot"""

version := "1.1"

scalaVersion := "2.11.6"

val akkaV = "2.4.17"
val akkaHttpV = "10.0.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-slf4j" % akkaV,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
