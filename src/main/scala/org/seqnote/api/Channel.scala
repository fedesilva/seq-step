package org.seqnote.api

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Channel[T <: Step] {
  val steps: IndexedSeq[T]
}

final case class SynthChannel(
  steps: IndexedSeq[NoteStep]
) extends Channel[NoteStep]

final case class DrumChannel(
  note:   Note,
  steps: IndexedSeq[DrumStep]
) extends Channel[DrumStep]
