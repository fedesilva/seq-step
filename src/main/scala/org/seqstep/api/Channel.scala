package org.seqstep.api

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Channel {
  val steps: SortedIntMap[Step]
}

final case class SynthChannel(
  steps: SortedIntMap[NoteStep]
) extends Channel

final case class DrumChannel(
  note:   Note,
  octave: Octave,
  steps: SortedIntMap[DrumStep]
) extends Channel
