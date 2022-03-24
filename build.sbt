scalaVersion := "3.1.1"
name := "ZioOne"
version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.0.0-RC2"
)

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:implicitConversions"
)
