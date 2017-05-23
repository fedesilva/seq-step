
import java.time.{LocalDateTime, ZoneId}

import fs2._

import scala.concurrent.duration._

// To Do List
// - Create interval stream
// - Create steps stream
// - merge them


implicit val scheduler: Scheduler = Scheduler.fromFixedDaemonPool(2)

implicit val strategy: Strategy   = Strategy.fromFixedDaemonPool(2)

val zone = ZoneId.of("America/Montevideo")

val ta = time.awakeEvery[Task](250.millis).map{ duration =>
  val now = LocalDateTime.now(ZoneId.of("America/Montevideo"))
  println(s"${duration.toMillis} at $now")
  duration -> now
}

val t = time.every[Task](250.millis).map{ flag =>
  val now = LocalDateTime.now(zone)
  println(s" $flag at $now")
  flag -> now
}


val sl = Stream.emits(List(1,2,3)).repeat

val zipped = ta.zip(sl).map{ 
  case ((a,b),c) => 
  println(c)
  (a,b,c)
}

val runEff = zipped.runLog

