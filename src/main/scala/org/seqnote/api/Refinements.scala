package org.seqnote.api

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.And
import eu.timepit.refined.numeric._
import shapeless.Nat._

/** Refinement types for values in the api.
  *
  * Notice that ranged types should be split into a range declaration and a the ranged type.
  *
  * This is so it's easier to call the functions and macros that validate runtime values and literals - you use the
  * range declaration to validate and convert into the refined type.
  *
  * Also notice there are functions that use the range to validate, this functions are declared here to avoid having
  * to flood the client code with `refined` imports.
  *
  *
  * Created by f on 19/5/17.
  *
  */
object Refinements {
  
  
  /** Allowed values range for midi values: 0 to 127  */
  type MIDIRange = And[GreaterEqual[_0], LessEqual[W.`127`.T]]
  
  /** Midi Value Int with correct range */
  type MIDIValue = Int Refined MIDIRange
  
  /** Helper to create a midi value from an unrefined int */
  def midiint(i: Int): Either[String, MIDIValue] = refineV[MIDIRange](i)
  
  /** Allowed values range for midi octaves */
  type OctaveRange = And[GreaterEqual[_0], LessEqual[_10]]
  
  /** An octave value is an int within the midi octave range: 0 to 10 */
  type Octave = Int Refined OctaveRange
  
  /** Helper to create a valid octave value */
  def octave(i:Int): Either[String, Refined[Int, OctaveRange]] = refineV[OctaveRange](i)
  
  /** Allowed step length values range for tracks step length */
  type StepLengthRange = And[GreaterEqual[_1], LessEqual[W.`256`.T]]
  
  /** Step length value within defined range: 1 to 256 */
  type StepLength = Int Refined StepLengthRange
  
  /** Helper to create a valid step length value */
  def stepLength(i: Int): Either[String, Refined[Int, StepLengthRange]] = refineV[StepLengthRange](i)
  
  
  
  
}
