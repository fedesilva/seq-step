package org.seqstep.api

sealed trait Step

case class SynthStep(
  note:   Note,
  octave: Octave,
  duration: Int,
  velocity: MIDIValue
)

case class DrumStep(
  instrument: Int  ,
  velocity: MIDIValue
)


sealed trait Pattern {
  val steps: SortedIntMap[Step]
}

case class SynthPattern(
  steps
)


