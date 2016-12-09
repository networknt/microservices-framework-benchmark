name := """play-java"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-java_2.11" % "2.5.10",
  "com.typesafe.play" %% "play-netty-server" % "2.5.10"
)
