package org.seqstep

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.And
import eu.timepit.refined.numeric._
import monocle.Lens
import monocle.function.At
import shapeless.Nat._

import scala.collection.immutable.{IntMap, SortedMap}

/** Refinement types for values in the api.
  *
  * Notice that ranged types should be split into a range declaration
  * and a range constrained type.
  *
  * This is so it's easier to call the functions and macros that
  * validate runtime values and literals -
  * you use the range declaration to validate
  * and convert into the refined type.
  *
  * Also notice there are functions that use the range to validate,
  * this functions are declared here to avoid having
  * to flood the client code with `refined` imports.
  *
  * Created by f on 19/5/17.
  *
  */
package object api extends cats.syntax.OptionSyntax {

  /** A sorted map with int keys */
  type SortedIntMap[T] = SortedMap[Int, T]

  /** Constructor for sorted maps with int keys */
  object SortedIntMap {
    def apply[T](): SortedIntMap[T] = SortedMap[Int, T]()
  }

  /** Allowed values range for midi values: 0 to 127  */
  type MIDIRange = GreaterEqual[_0] And LessEqual[W.`127`.T]

  /** Midi Value Int with correct range */
  type MIDIValue = Int Refined MIDIRange

  /** Helper to create a midi value from an unrefined int */
  def midiint(i: Int): Either[String, MIDIValue] = refineV[MIDIRange](i)

  /** Allowed values range for midi octaves */
  type OctaveRange = GreaterEqual[_0] And LessEqual[_10]

  /** An octave value is an int within the midi octave range: 0 to 10 */
  type Octave = Int Refined OctaveRange

  /** Helper to create a valid octave value */
  def octave(i: Int): Either[String, Int Refined OctaveRange] = refineV[OctaveRange](i)

  val DefaultOctave: Int Refined OctaveRange = refineMV[OctaveRange](1)

  /** Allowed step length values range for tracks step length */
  type StepLengthRange = GreaterEqual[_1] And LessEqual[W.`256`.T]

  /** Step length value within defined range: 1 to 256 */
  type StepLength = Int Refined StepLengthRange

  /** Helper to create a valid step length value */
  def stepLength(i: Int): Either[String, StepLength] = refineV[StepLengthRange](i)

  trait Error {2
    val description: String
  }

  final case class GenericError(description: String) extends Error

}
