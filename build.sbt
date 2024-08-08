name := "Cumbre"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.7.0",
  "org.postgresql" % "postgresql" % "42.2.20",
  "com.zaxxer" % "HikariCP" % "3.4.5",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test,
  "com.google.api-client" % "google-api-client" % "1.33.0",
  "com.google.oauth-client" % "google-oauth-client" % "1.31.5",
  "com.softwaremill.sttp.client3" %% "core" % "3.6.1",
  "com.softwaremill.sttp.client3" %% "circe" % "3.6.1",
  "io.circe" %% "circe-core" % "0.14.1",
  "io.circe" %% "circe-generic" % "0.14.1",
  "io.circe" %% "circe-parser" % "0.14.1",
  "javax.servlet" % "javax.servlet-api" % "4.0.1",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.44.v20210927", 
  "org.eclipse.jetty" % "jetty-server" % "9.4.44.v20210927"  
)
mainClass in Compile := Some("web.ScalatraBootstrap")