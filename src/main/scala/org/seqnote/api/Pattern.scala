package org.seqnote.api

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Pattern[T <: Step] {
  val steps: IndexedSeq[T]
}

final case class SynthPattern(
  steps: IndexedSeq[NoteStep]
) extends Pattern[NoteStep]

final case class DrumPattern(
  note:   Note,
  steps: IndexedSeq[DrumStep]
) extends Pattern[DrumStep]