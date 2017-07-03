
import java.time.{LocalDateTime, ZoneId}

import fs2._

import scala.collection.mutable
import scala.concurrent.duration._

// To Do List
// - Create interval stream
// - Create steps stream
// - merge them


implicit val scheduler: Scheduler = Scheduler.fromFixedDaemonPool(2)

implicit val strategy: Strategy   = Strategy.fromFixedDaemonPool(2)

val zone = ZoneId.of("America/Montevideo")

val tickInterrupter = time.sleep[Task](15.seconds).map( _  => true) //++ Stream(true)

val tint = fs2.async.signalOf[Task, Boolean](false)
val tints = tint.flatMap(s => s.modify( _ => true))




val ticks = time.awakeEvery[Task](250.millis).map{ duration =>
  val now = LocalDateTime.now(ZoneId.of("America/Montevideo"))
  println(s"${duration.toMillis} at $now")
  duration.toMillis -> now
}.interruptWhen(tickInterrupter)

val notes = Stream.emits(List(1,2,3)).repeat

val zipped = ticks.zip(notes).map{
  case ((a,b),c) => 
  println(c)
  (a,b,c)
}


val runEff = zipped.runLog

