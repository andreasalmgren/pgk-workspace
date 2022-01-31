package nlp

case class Text(source: String):
  lazy val words: Vector[String] = source.map( ch => if (ch.isLetter) then ch else ' ' ).split(' ').filter(_.size != 0).map(_.toLowerCase).toVector // dela upp source i ord // filter(_.size > 0)
  
  lazy val distinct: Vector[String] = words.distinct

  lazy val wordSet: Set[String] = words.toSet

  lazy val wordsOfLength: Map[Int, Set[String]] = wordSet.groupBy(_.length)

  lazy val wordFreq: Map[String, Int] = 
    val freq = FreqMapBuilder(words:_*)
    freq.toMap

  // skapa ordfrekvenstabellen wordFreq genom att registrera ordförekomster med hjälp av FreqMapBuilder. 
  // Tabellen wordFreq ska bestå av nyckelvärdepar w -> f där f är antalet gånger ordet w förekommer i words

  def ngrams(n: Int): Vector[Vector[String]] = words.sliding(n).toVector

  lazy val bigrams: Vector[(String, String)] = ngrams(2).map(xs => (xs(0), xs(1)))

  lazy val followFreq: Map[String, Map[String, Int]] =
    val result = scala.collection.mutable.Map.empty[String, FreqMapBuilder]
    for (key, next) <- bigrams do
      if result.contains(key) /* key finns redan definierad i result */ then result(key).add(next) /* på "platsen" result(key): lägg till next i frekvenstabellen */
      else result += key -> FreqMapBuilder(next) /* lägg till (key -> ny frekvenstabell med next) i result*/
      
    end for
    result.map(p => p._1 -> p._2.toMap).toMap // toMap ger oföränderlig Map

  lazy val follows: Map[String, String] =
    followFreq.map( (key, followMap) => 
      val maxByFreq: (String, Int) = followMap.maxBy(_._2)
      val mostCommonFollower: String = maxByFreq._1
      (key, mostCommonFollower) 
    )
    // eller kortare med samma resultat: (lättare eller svårare att läsa?)
    // followFreq.map((k, v) => k -> v.maxBy(_._2)._1)

object Text:
  def fromFile(fileName: String, encoding: String = "UTF-8"): Text =
    val source = scala.io.Source.fromFile(fileName, encoding)
    val txt = try source.mkString finally source.close()
    Text(txt)
  
  def fromURL(url: String, encoding: String = "UTF-8"): Text =
    val source = scala.io.Source.fromURL(url, encoding)
    val txt = try source.mkString finally source.close()
    Text(txt)