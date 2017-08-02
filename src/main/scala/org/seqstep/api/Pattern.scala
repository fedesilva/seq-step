package org.seqstep.api

import monocle.macros.Lenses



sealed trait Pattern {
  val tracks: SortedIntMap[Track]
}

@Lenses
final case class SynthPattern(
  tracks: SortedIntMap[SynthTrack]
) extends Pattern

@Lenses
final case class DrumPattern(
  tracks: SortedIntMap[Track]
) extends Pattern


