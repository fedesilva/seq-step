
import java.time.{LocalDateTime, ZoneId}

import fs2._

import scala.collection.mutable
import scala.concurrent.duration._

// To Do List
//
// - Create interval stream
// - Test queue
// - Test Signal
//

implicit val scheduler: Scheduler = Scheduler.fromFixedDaemonPool(2)

implicit val strategy: Strategy   = Strategy.fromFixedDaemonPool(2)

val zone = ZoneId.of("America/Montevideo")

val tickInterrupter = time.sleep[Task](15.seconds).map( _  => true)

val ticks = time.awakeEvery[Task](250.millis).map { duration =>
  LocalDateTime.now(ZoneId.of("America/Montevideo"))
}.interruptWhen(tickInterrupter)





