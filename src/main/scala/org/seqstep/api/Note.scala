package org.seqstep.api

/**
  * Created by f on 18/5/17.
  */
sealed trait Note

object Note {
  
  case object C   extends Note
  case object Db  extends Note
  case object D   extends Note
  case object E   extends Note
  case object Fb  extends Note
  case object F   extends Note
  case object G   extends Note
  case object Ab  extends Note
  case object A   extends Note
  case object Bb  extends Note
  case object B   extends Note
  
  lazy val all: Vector[Note] = Vector(C, Db, D, E, Fb, F, G, Ab, A, Bb, B)
  
}
