import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.functionalscalaws"
ThisBuild / organizationName := "functionalscalaws"

lazy val root = (project in file("."))
  .settings(
    name := "FunctionalScalaWS",
    libraryDependencies += scalaTest % Test
  )

val http4sVersion     = "0.21.2"
val pureConfigVersion = "0.12.3"

libraryDependencies ++= Seq(
  "org.typelevel"         %% "cats-core"              % "2.1.1",
  "dev.zio"               %% "zio"                    % "1.0.0-RC18-2",
  "dev.zio"               %% "zio-interop-cats"       % "2.0.0.0-RC13",
  "org.typelevel"         %% "cats-effect"            % "2.1.2",
  "org.http4s"            %% "http4s-dsl"             % http4sVersion,
  "org.http4s"            %% "http4s-blaze-server"    % http4sVersion,
  "com.github.pureconfig" %% "pureconfig"             % pureConfigVersion,
  "com.github.pureconfig" %% "pureconfig-cats-effect" % pureConfigVersion,
  "io.chrisdavenport"     %% "log4cats-slf4j"         % "1.0.1",
  "ch.qos.logback"        % "logback-classic"         % "1.2.3",
  "org.http4s"            %% "http4s-circe"           % http4sVersion,
  "io.circe"              %% "circe-generic"          % "0.12.3",
  //Test
  "dev.zio"           %% "zio-test"        % "1.0.0-RC18-2" % "test",
  "dev.zio"           %% "zio-test-sbt"    % "1.0.0-RC18-2" % "test",
  "org.scalatest"     %% "scalatest"       % "3.1.1" % "test",
  "org.scalatestplus" %% "scalacheck-1-14" % "3.1.1.1",
  "org.scalacheck"    %% "scalacheck"      % "1.14.3",
  "com.olegpy"        %% "meow-mtl-core"   % "0.4.0"
)

testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds",
  "-Wdead-code",
  "-Wunused:privates",
  "-Wunused:locals",
  "-Wunused:explicits",
  "-Wunused:params",
  "-Xlint:unused",
  "-Wunused:imports",
  "-Ymacro-annotations"
)
