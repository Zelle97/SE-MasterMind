name := "SE-MasterMind"

version := "0.1"

scalaVersion := "3.1.1"

lazy val view = project.in(file("view")).settings(mainClass in Compile := Some("mastermind.view.ViewInterface"))
lazy val core = project.in(file("core")).settings(mainClass in Compile := Some("mastermind.core.CoreInterface"))
lazy val persistence = project in file("persistence")
lazy val root = (project in file(".")).aggregate(view, core, persistence)

mainClass := Some("mastermind.MasterMind")

//val AkkaVersion = "2.6.8"
val AkkaVersion = "2.6.9"
val AkkaHttpVersion = "10.2.9"
libraryDependencies ++= Seq(
  ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)
)

libraryDependencies += ("org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0").cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT")
libraryDependencies += "org.mariadb.jdbc" % "mariadb-java-client" % "3.0.4"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.4"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"
libraryDependencies += ("com.typesafe.slick" %% "slick-hikaricp" % "3.3.3").cross(CrossVersion.for3Use2_13)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13)
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "org.mockito" % "mockito-core" % "2.7.19" % Test
libraryDependencies += "org.scalatestplus" %% "mockito-4-5" % "3.2.12.0" % "test"

coverageExcludedPackages := "<empty>;mastermind.view.*;<empty>;mastermind.MasterMindModule\\.*;<empty>;mastermind.MasterMind\\.*"

assembly / assemblyJarName := "mastermind.jar"
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

scalacOptions += "-deprecation"

resolvers += "jitpack" at "https://jitpack.io"
