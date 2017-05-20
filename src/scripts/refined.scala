

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import shapeless.Nat._
import cats._
import cats.implicits._
import eu.timepit.refined.boolean.And

// type MIDIRange = Interval.Open[_0,W.`128`.T]
type MIDIRange = And[GreaterEqual[_0],LessEqual[W.`127`.T]]

type MIDIValue = Int Refined MIDIRange

case class NoteEvent(note: MIDIValue, vel: MIDIValue  )

def midiint(i: Int): Either[String, MIDIValue] = refineV[MIDIRange](i)

val note1 = NoteEvent( refineMV[MIDIRange](64), refineMV[MIDIRange](127) )

val note2E = (midiint(64) |@| midiint(127)).map( (note: MIDIValue, vel: MIDIValue ) => NoteEvent(note, vel) )

val note3E =(midiint(0) |@| midiint(127)).map( (note: MIDIValue, vel: MIDIValue ) => NoteEvent(note, vel) )

val note4EFail = ( midiint(-1) |@| midiint(129)).map( (note: MIDIValue, vel: MIDIValue ) => NoteEvent(note, vel) )