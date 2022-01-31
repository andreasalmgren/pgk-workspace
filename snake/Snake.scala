package snake

import java.awt.{Color as Color}


class Snake (
  val initPos: Pos,
  val initDir: Dir,
  val headColor: Color,
  val tailColor: Color,
 )(using ctx: SnakeGame, settings: Settings) extends CanMove: 
  var dir: Dir = initDir
  val initBody: List[Pos] = List(initPos + initDir, initPos)
  val body: scala.collection.mutable.Buffer[Pos] = initBody.toBuffer
  val initLength: Int = settings.snake.initLength
  val growEvery: Int = settings.snake.growEvery
  val startGrowingAfter: Int = settings.snake.startGrowingAfter
  var nbrOfStepsSinceReset = 0
  private var _nbrOfApples = 0
  def nbrOfApples: Int = _nbrOfApples
  private var _nbrOfBananas = 0
  def nbrOfBananas: Int = _nbrOfBananas

  def reset(): Unit = { // återställ starttillstånd, ge rätt svanslängd
    body.clear()
    body.appendAll(initBody) // sätter body till initial body
    dir = initDir    
  }

  def grow(): Unit = // väx i rätt riktning med extra svansposition
    body.prepend(body.head + dir)

  def shrink(): Unit = // krymp svansen om kroppslängden är större än 2
    if body.size > 2 then body -= body.last

  def isOccupyingBlockAt(p: Pos): Boolean = body.contains(p) // kolla om p finns i kroppen

  def isHeadCollision(other: Snake): Boolean = // kolla om huvudena krockar
    body.head == other.body.head 

  def isTailCollision(other: Snake): Boolean = // mitt huvud i annans svans
    other.body.tail.contains(body.head)

  private var _isEatenByMonster: Boolean = false
  def isEatenByMonster: Boolean = _isEatenByMonster
  def eatenByMonster(): Unit = _isEatenByMonster = true

  def move(): Unit =
    grow()
    shrink()

    // If the head of the snake is at an apple's position, then grow
    // väx och krymp enl. regler
    // åtgärder om äter frukt eller blir uppäten av monster
    val applePositions = ctx.entities.collect { case a: Apple => a.pos }
    val apples =  ctx.entities.collect { case a: Apple => a }
    if applePositions.contains(body.head) then 
      grow()  
      apples.foreach{ a => a.reset()}
      _nbrOfApples += 1
      if nbrOfApples == 1 then 
        ctx.drawCenteredText(s"                        Points = ${nbrOfApples}", Colors.DarkGreen)
        ctx.enterGameOverState()

    var monsterPosition = ctx.entities.collect { case a: Monster => a.shape() }
    if monsterPosition(0).contains(body(0)) || 
    monsterPosition(0).contains(body(1)) then 
      ctx.enterGameOverState()

    var bananPosition = ctx.entities.collect { case a: Banana => a.shape() }
    val bananas =  ctx.entities.collect { case a: Banana => a }
    if bananPosition(0).contains(body(0)) || bananPosition(0).contains(body(1)) then
      grow()
      grow()  
      grow()    
      bananas.foreach{ a => a.reset()
      _nbrOfBananas += 1
      if nbrOfBananas == 1 then 
        ctx.drawCenteredText(s"                        Points = ${nbrOfBananas}", Colors.DarkGreen)
        ctx.enterGameOverState()
      }

    nbrOfStepsSinceReset += 10
    if(nbrOfStepsSinceReset > growEvery){
      grow()
      nbrOfStepsSinceReset = 0
    }

  override def toString = // bra vid println-debugging
    body.map(p => (p.x, p.y)).mkString(">:)", "~", s" going $dir")

  def draw(): Unit = 
    ctx.drawBlock(body.head.x, body.head.y, headColor) // colors head with headColor
    body.tail.foreach(pos => ctx.drawBlock(pos.x, pos.y, tailColor)) // colors tail with tailColor

  def erase(): Unit = {
      //body.foreach(p => game.eraseBlock(p.x, p.y))
    for(i <- body.indices) {
      ctx.eraseBlock(body(i).x, body(i).y)
    }
  }