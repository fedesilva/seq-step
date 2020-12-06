package org.seqstep.api

import monocle.macros.Lenses

sealed trait Step

@Lenses
final case class SynthStep(
  note: Note,
  octave: Octave,
  duration: Int,
  velocity: MIDIValue
) extends Step

@Lenses
final case class DrumStep(
  instrument: Int,
  velocity: MIDIValue
) extends Step
