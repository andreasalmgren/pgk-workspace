package cards

class Deck private (val initCards: Vector[Card]):
  private var cards: Array[Card] = initCards.toArray

  def reset(): Unit = cards = initCards.toArray
  def apply(i: Int): Card = cards(i)
  def toVector: Vector[Card] = cards.toVector
  override def toString: String = cards.mkString(" ")

  def peek(n: Int): Vector[Card] = cards.take(n).toVector

  def remove(n: Int): Vector[Card] =
    val init = peek(n)
    cards = cards.drop(n)
    init

  def prepend(moreCards: Card*): Unit = cards = moreCards.toArray ++ cards

  /** Swaps cards at position a and b. */
  def swap(a: Int, b: Int): Unit = {
    val temp = cards(a)
    cards(a) = cards(b)
    cards(b) = temp
  }

  /** Randomly reorders the cards in this deck. */
  def shuffle(): Unit = {
    for (i <- 0 to (cards.length-1)){  // for loop med alla kort i leken, -1 för index
      val n = cards.length -1 -i // positionen för alla kort baklänges
      val r = util.Random.nextInt(n+1) // slumpmässigt tal (kort) av alla kort vi har (från 0 till cardslength n+1)
      swap(n, r) //byter plats på korten
    }
  }



object Deck:
  def empty: Deck = new Deck(Vector())
  def apply(cards: Seq[Card]): Deck = new Deck(cards.toVector)

  /** Creates a new full Deck with 52 cards in rank and suit order. */
  def full(): Deck = {
    val fullArray = new Array[Card](52)
    var index = 0
    for(i <- Card.suitRange){ // Range = 1 to 4
      for (j <- Card.rankRange) { // Range = 1 to 13
        fullArray(index) = Card(j, i); index += 1  
      } 
    }
    new Deck(fullArray.toVector)
  }