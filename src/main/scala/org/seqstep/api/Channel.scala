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
  steps:  SortedIntMap[DrumStep]
) extends Channel

final case class RenderedChannel(steps: SortedIntMap[Vector[RenderedStep]])


/** Channel rendering type class allows rendering control steps before playback.
  *
  * Adds note off and does other relevant transformations like unifying contiguous full open notes.
  *
  */
trait ChannelRenderer[T <: Channel] {
  
  // For each step, generate a corresponding note off step.
  // The purpose of doing it once and not directly as needed is to have the computation done
  // on a single place and not later during playback.
  
  def render(c: T): RenderedChannel
  
}

object ChannelRenderer {
  
  def render[T <: Channel : ChannelRenderer](c: T): RenderedChannel =
    implicitly[ChannelRenderer[T]].render(c)
  
  implicit val drumChannelRenderer = new ChannelRenderer[DrumChannel] {
    override def render(c: DrumChannel): RenderedChannel = {
      val ss = c.steps.map { case (idx, step) =>
        val on  = RenderedNoteStep(c.note, c.octave, step.velocity)
        val off = RenderedNoteOffStep(c.note,c.octave)
        val es  = Vector(on , off)
        (idx, es)
      }
      RenderedChannel(ss)
    }
  }
  
  implicit val synthChannelRenderer = new ChannelRenderer[SynthChannel] {
    override def render(c: SynthChannel): RenderedChannel = {
      val ss = c.steps.flatMap { case (idx, step) =>
        // new events, with their index
        val nix = idx + step.duration
        val on  = RenderedNoteStep(step.note, step.octave, step.velocity)
        val off = RenderedNoteOffStep(step.note, step.octave)
        Vector(idx -> on, nix -> off)
          .groupBy(_._1)
          .map{ case (k, v) => k -> v.map{ case (_, x) => x } }
          
      }
      RenderedChannel(ss)
    }
  }
  
}



