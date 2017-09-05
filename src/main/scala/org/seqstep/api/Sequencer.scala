package org.seqstep.api


import monocle.macros.Lenses
import monocle.function.At.at

import cats._, cats.data._, cats.implicits._
import Validated.{ valid, invalid }
import cats.data.{ NonEmptyList => NEL }


/** Top level container of patterns.
  *
  * @param tracks patterns this sequencer holds.
  *
  */
@Lenses
case class Sequencer(tracks: SortedIntMap[Track])

object Sequencer {
  
  /** New empty sequencer */
  def apply(): Sequencer = {
    val ps  = SortedIntMap()
    new Sequencer(ps)
  }
  
  /** New Sequencer using the passed patterns */
  def apply(tracks: SortedIntMap[Track]): Sequencer = new Sequencer(tracks)
  
  /** A sequencer with default tracks */
  def initialized: Validated[NEL[Error], Sequencer] = {

    import SequencerSyntax._
    
    val seq = Sequencer()
    
    (1 to 2)
      .flatMap( i => midiint(i).toOption )
      .foldLeft( valid[NEL[Error], Sequencer](seq)) { (vs, v) =>
        if (v.value == 1) {
          vs.andThen(seq => seq.addTrack[SynthTrack](v.value, v))
        }
        else {
          vs.andThen(s => s.addTrack[DrumTrack](v.value, v))
        }
      }
    
  }
  
}


