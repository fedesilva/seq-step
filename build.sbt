import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.cosomojo",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "SeqStep",
    libraryDependencies ++= Seq(
      refined, cats,
      scalaTest % Test
    )
  )

  
