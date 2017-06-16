package org.seqnote.api

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Channel {
  val steps: SparseIndexedSeq[Step]
}

final case class SynthChannel(
  steps: SparseIndexedSeq[NoteStep]
) extends Channel

final case class DrumChannel(
  note:   Note,
  octave: Octave,
  steps: SparseIndexedSeq[DrumStep]
) extends Channel
