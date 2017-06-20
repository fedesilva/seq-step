package org.seqstep.api

import scala.collection.immutable.SortedMap

/** A sequence of Steps.
  *
  * FIXME make it so that the steps collections is refined to the `stepLength` of the track.
  *
  * Created by f on 18/5/17.
  */
sealed trait Channel {
  val steps: SortedIntMap[Seq[Step]]
}

final case class SynthChannel(
  steps: SortedIntMap[Seq[NoteStep]]
) extends Channel

final case class DrumChannel(
  note:   Note,
  octave: Octave,
  steps:  SortedIntMap[Seq[DrumStep]]
) extends Channel

final case class RenderedChannel(steps: SortedIntMap[Seq[Step]]) extends Channel


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
      val ss = c.steps.map { case (idx, events) =>
        val es = events.flatMap{ d =>
          val off = OffStep(c.note,c.octave)
          Seq(d, off)
        }
        (idx, es)
      }
      RenderedChannel(ss)
    }
  }
  
  implicit val synthChannelRenderer = new ChannelRenderer[SynthChannel] {
    override def render(c: SynthChannel): RenderedChannel = {
      val ss = c.steps.flatMap { case (idx, events) =>
        // new events, with their index
        events.flatMap { n =>
          val nix = idx + n.duration
          Seq(nix -> OffStep(n.note, n.octave), idx -> n)
        }
        .groupBy { case (k, _) => k }
        .map { case (k, v) => k -> v.map { case (_, x) => x } }
        .toSeq
      }
      RenderedChannel(ss)
    }
  }
  
}



