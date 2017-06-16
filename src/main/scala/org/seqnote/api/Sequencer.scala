package org.seqnote.api

import eu.timepit.refined._

import scala.util.Try


object Sequencer {
  
  def apply() = new Sequencer(SparseIndexedSeq())
  def apply(patterns: SparseIndexedSeq[Pattern]) = new Sequencer(patterns)
  
  implicit class Builder(seq: Sequencer) {
    
    def initialize: Sequencer = {
      val p = Pattern(SparseIndexedSeq())
      seq.copy(patterns = seq.patterns + (seq.patterns.size -> p))
    }
    
    // Fucking have a look at monocle
    def addSynthTrack(patIndex: Int, midiChannel: MIDIValue): Either[String, Sequencer] = {
      Try(seq.patterns(patIndex)).map { p =>
        val t   = SynthTrack(midiChannel)
        val pt  = p.copy( tracks = p.tracks + (p.tracks.size -> t) )
        val ps  = seq.patterns + (patIndex -> pt)
        seq.copy(patterns = ps)
      }
      .toEither.left.map(_.getMessage)
      
    }
  
    def addDrumTrack(patIndex: Int, midiChannel: MIDIValue): Either[String, Sequencer] = {
      Try(seq.patterns(patIndex)).map { p =>
        val t   = DrumTrack(midiChannel)
        val pt  = p.copy( tracks = p.tracks + (p.tracks.size -> t) )
        val ps  = seq.patterns + (patIndex -> pt)
        seq.copy(patterns = ps)
      }
      .toEither.left.map(_.getMessage)
    
    }
    
    
    
  }
  
}

/**
  * Created by f on 16/6/17.
  */
case class Sequencer(patterns: SparseIndexedSeq[Pattern]) {
  
  
  
}
