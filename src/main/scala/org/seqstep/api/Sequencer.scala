package org.seqstep.api


import monocle.macros.Lenses
import monocle.function.At.at


import scala.util.Try

/** Top level container of patterns.
  *
  * @param patterns patterns this sequencer holds.
  *
  */
@Lenses
case class Sequencer(patterns: SortedIntMap[Pattern])

object Sequencer {
  
  /** New empty sequencer */
  def apply(): Sequencer = {
    val ps  = SortedIntMap()
    new Sequencer(ps)
  }
  
  /** New Sequencer using the passed patterns */
  def apply(patterns: SortedIntMap[Pattern]): Sequencer = new Sequencer(patterns)
  
  /** A sequencer with default tracks */
  def initialized: Sequencer = {

    val seq = Sequencer()
    (1 to 2).flatMap( i => midiint(i).toOption ).foldLeft(seq){ (s, v) =>
      if (v.value > 1) {
        s.addTrack[SynthTrack](0,v).fold( x =>{println(x); s }, identity)
      }
      else {
        s.addTrack[DrumTrack](0, v).fold( x =>{println(x); s }, identity)
      }
    }
  }
  
  /** Syntax methods for manipulating sequencer instances */
  implicit class Syntax(seq: Sequencer) {
   
    
    def addTrack[T <: Track: TrackMaker]
                (trIndex: Int, midiChannel: MIDIValue): Either[String, Sequencer] = {
  
      Try {
        val t       = TrackMaker.make(midiChannel)
//        val trackL  = Sequencer.tracks ^|-> at(trIndex)
//        trackL.set(t.some)(seq)
      }
      .toEither.left.map(_.getMessage)
      ???
    }
    
    def addStep[T <: Step]
               (trIdx: Int, ptIdx: Int, stepIdx: Int, step: T ): Either[String, Sequencer] = {
      
      
      
      ???
      
    }
    
  }
  
}


