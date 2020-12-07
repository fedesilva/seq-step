
import java.time.{LocalDateTime, ZoneId}

import cats.effect._
import fs2.Stream
import scala.concurrent.ExecutionContext.Implicits.global



import scala.concurrent.duration._

implicit val timer: Timer[IO] = IO.timer(global)
implicit val cs: ContextShift[IO] = IO.contextShift(global)

// To Do List
// - Create interval stream
// - Create steps stream
// - merge them

val zone = ZoneId.of("America/Montevideo")

val ticks = Stream.awakeEvery[IO](250.millis).interruptAfter(5.seconds)

val notes = Stream.emits(List(1,2,3)).repeat

val zipped = ticks.zip(notes).map{
  case (fd,c) =>
  println(s"duration ${fd.toMillis} notes $c")
  (fd,c)
}


def run(): Unit = {
  zipped.compile.drain.unsafeRunSync()
}

