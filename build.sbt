import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.cosomojo",
      scalaVersion := "3.1.3",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "SeqStep",
    libraryDependencies ++= runtimeDeps ++ testDeps,
    scalacOptions += "-explain"
  )


//addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)
  
