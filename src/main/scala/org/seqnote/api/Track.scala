package org.seqnote.api

import eu.timepit.refined.refineMV
import org.seqnote.api.Refinements.{MIDIValue, StepLength, StepLengthRange}

/**
  * Created by f on 19/5/17.
  */
sealed trait Track[T <: Pattern[_]] {
  
  val channel:    MIDIValue
  val stepLength: StepLength
  val patterns:   IndexedSeq[T]
  
}

final case class SynthTrack(
  channel:    MIDIValue,
  stepLength: StepLength = refineMV[StepLengthRange](16),
  patterns:   IndexedSeq[SynthPattern]
) extends Track[SynthPattern]

final case class DrumTrack(
  channel:    MIDIValue,
  stepLength: StepLength = refineMV[StepLengthRange](16),
  patterns:   IndexedSeq[DrumPattern]
) extends Track[DrumPattern]



