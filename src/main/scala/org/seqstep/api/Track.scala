package org.seqstep.api

import eu.timepit.refined._
import monocle.macros.Lenses

/**
  * Created by f on 19/5/17.
  */
sealed trait Track {
  
  val midiChannel: MIDIValue
  
  val patterns: SortedIntMap[Pattern]
  
  //
  // hardcoded until I implement measures (16 = 4x4).
  // FIXME should be a member of pattern
  //
  val stepLength : StepLength = refineMV[StepLengthRange](16)
  
}

@Lenses
final case class SynthTrack(
  midiChannel: MIDIValue,
  patterns: SortedIntMap[SynthPattern] = SortedIntMap()
) extends Track

@Lenses
final case class DrumTrack(
  note: Note,
  octave: Octave,
  midiChannel: MIDIValue,
  patterns: SortedIntMap[DrumPattern] = SortedIntMap()
) extends Track


trait TrackMaker[T <: Track] {
  def make(midiChannel: MIDIValue): T
}

object TrackMaker {
  
  def make[T <: Track]
          (midiChannel: MIDIValue)(implicit b: TrackMaker[T]): T =
            b.make(midiChannel)
  
  implicit val synthTrackBuilder = new TrackMaker[SynthTrack] {
    override def make(midiChannel: MIDIValue): SynthTrack =
      SynthTrack(midiChannel)
  }
  
  implicit val drumTrackBuilder = new TrackMaker[DrumTrack] {
    override def make(midiChannel: MIDIValue): DrumTrack =
      DrumTrack(Note.C, DefaultOctave, midiChannel)
  }
  
}


