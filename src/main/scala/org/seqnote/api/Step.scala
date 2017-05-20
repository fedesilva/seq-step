package org.seqnote.api

import org.seqnote.api.Refinements.{MIDIValue, Octave, StepLength}

/**
  * Created by f on 19/5/17.
  */
sealed trait Step {
  val index: StepLength
}

case class NoteStep(
  index:    StepLength,
  note:     Note,
  octave:   Octave,
  duration: MIDIValue,
  velocity: MIDIValue
) extends Step

case class DrumStep(
  index:        StepLength,
  instrument:   MIDIValue,
  velocity:     MIDIValue
) extends Step
