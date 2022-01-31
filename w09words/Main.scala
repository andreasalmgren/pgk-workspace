package nlp

object Main:
  val defaultUrl = "https://fileadmin.cs.lth.se/pgk/skattkammaron.txt"
  val defaultN = 10

  def top(n: Int, freqMap: Map[String, Int]): Vector[(String, Int)] =
    val mostFreq = freqMap.toVector.sortBy(_._2).reverse
    val topWords = scala.collection.mutable.Map.empty[String, Int]
    for i <- 0 until n do topWords += mostFreq(i)
    topWords.toVector.sortBy(_._2).reverse // du kan slänga på .reverse här om du vill sortera från störst till minst :)

  def report(text: Text, from: String, n: Int): String =
    val longestWordsWithLength =
      top(n, text.distinct.map(w => (w, w.length)).toMap).mkString(", ")
    s"""
    |Källa: $from
    |
    |*** Antal ord: ${text.words.size}
    |
    |*** De $n vanligaste orden och deras frekvens:
    |${top(n, text.wordFreq).mkString(", ")}
    |
    |*** De $n längsta orden och deras längd:
    |$longestWordsWithLength
    """.stripMargin

  def main(args: Array[String]): Unit =
    val location = if args.isEmpty then defaultUrl else args(0)
    val n = if args.length < 2 then defaultN else args(1).toInt
    val text =
      if location.startsWith("http") then Text.fromURL(location)
      else Text.fromFile(location)
      
    println(report(text, location, n))