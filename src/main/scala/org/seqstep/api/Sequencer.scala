package org.seqstep.api


import scala.util.Try

/** Top level container of patterns.
  *
  * @param patterns patterns this sequencer holds.
  *
  */
case class Sequencer(patterns: SortedIntMap[Pattern])

object Sequencer {
  
  /** New empty sequencer with an empty pattern */
  def apply(): Sequencer = {
    val p   = Pattern(SortedIntMap())
    val ps  = SortedIntMap() + (0 -> p)
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
                (patIndex: Int, midiChannel: MIDIValue): Either[String, Sequencer] = {
      
      Try(seq.patterns(patIndex)).map { p =>
        val t   = TrackMaker.make(midiChannel)
        val key = if(p.tracks.isEmpty) 1 else p.tracks.keySet.max + 1
        // FIXME EEEEEK, use monocle
        val pt  = p.copy( tracks = p.tracks + (key -> t) )
        val ps  = seq.patterns + (patIndex -> pt)
        seq.copy(patterns = ps)
      }
      .toEither.left.map(_.getMessage)
      
    }
    
  }
  
}


