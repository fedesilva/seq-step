

val v1 = Vector( 1 -> "a", 2 -> "b")
val v2 = Vector( 1 -> "uno", 2 -> "dos", 3 -> "tres")

val imv: Seq[Vector[(Int, String)]] = Seq(v1, v2)

val a: Seq[(Int, String)] = for {
  vs <- imv
  v <- vs
} yield v

val b = a.groupBy(_._1)

val c = b.map {
  case (i, pairs) =>
    val ss = pairs.map {
      case (_, s) => s
    }
    i -> ss
}