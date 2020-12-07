import sbt._

object Dependencies {
  
  val monocleVersion = "2.1.0"
  
  lazy val runtimeDeps = Seq(
    "eu.timepit"    %% "refined"    % "0.9.19",
    "org.typelevel" %% "cats-core"  % "2.3.0",
    "com.chuusai"   %% "shapeless"  % "2.3.3",
    "co.fs2"        %% "fs2-core"   % "2.4.6",
    "com.github.julien-truffaut" %%  "monocle-core"     % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-macro"    % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-refined"  % monocleVersion
  )
  
  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.2.3" % "test",
    "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
  )
  
  
  
}
