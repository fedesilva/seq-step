package org.seqstep.api

import monocle.macros.Lenses

import scala.collection.immutable
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



/** Pattern rendering type class allows rendering control steps before playback.
  *
  * Adds note off and does other relevant transformations like unifying contiguous full open notes.
  *
  */
trait PatternRenderer[T <: Pattern] {
  
  // For each step, generate a corresponding note off step.
  // The purpose of doing it once and not directly as needed is to have the computation done
  // on a single place and not later during playback.
  
  def render(c: T): PatternRenderer.RenderedVoice
  
}

object PatternRenderer {
  
  final case class RenderedVoice(steps: SortedIntMap[Vector[RenderedStep]])
  
  def render[T <: Pattern : PatternRenderer](c: T): RenderedVoice =
    implicitly[PatternRenderer[T]].render(c)
  
  
  implicit val drumPatternRenderer = new PatternRenderer[DrumPattern] {
    
    override def render(c: DrumPattern): RenderedVoice = {
      
      val steps = for {
        (_, v)  <- c.voices
        (is, s) <- v.steps
      } yield {
        
        val on  = RenderedNoteStep(v.note, v.octave, s.velocity)
        val off = RenderedNoteOffStep(v.note, v.octave)
        val onOffSteps = Vector(on, off)
        (is, onOffSteps)
      }
      
      RenderedVoice(steps)
      
    }
  }
  
  implicit val synthPatternRenderer = new PatternRenderer[SynthPattern] {
    
    override def render(c: SynthPattern): RenderedVoice = {
      
      // Get the on and off note steps
      val pairs = (for {
        (_, v)      <- c.voices
        (idx, step) <- v.steps
      } yield {
        val nix = idx + step.duration
        val on  = RenderedNoteStep(step.note, step.octave, step.velocity)
        val off = RenderedNoteOffStep(step.note, step.octave)
        Vector(idx -> on, nix -> off)
      }).flatten
      
      // Group the index -> note pairs by index and
      val ss =
        pairs
          .toVector
          .groupBy(_._1)
          .map {
            case (i, ps) =>
              val ss = ps.map {
                case (_, s) => s
              }
              i -> ss
          }
  
      RenderedVoice(SortedMap(ss.toSeq: _*))
      
    }
    
  }
  
}

