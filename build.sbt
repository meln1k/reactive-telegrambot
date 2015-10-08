organization := "com.github.meln1k"

name := """reactive-telegrambot"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.13",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.13",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.13" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
