package org.seqstep.api

/**
  * Created by f on 19/5/17.
  */
sealed trait Step

case class NoteStep(
  note:     Note, 
  octave:   Octave,
  duration: Int,
  velocity: MIDIValue
) extends Step

case class DrumStep(velocity: MIDIValue) extends Step

case class OffStep(note: Note, octave: Octave) extends Step






