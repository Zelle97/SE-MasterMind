name := "SE-MasterMind"

version := "0.1"

scalaVersion := "3.1.1"

mainClass := Some("mastermind.MasterMind")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13)
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"

coverageExcludedPackages := "<empty>;mastermind.view.*;<empty>;mastermind.MasterMindModule\\.*;<empty>;mastermind.MasterMind\\.*"

assemblyJarName in assembly := "mastermind.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

scalacOptions += "-deprecation"
