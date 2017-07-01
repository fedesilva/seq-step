import sbt._

object Dependencies {
  
  val monocleVersion = "1.4.0"
  
  lazy val runtimeDeps = Seq(
    "eu.timepit"    %% "refined"    % "0.8.1",
    "org.typelevel" %% "cats"       % "0.9.0",
    "com.chuusai"   %% "shapeless"  % "2.3.2",
    "co.fs2"        %% "fs2-core"   % "0.9.6",
    "com.github.julien-truffaut" %%  "monocle-core"     % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-macro"    % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-refined"  % monocleVersion
  )
  
  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
  )
  
  
  
}
