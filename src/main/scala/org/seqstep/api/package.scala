package org.seqstep

import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.And
import eu.timepit.refined.numeric.{GreaterEqual, LessEqual}
import eu.timepit.refined._
import eu.timepit.refined.api.Validate.{Plain, constant}
import lucuma.refined._

import monocle.Lens
import monocle.function.At

import scala.collection.immutable.SortedMap

/** Refinement types for values in the api.
  *
  * Notice that ranged types should be split into a range declaration and a the ranged type.
  *
  * This is so it's easier to call the functions and macros that validate runtime values and
  * literals - you use the range declaration to validate and convert into the refined type.
  *
  * Also notice there are functions that use the range to validate, this functions are declared here
  * to avoid having to flood the client code with `refined` imports.
  *
  * Created by f on 19/5/17.
  */
package object api:

  /** A sorted map with int keys */
  type SortedIntMap[T] = SortedMap[Int, T]

  /** Constructor for sorted maps with int keys */
  object SortedIntMap:
    def apply[T](): SortedIntMap[T] = SortedMap[Int, T]()

  /** Allowed values range for midi values: 0 to 127 */
  type MIDIRange = And[GreaterEqual[0], LessEqual[127]]

  /** Midi Value Int with correct range */
  type MIDIValue = Int Refined MIDIRange

  /** Helper to create a midi value from an unrefined int */
  def midiint(i: Int): Either[String, MIDIValue] = refineV[MIDIRange](i)

  /** Allowed values range for midi octaves */
  type OctaveRange = And[GreaterEqual[0], LessEqual[10]]

  /** An octave value is an int within the midi octave range: 0 to 10 */
  type Octave = Int Refined OctaveRange

  /** Helper to create a valid octave value */
  def octave(i: Int): Either[String, Octave] = refineV[OctaveRange](i)

  val DefaultOctave = 1.refined[OctaveRange]

  /** Allowed step length values range for tracks step length */
  type StepLengthRange = And[GreaterEqual[1], LessEqual[256]]

  /** Step length value within defined range: 1 to 256 */
  type StepLength = Int Refined StepLengthRange

  /** Helper to create a valid step length value */
  def stepLength(i: Int): Either[String, Refined[Int, StepLengthRange]] =
    refineV[StepLengthRange](i)

  /** At instance for SortedMap */
  implicit def atSortedMap[K, V]: At[SortedMap[K, V], K, Option[V]] =
    (i: K) =>
      Lens[SortedMap[K, V], Option[V]] { m =>
        m.get(i)
      }(optV => map => optV.fold(map - i)(v => map + (i -> v)))

