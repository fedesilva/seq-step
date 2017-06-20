package org.seqnote.api

import eu.timepit.refined._

import scala.collection.immutable.SortedMap

/**
  * Created by f on 19/5/17.
  */
sealed trait Track {
  
  val midiChannel: MIDIValue
  val channels: SortedIntMap[Channel]
  
  // hardcoded until I implement measures (16 = 4x4).
  val stepLength : StepLength = refineMV[StepLengthRange](16)
  
}

final case class SynthTrack(
  midiChannel: MIDIValue,
  channels: SortedIntMap[SynthChannel] = SortedIntMap()
) extends Track

final case class DrumTrack(
  midiChannel: MIDIValue,
  channels: SortedIntMap[DrumChannel] = Track.defaultDrumChannels
) extends Track

object Track {

  /** Channels in drum machines are instruments, and have fixed per each.
    *
    * Using the first eight semitones of the default octave is a common setup.
    */
  def defaultDrumChannels: SortedIntMap[DrumChannel] = {
    val channels =
      Note.all
        .take(8)
        .zipWithIndex
        .map { case (n, i) =>
          i -> DrumChannel(n, DefaultOctave, steps = SortedIntMap())
        }
    SortedMap[Int, DrumChannel](channels :_*)
  }
  
  object TrackMaker {
    def make[T <: Track](midiChannel: MIDIValue)(implicit b: TrackMaker[T]): T = b.make(midiChannel)
  }
  
  trait TrackMaker[T <: Track] {
    def make(midiChannel: MIDIValue): T
  }
  
  implicit val synthTrackBuilder = new TrackMaker[SynthTrack]{
    override def make(midiChannel: MIDIValue): SynthTrack = SynthTrack(refineMV[MIDIRange](1))
  }
  
  implicit val drumTrackBuilder = new TrackMaker[DrumTrack]{
    override def make(midiChannel: MIDIValue): DrumTrack = DrumTrack(refineMV[MIDIRange](1))
  }
  
}

trait RenderTrack {
  
  // Merge all channels, put notes from all channels into one key ([Int,Step])
  // For each step, generate a corresponding note off step.
  // The purpose of doing it once and not directly as needed is to have the computation done
  // on a single place and not later during playback.
  
  
  
}


