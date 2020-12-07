package org.seqstep.api

import cats.data.Validated.{invalid, valid}
import cats.data.{NonEmptyList => NEL, _}
import monocle.function.At._

import scala.util.Try

object SequencerSyntax {

  /** Syntax methods for manipulating sequencer instances */
  implicit class Syntax(seq: Sequencer) {

    def addTrack[T <: Track: TrackMaker](
      trIndex: Int,
      midiChannel: MIDIValue
    ): Validated[NEL[Error], Sequencer] = {

      Try {
        val t = TrackMaker.make(midiChannel)
        val trackL = Sequencer.tracks composeLens at(trIndex)
        trackL.set(t.some)(seq)
      }.fold(
        t => invalid(NEL.of(GenericError(t.getMessage))),
        seq => valid(seq)
      )

    }

    def addStep[T <: Step](
      trIdx: Int,
      ptIdx: Int,
      stepIdx: Int,
      step: T
    ): Validated[Error, Sequencer] = {
      ???
    }

  }

}
