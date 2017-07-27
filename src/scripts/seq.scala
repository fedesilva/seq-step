import org.seqstep.api._

import scala.collection.immutable.SortedMap
import eu.timepit.refined._

val seq = Sequencer.initialized

// [(1),2,3,4] [(5),6,7,8] [(9),10,11,12] [(13),14,15,16]
// 1,5,9,13

val vel = refineMV[MIDIRange](32)

val newSteps = SortedMap[Int, DrumStep](
  1   -> DrumStep(vel),
  5   -> DrumStep(vel),
  9   -> DrumStep(vel),
  13  -> DrumStep(vel)
)

// seq.addStep(trackIndex, patternIndex, stepIndex, step)
// seq.addSteps(trackIndex, patternIndex, steps: Map[Int, Vector[Step])

