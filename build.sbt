name := "ClinicManagement"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.7.0",
  "org.postgresql" % "postgresql" % "42.2.20",
  "com.zaxxer" % "HikariCP" % "3.4.5",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test
)

