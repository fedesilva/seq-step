package org.seqstep.api

import monocle.macros.Lenses

import scala.collection.immutable.SortedMap

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Voice {
  val steps: SortedIntMap[Step]
}

@Lenses
final case class SynthVoice(
  steps: SortedIntMap[NoteStep]
) extends Voice

@Lenses
final case class DrumVoice(
  note:   Note,
  octave: Octave,
  steps:  SortedIntMap[DrumStep]
) extends Voice





