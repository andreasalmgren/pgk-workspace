package nlp

class FreqMapBuilder:
  private val register = scala.collection.mutable.Map.empty[String, Int]
  def toMap: Map[String, Int] = register.toMap
  def add(s: String): Unit =
    if !register.contains(s) then (register += (s -> 1))
    else register(s) += 1

object FreqMapBuilder:
  /** Skapa ny FreqMapBuilder och räkna strängarna i xs */
  def apply(xs: String*): FreqMapBuilder =
    val result = new FreqMapBuilder
      xs.foreach(result.add)
      result