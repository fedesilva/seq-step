package org.seqstep.api

import monocle.macros.Lenses

sealed trait Pattern {
  val steps: SortedIntMap[Step]
}

@Lenses
final case class SynthPattern(
  steps: SortedIntMap[SynthStep]
) extends Pattern

@Lenses
final case class DrumPattern(
  steps: SortedIntMap[DrumStep]
) extends Pattern
