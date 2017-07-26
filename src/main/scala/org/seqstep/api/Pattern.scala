package org.seqstep.api

import monocle.macros.Lenses

import scala.collection.immutable.SortedMap

sealed trait Pattern {
  val voices: SortedIntMap[Voice]
}

@Lenses
final case class SynthPattern(voices: SortedIntMap[SynthVoice]) extends Pattern

@Lenses
final case class DrumPattern(voices: SortedIntMap[DrumVoice]) extends Pattern

object DrumPattern {
  
  def initialized = DrumPattern(defaultDrumVoices)
  
  /** Setup default drum voices. */
  def defaultDrumVoices: SortedMap[Int, DrumVoice] = {
    val channels =
      Note.all
        .take(8)
        .zipWithIndex
        .map { case (n, i) =>
          i -> DrumVoice(n, DefaultOctave, steps = SortedIntMap())
        }
    SortedMap[Int, DrumVoice](channels :_*)
  }
  
}
