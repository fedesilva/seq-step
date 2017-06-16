package org.seqnote.api


case class Pattern(tracks: SparseIndexedSeq[Track]) {
  
  def allSteps = {
    for {
      (it,  t)  <- tracks
      (ic,  c)  <- t.channels
      (is,  s)  <-  c.steps
    } yield s
  }
  
}
