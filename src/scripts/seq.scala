

import org.seqstep.api._

import scala.collection.immutable.SortedMap
import eu.timepit.refined._

val seq = Sequencer.initialized

val (pidx, p) = seq.patterns.head
val (tidx, tr) = p.tracks.head
val (cidx, ch) = tr.channels.collect{ case (idx, d: DrumChannel) => idx -> d}.head

// [(1),2,3,4] [(5),6,7,8] [(9),10,11,12] [(13),14,15,16]
// 1,5,9,13

val vel = refineMV[MIDIRange](32)

val newSteps = SortedMap[Int, Seq[DrumStep]](
  1   -> Seq(DrumStep(vel)),
  5   -> Seq(DrumStep(vel)),
  9   -> Seq(DrumStep(vel)),
  13  -> Seq(DrumStep(vel))
)

val nch = ch.copy(steps = newSteps)
