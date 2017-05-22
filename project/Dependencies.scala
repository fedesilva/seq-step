import sbt._

object Dependencies {
  
  lazy val runtimeDeps = Seq(
    "eu.timepit"    %% "refined"    % "0.8.1",
    "org.typelevel" %% "cats"       % "0.9.0",
    "com.chuusai"   %% "shapeless"  % "2.3.2",
    "co.fs2"        %% "fs2-core"   % "0.9.6"
  )
  
  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1"
  )
  
  
  
}
