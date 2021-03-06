name := "SE-MasterMind"

version := "0.1"

scalaVersion := "2.13.3"

mainClass := Some("mastermind.MasterMind")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.11"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"

coverageExcludedPackages := "<empty>;mastermind.view.*;<empty>;mastermind.MasterMindModule\\.*;<empty>;mastermind.MasterMind\\.*"

assemblyJarName in assembly := "mastermind.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
