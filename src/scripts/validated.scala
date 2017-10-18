
import cats._, cats.data._, cats.implicits._
import Validated.{ valid, invalid }
import cats.data.{ NonEmptyList => NEL }

// |@| is called "cartesian builder".



val badResult: Validated[NonEmptyList[String], Seq[Char]] = (
    valid[NEL[String], String]("event 1 ok")                |@|
    invalid[NEL[String], String](NEL.of("event 2 failed!")) |@|
    invalid[NEL[String], String](NEL.of("event 3 failed!"))
  ).map { (a: String, b: String, c: String) =>  a ++ b ++ c}


val okResult: Validated[NonEmptyList[String], Seq[Char]] = (
    valid[NEL[String], String]("event 1 ok") |@|
    valid[NEL[String], String]("event 2 ok")
  ).map { (a: String, b: String) =>  a ++ b }


val v1: Validated[NEL[String], String] = valid[NEL[String], String]("event 1 ok")
val v2: Validated[NEL[String], String] = valid[NEL[String], String]("event 1 ok")
val e1: Validated[NEL[String], String] = invalid[NEL[String], String](NEL.of("event 3 failed!"))

val vs: Seq[Validated[NEL[String], String]] = Seq(v1, v2, e1)

val v3: Validated[NEL[String], String] = vs.reduce(_ |+| _)

val allValid = Seq(v1, v2)

val allValidReduced = allValid.reduce(_ |+| _)

//.foldLeft(List[Validated[NEL[String], String]]()){ (l, v) => l :+ v.  }

val vv1: Validated[String, String] = valid[String, String]("event 1 ok")
val vv2: Validated[String, String] = valid[String, String]("event 1 ok")
val ee1: Validated[String, String] = invalid[String, String]("event 3 failed!")
val ee2: Validated[String, String] = invalid[String, String]("event 4 failed!")

val vvs: Seq[Validated[String, String]] = Seq(vv1, vv2, ee1, ee2)

val vv3: Validated[String, String] = vvs.reduce(_ |+| _)

val catched = Validated.catchNonFatal( "s".toInt )


