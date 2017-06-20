package org.seqnote.api

/**
  * Created by f on 19/5/17.
  */
sealed trait Step

case class NoteStep(
  note:     Note, // FIXME Consider changing to interval (and putting note in the track.
  octave:   Octave,
  duration: MIDIValue,
  velocity: MIDIValue
) extends Step

case class DrumStep(
  instrument:   MIDIValue,
  velocity:     MIDIValue
) extends Step

case object OffStep extends Step
