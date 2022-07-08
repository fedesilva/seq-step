import sbt._

object Dependencies {
  
  val monocleVersion = "3.1.0"
  
  lazy val runtimeDeps = Seq(
    "eu.timepit" %% "refined" % "0.10.1",
    "org.typelevel" %% "cats-core" % "2.8.0",
    "org.typelevel" %% "shapeless3-deriving" % "3.0.1",
    "co.fs2" %% "fs2-core" % "3.2.9",
    "dev.optics" %% "monocle-core" % "3.1.0",
    "dev.optics" %% "monocle-macro" % "3.1.0"
  )
  
  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.2.12" % "test"
    //    "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
  )
  
  
}
