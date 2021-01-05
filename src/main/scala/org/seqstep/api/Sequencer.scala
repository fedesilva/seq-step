package org.seqstep.api

import cats.data.Validated.valid
import cats.data.{NonEmptyList => NEL, _}
import monocle.macros.Lenses

/** Top level container of tracks.
  *
  * TODO add scenes member. scenes should only be created by
  *
  * @param tracks the tracks this sequencer holds.
  *
  */
@Lenses
case class Sequencer(
  tracks: SortedIntMap[Track]
)

object Sequencer {

  /** New empty sequencer */
  def apply(): Sequencer = {
    val tracks = SortedIntMap()
    new Sequencer(tracks)
  }

  /** New Sequencer using the passed patterns */
  def apply(tracks: SortedIntMap[Track]): Sequencer =
    new Sequencer(tracks)

  /** A sequencer with default tracks */
  def initialized: Validated[NEL[Error], Sequencer] = {

    import SequencerSyntax._

    val seq = Sequencer()

    (1 to 2)
      .flatMap(i => midiint(i).toOption)
      .foldLeft(valid[NEL[Error], Sequencer](seq)) { (vs, v) =>
        if (v.value == 1) {
          vs.andThen(_.addTrack[DrumTrack](v.value, v))
        } else {
          vs.andThen(_.addTrack[SynthTrack](v.value, v))
        }
      }

  }

}
