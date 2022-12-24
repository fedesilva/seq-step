package org.seqstep.api

import eu.timepit.refined._

import scala.collection.immutable.SortedMap
import lucuma.refined._

/** Created by f on 19/5/17.
  */
sealed trait Track:

  val midiChannel: MIDIValue
  val channels:    SortedIntMap[Channel]

  // hardcoded until I implement measures (16 = 4x4).
  val stepLength: StepLength = 16.refined[StepLengthRange]

final case class SynthTrack(
  midiChannel: MIDIValue,
  channels:    SortedIntMap[SynthChannel] = SortedIntMap()
) extends Track

final case class DrumTrack(
  midiChannel: MIDIValue,
  channels:    SortedIntMap[DrumChannel] = Track.defaultDrumChannels()
) extends Track

object Track:

  /** Setup default drum channels.
    *
    * Channels in drum machines are instruments, and have fixed notes each. Using the first eight
    * semitones of the default octave is a common setup.
    */
  def defaultDrumChannels(count: Int = 8): SortedIntMap[DrumChannel] =
    val channels =
      Note.values
        .take(count)
        .zipWithIndex
        .map { case (n, i) =>
          i -> DrumChannel(n, DefaultOctave, steps = SortedIntMap())
        }

    SortedMap[Int, DrumChannel](channels: _*)

trait TrackMaker[T <: Track]:
  def make(midiChannel: MIDIValue): T

object TrackMaker:

  def make[T <: Track : TrackMaker](midiChannel: MIDIValue) : T = 
    implicitly[TrackMaker[T]].make(midiChannel)

  given TrackMaker[SynthTrack] with
      override def make(midiChannel: MIDIValue): SynthTrack = SynthTrack(1.refined[MIDIRange])

  given TrackMaker[DrumTrack] with
    override def make(midiChannel: MIDIValue): DrumTrack = DrumTrack(1.refined[MIDIRange])
