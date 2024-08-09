val ScalatraVersion = "3.1.0"

ThisBuild / scalaVersion := "3.3.3"
ThisBuild / organization := "com.cumbre"

lazy val hello = (project in file("."))
  .settings(
    name    := "Cumbre Website",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.google.api-client"          % "google-api-client"          % "1.33.0",
      "com.google.oauth-client"        % "google-oauth-client"        % "1.31.5",
      "com.softwaremill.sttp.client3" %% "circe"                      % "3.6.1",
      "com.softwaremill.sttp.client3" %% "core"                       % "3.6.1",
      "io.circe"                      %% "circe-core"                 % "0.14.1",
      "io.circe"                      %% "circe-generic"              % "0.14.1",
      "io.circe"                      %% "circe-parser"               % "0.14.1",
      "org.scalatra"                  %% "scalatra-jakarta"           % ScalatraVersion,
      "org.scalatra"                  %% "scalatra-scalatest-jakarta" % ScalatraVersion % "test",
      "ch.qos.logback"                 % "logback-classic"            % "1.5.6"         % "runtime",
      "org.eclipse.jetty.ee10"         % "jetty-ee10-webapp"          % "12.0.10"       % "container",
      "jakarta.servlet"                % "jakarta.servlet-api"        % "6.0.0"         % "provided",
    ),
  )

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)

Jetty / containerLibs := Seq("org.eclipse.jetty.ee10" % "jetty-ee10-runner" % "12.0.10" intransitive ())
Jetty / containerMain := "org.eclipse.jetty.ee10.runner.Runner"
