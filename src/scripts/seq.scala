

import org.seqnote.api._
import Sequencer.Builder

val seq = (1 to 2).flatMap( i => midiint(i).toOption).foldLeft(Sequencer().initialize){ (s, v) =>
  if (v.value > 1) {
    s.addSynthTrack(0,v).fold( _=>s, identity)
  }
  else {
    s.addDrumTrack(0, v).fold( _=>s, identity)
  }
}



