import sbt._

object Dependencies {
  lazy val refined    = "eu.timepit" %% "refined" % "0.8.1"
  lazy val cats       = "org.typelevel" %% "cats" % "0.9.0"
  lazy val fs2Core    = "co.fs2" %% "fs2-core" % "0.9.6"
  lazy val scalaTest  = "org.scalatest" %% "scalatest" % "3.0.1"
}
