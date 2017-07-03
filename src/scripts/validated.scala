
import cats._, cats.data._, cats.implicits._
import Validated.{ valid, invalid }
import cats.data.{ NonEmptyList => NEL }

// |@| is called "cartesian builder".

val badResult: Validated[NonEmptyList[String], String] =
  (
    valid[NEL[String], String]("event 1 ok")                |@|
    invalid[NEL[String], String](NEL.of("event 2 failed!")) |@|
    invalid[NEL[String], String](NEL.of("event 3 failed!"))
  ).map { (a: String, b: String, c: String) =>  a + b + c}


val okResult: Validated[NonEmptyList[String], String] =
  (
    valid[NEL[String], String]("event 1 ok") |@|
    valid[NEL[String], String]("event 2 ok")
  ).map { (a: String, b: String) =>  a ++ b }
