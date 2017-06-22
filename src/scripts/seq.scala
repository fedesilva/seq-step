

import org.seqstep.api._

import scala.collection.immutable.SortedMap
import eu.timepit.refined._

val seq = Sequencer.initialized

val (pidx, p)                 = seq.patterns.head
val (tidx, tr: DrumTrack)     = p.tracks.head
val (cidx, ch: DrumChannel)   = tr.channels.head  //.collect{ case (idx, d: DrumChannel) => idx -> d}.head

// [(1),2,3,4] [(5),6,7,8] [(9),10,11,12] [(13),14,15,16]
// 1,5,9,13

val vel = refineMV[MIDIRange](32)

val newSteps = SortedMap[Int, DrumStep](
  1   -> DrumStep(vel),
  5   -> DrumStep(vel),
  9   -> DrumStep(vel),
  13  -> DrumStep(vel)
)

val nch = ch.copy(steps = newSteps)

val ss = seq.copy(
  patterns = seq.patterns + (0 -> p.copy(
    tracks = p.tracks + ( 0 -> tr.copy(
      channels = tr.channels + (0 -> ch.copy(
        steps = newSteps
      ))
    ))
  ))
)