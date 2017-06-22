package org.seqstep.api

/** Step  is a representation of a note event. There are different step types for drums and synths.
  *
  * Created by f on 19/5/17.
  */
sealed trait Step

/** Synth Step contains a note and an octave and duration
  *
  * @param note note to play
  * @param octave octave of the note to play
  * @param duration for how long to play the note
  * @param velocity play the note with this intensity
  *
  */
final case class NoteStep(
  note:     Note, 
  octave:   Octave,
  duration: Int,
  velocity: MIDIValue
) extends Step

/** A DrumStep does not hold a note, it's channel will hold the note since all steps in a
  * drum channel are on the same note.
  *
  * @param velocity step velocity
  *
  */
final case class DrumStep(velocity: MIDIValue) extends Step

/** Rendered Step represents a generic step ready for midi event generation; note on and note off.
  *
  * This is what will be played.
  *
  */
trait RenderedStep

final case class RenderedNoteStep(
  note:     Note,
  octave:   Octave,
  velocity: MIDIValue
) extends RenderedStep

final case class RenderedNoteOffStep(
  note: Note,
  octave: Octave
) extends RenderedStep






