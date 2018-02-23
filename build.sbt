name := "cd-kafka"

version := "1.0"

val akkaV = "2.5.3"
val scalaV = "2.12.1"

scalaVersion := scalaV

val loggingDeps = Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-stream" % akkaV,
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.16",
  "com.typesafe.akka" %% "akka-slf4j" % akkaV
) ++ loggingDeps
