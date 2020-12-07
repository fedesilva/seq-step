import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization  := "com.cosomojo",
      scalaVersion  := "2.13.4",
      version       := "0.0.1-SNAPSHOT"
    )),
    name := "SeqStep",
    libraryDependencies ++= runtimeDeps ++ testDeps
  )
  .settings(
    scalacOptions in Global += "-Ymacro-annotations"
  )
  .settings(
    Global / onChangedBuildSource := ReloadOnSourceChanges
  )

