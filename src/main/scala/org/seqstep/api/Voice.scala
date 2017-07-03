package org.seqstep.api

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Voice {
  val steps: SortedIntMap[Step]
}

final case class SynthVoice(
  steps: SortedIntMap[NoteStep]
) extends Voice

final case class DrumVoice(
  note:   Note,
  octave: Octave,
  steps:  SortedIntMap[DrumStep]
) extends Voice

final case class RenderedVoice(steps: SortedIntMap[Vector[RenderedStep]])


/** Voice rendering type class allows rendering control steps before playback.
  *
  * Adds note off and does other relevant transformations like unifying contiguous full open notes.
  *
  */
trait VoiceRenderer[T <: Voice] {
  
  // For each step, generate a corresponding note off step.
  // The purpose of doing it once and not directly as needed is to have the computation done
  // on a single place and not later during playback.
  
  def render(c: T): RenderedVoice
  
}

object VoiceRenderer {
  
  def render[T <: Voice : VoiceRenderer](c: T): RenderedVoice =
    implicitly[VoiceRenderer[T]].render(c)
  
  implicit val drumVoiceRenderer = new VoiceRenderer[DrumVoice] {
    override def render(c: DrumVoice): RenderedVoice = {
      val steps = c.steps.map { case (idx, step) =>
        val on  = RenderedNoteStep(c.note, c.octave, step.velocity)
        val off = RenderedNoteOffStep(c.note,c.octave)
        val onOffSteps  = Vector(on , off)
        (idx, onOffSteps)
      }
      RenderedVoice(steps)
    }
  }
  
  implicit val synthVoiceRenderer = new VoiceRenderer[SynthVoice] {
    override def render(c: SynthVoice): RenderedVoice = {
      val ss = c.steps.flatMap { case (idx, step) =>
        // new events, with their index
        val nix = idx + step.duration
        val on  = RenderedNoteStep(step.note, step.octave, step.velocity)
        val off = RenderedNoteOffStep(step.note, step.octave)
        Vector(idx -> on, nix -> off)
          .groupBy(_._1)
          .map{ case (k, v) => k -> v.map{ case (_, x) => x } }
          
      }
      RenderedVoice(ss)
    }
  }
  
}



