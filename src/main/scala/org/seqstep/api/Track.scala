package org.seqstep.api

import eu.timepit.refined._
import monocle.macros.Lenses

/** Created by f on 19/5/17.
  */
sealed trait Track {

  val midiChannel: MIDIValue

  val patterns: SortedIntMap[Pattern]

  //
  // hardcoded until I implement measures (16 = 4x4).
  // FIXME should be a member of pattern
  //
  val stepLength: StepLength = refineMV[StepLengthRange](16)

}

@Lenses
final case class SynthTrack(
  midiChannel: MIDIValue,
  patterns:    SortedIntMap[SynthPattern] = SortedIntMap()
) extends Track

@Lenses
final case class DrumTrack(
  note:        Note,
  octave:      Octave,
  midiChannel: MIDIValue,
  patterns:    SortedIntMap[DrumPattern] = SortedIntMap()
) extends Track

trait TrackMaker[T <: Track] {
  def make(midiChannel: MIDIValue): T
}

object TrackMaker {

  def make[T <: Track](midiChannel: MIDIValue)(implicit b: TrackMaker[T]): T =
    b.make(midiChannel)

  implicit val synthTrackMaker: TrackMaker[SynthTrack] =
    (midiChannel: MIDIValue) => SynthTrack(midiChannel)

  implicit val drumTrackMaker: TrackMaker[DrumTrack] =
    (midiChannel: MIDIValue) => DrumTrack(Note.C, DefaultOctave, midiChannel)

}
