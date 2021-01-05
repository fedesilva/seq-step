
import java.time.ZoneId
import cats.effect._
import fs2.{Pure, Stream}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

implicit val timer: Timer[IO] = IO.timer(global)
implicit val cs: ContextShift[IO] = IO.contextShift(global)

// To Do List
// - Create interval stream
// - Create steps stream
// - merge them

val zone: ZoneId =
  ZoneId.of("America/Montevideo")

val ticks: Stream[IO, FiniteDuration] =
  Stream
    .awakeEvery[IO](250.millis)
    .interruptAfter(5.seconds)

val notes: Stream[Pure, Int] =
  Stream
    .emits(List(1, 2, 3))
    .repeat

val zipped: Stream[IO, (FiniteDuration, Int)] =
  ticks.zip(notes).map {
    case (fd, c) =>
      println(s"duration ${fd.toMillis} notes $c")
      (fd, c)
  }

def run(): Unit =
  zipped
    .compile
    .drain
    .unsafeRunSync()
