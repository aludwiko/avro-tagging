name := """minimal-scala"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

val macwireVersion = "2.2.3"
lazy val macwireDependencies = Seq(
  "com.softwaremill.macwire" %% "macros"  % macwireVersion,
  "com.softwaremill.macwire" %% "util"    % macwireVersion,
  "com.softwaremill.common"  %% "tagging" % "1.0.0"
)

lazy val otherDependencies = Seq(
  "org.typelevel"              %% "cats"              % "0.7.0",
  "org.json4s"                 %% "json4s-native"     % "3.3.0",
  "com.github.t3hnar"          % "scala-bcrypt_2.11"  % "2.4",
  "com.sksamuel.avro4s"        %% "avro4s-core"       % "1.6.1",
  "com.sksamuel.avro4s"        %% "avro4s-macros"     % "1.6.1",
  "com.typesafe.scala-logging" %% "scala-logging"     % "3.4.0",
  "org.slf4j"                  % "log4j-over-slf4j"   % "1.7.21",
  "com.typesafe.akka"          %% "akka-stream-kafka" % "0.12",
  "net.manub"        %% "scalatest-embedded-kafka" % "0.7.1"  % "test",
  "org.scalacheck"   %% "scalacheck"               % "1.12.5" % "test",
  "com.ironcorelabs" %% "cats-scalatest"           % "1.3.0"  % "test",
  "io.swagger"       %% "swagger-play2" % "1.5.3.2",
  "org.clapper"      %% "classutil"     % "1.0.12",
  "org.bouncycastle" % "bcprov-jdk16"   % "1.46",
  "org.scalacheck"   %% "scalacheck"    % "1.12.5",
  "org.scalaz"       %% "scalaz-core"   % "7.1.3"
)

libraryDependencies ++= macwireDependencies ++ otherDependencies

fork in run := true

scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf"))
onLoad in Global := (Command.process("scalafmt", _: State)) compose (onLoad in Global).value
