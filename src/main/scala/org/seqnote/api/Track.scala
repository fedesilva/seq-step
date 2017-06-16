package org.seqnote.api

import eu.timepit.refined.refineMV

import scala.collection.immutable.SortedMap


object Track {
  
  def defaultDrumChannels: SparseIndexedSeq[DrumChannel] = {
    val channels =
      Note.all
        .take(8)
        .zipWithIndex
        .map { case (n, i) =>
          i -> DrumChannel(n, DefaultOctave, steps = SparseIndexedSeq())
        }
    SortedMap[Int, DrumChannel](channels :_*)
  }
      
      
  
}



/**
  * Created by f on 19/5/17.
  */
sealed trait Track {
  
  val midiChannel: MIDIValue
  val channels: SparseIndexedSeq[Channel]
  
  // hardcoded until I implement measures (16 = 4x4).
  val stepLength : StepLength = refineMV[StepLengthRange](16)
  
}

final case class SynthTrack(
  midiChannel: MIDIValue,
  channels: SparseIndexedSeq[SynthChannel] = SparseIndexedSeq()
) extends Track

final case class DrumTrack(
  midiChannel: MIDIValue,
  channels: SparseIndexedSeq[DrumChannel] = Track.defaultDrumChannels
) extends Track



