package org.seqstep.api

import scala.collection.immutable.Iterable


case class Pattern(tracks: SortedIntMap[Track]) {
  
  def allSteps: Iterable[Step] = {
    for {
      (it,  t)  <- tracks
      (ic,  c)  <- t.channels
      (is,  s)  <-  c.steps
    } yield s
  }
  
}
