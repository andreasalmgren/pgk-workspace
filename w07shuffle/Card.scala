package cards

case class Card(rank: Int, suit: Int):
  import Card._

  require(rankRange.contains(rank), s"rank=$rank, must be in $rankRange") // rank måste vara en del av rankrange
  require(suitRange.contains(suit), s"suit=$suit, must be in $suitRange") // suit måste vara inom suitrange

  val rankString: String = ranks(rank - 1) // ger rang (värde) på index (rank - 1)
  val suitChar:   Char   = suits(suit - 1) // ger valör på index (suit - 1)

  override def toString() = s"$rankString$suitChar " // Om du har ett kort och vill skriva ut dess värde
  
object Card:
  val suitRange: Range = 1 to 4
  val rankRange: Range = 1 to 13
  val suits: Vector[Char] = "♠♥♣♦".toVector
  val ranks: Vector[String] =
    "A" +: ((2 to 10).map(_.toString).toVector ++ Vector("J", "Q", "K")) // skapar ett set med 13 kort. 
    // ((2 to 10).map(_.toString).toVector == Vector[String] = Vector(2, 3, 4, 5, 6, 7, 8, 9, 10)