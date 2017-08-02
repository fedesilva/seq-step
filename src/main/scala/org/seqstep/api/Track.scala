package org.seqstep.api

import eu.timepit.refined._
import monocle.macros.Lenses

import scala.collection.immutable.SortedMap


/**
  * Created by f on 19/5/17.
  */
sealed trait Track {
  
  val midiChannel: MIDIValue
  
  val steps: SortedIntMap[Step]
  
  // hardcoded until I implement measures (16 = 4x4).
  // FIXME should be a member of pattern
  val stepLength : StepLength = refineMV[StepLengthRange](16)
  
}

@Lenses
final case class SynthTrack(
  midiChannel: MIDIValue,
  steps: SortedIntMap[SynthStep] = SortedIntMap()
) extends Track

@Lenses
final case class DrumTrack(
  note: Note,
  octave: Octave,
  midiChannel: MIDIValue,
  steps: SortedIntMap[DrumStep] = SortedIntMap()
) extends Track


trait TrackMaker[T <: Track] {
  def make(midiChannel: MIDIValue): T
}

object TrackMaker {
  
  def make[T <: Track](midiChannel: MIDIValue)(implicit b: TrackMaker[T]): T = b.make(midiChannel)
  
  implicit val synthTrackBuilder = new TrackMaker[SynthTrack] {
    override def make(midiChannel: MIDIValue): SynthTrack =
      SynthTrack(refineMV[MIDIRange](1))
  }
  
  implicit val drumTrackBuilder = new TrackMaker[DrumTrack] {
    override def make(midiChannel: MIDIValue): DrumTrack =
      DrumTrack(Note.C, DefaultOctave, refineMV[MIDIRange](1))
  }
  
}


