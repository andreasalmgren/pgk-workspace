package snake

class Snake (
  val initPos: Pos,
  val initDir: Dir,
  val headColor: java.awt.Color,
  val tailColor: java.awt.Color,
  val game: SnakeGame
) extends CanMove {
  var dir: Dir = initDir

  val initBody: List[Pos] = List(initPos + initDir, initPos)

  val body: scala.collection.mutable.Buffer[Pos] = initBody.toBuffer

  val initTailSize: Int = 10 // välj själv vad som är lagom svårt

  var nbrOfStepsSinceReset = 0
  val growEvery = 10
  var points=0
  // val startGrowingAfter = 400
  // var nbrOfApples = 0

  def reset(): Unit = {
    // återställ starttillstånd, ge rätt svanslängd
    body.clear() 
    body ++= initBody
  }

  def grow(): Unit = {
    // väx i rätt riktning med extra svansposition
    val nextPos = body.head+dir
    nextPos +=: body
  } 
   // krymp svansen om kroppslängden är större än 2
  def shrink(): Unit = if(body.size>2) body-= body.last

  // kolla om p finns i kroppen
  def isOccupyingBlockAt(p: Pos): Boolean = body.contains(p)

  def isHeadCollision(other: Snake): Boolean = this.body.head == other.body.head    // kolla om huvudena krockar
  def isTailCollision(other: Snake): Boolean = other.body.tail.contains(this.body.head) // mitt huvud i annans svans

  def fruitCollision(e: Entity): Unit = {
    e match{
      case a:Apple => { 
        if (body.head == a.pos){
          points += 1
          a.reset()
          println(points)
        }
      } 
      case b: Banana => { 
        if(b.isOccupyingBlockAt(body.head)) { grow() 
        b.reset()
        }
      }
      case _ =>
    }
  }

  // väx och krymp enl. regler; action om äter frukt
  def move(): Unit = {
    this.grow
    this.shrink
    
    nbrOfStepsSinceReset += 1
    if(nbrOfStepsSinceReset > growEvery){
      this.grow
      nbrOfStepsSinceReset = 0
    }
    game.entities.foreach(i => fruitCollision(i))
  } 

  def draw(): Unit = {
    game.drawBlock(body.head.x, body.head.y, headColor)
    body.tail.foreach(p => game.drawBlock(p.x, p.y, tailColor))
  }

  def erase(): Unit = {
      //body.foreach(p => game.eraseBlock(p.x, p.y))
    for(i <- body.indices) {
      game.eraseBlock(body(i).x, body(i).y)
    }
  }
  override def toString =  // bra vid println-debugging
    body.map(p => (p.x, p.y)).mkString(">:)", "~", s" going $dir")
}
