import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.cosomojo",
      scalaVersion := "3.2.1",
      version := "0.1.0-SNAPSHOT"
    )),    
    //Global / onChangedBuildSource := ReloadOnSourceChanges,  
    name := "SeqStep",
    libraryDependencies ++= runtimeDeps ++ testDeps,
    scalacOptions ++= Seq(
      "-Werror",
      "-print-lines",
      "-deprecation",
      "-explaintypes",
      "-language:strictEquality",
      "-explain",
      "-new-syntax", // Enforce new syntax, rewrite stuff if paired with -rewrite
      "-rewrite",
    )
  )


//addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)
  