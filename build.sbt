import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.cosomojo",
      scalaVersion := "3.2.1",
      version := "0.1.0-SNAPSHOT"
    )),    
    Global / onChangedBuildSource := ReloadOnSourceChanges,  
    name := "SeqStep",
    libraryDependencies ++= runtimeDeps ++ testDeps,
    scalacOptions ++= Seq(
      // "-new-syntax",                       // Enforce new syntax 
      "-indent",
      "-rewrite",
      "-language:strictEquality",
      "-explain"
    )
  )


//addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)
  
