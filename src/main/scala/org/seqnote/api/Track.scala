package org.seqnote.api

import eu.timepit.refined.refineMV
import org.seqnote.api.Refinements.{MIDIValue, StepLength, StepLengthRange}

/**
  * Created by f on 19/5/17.
  */
sealed trait Track[T <: Channel[_]] {
  
  val channel:    MIDIValue
  val stepLength: StepLength
  val channels:   IndexedSeq[T]
  
}

final case class SynthTrack(
  channel:    MIDIValue,
  stepLength: StepLength = refineMV[StepLengthRange](16),
  channels:   IndexedSeq[SynthChannel]
) extends Track[SynthChannel]

final case class DrumTrack(
  channel:    MIDIValue,
  stepLength: StepLength = refineMV[StepLengthRange](16),
  channels:   IndexedSeq[DrumChannel]
) extends Track[DrumChannel]



