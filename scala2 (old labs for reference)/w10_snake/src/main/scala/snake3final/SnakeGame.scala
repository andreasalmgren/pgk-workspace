package snake
import introprog.BlockGame

abstract class SnakeGame(title: String) extends BlockGame(
  title, dim = (50, 30), blockSize = 15, background = Colors.Background,
  framesPerSecond = 50, messageAreaHeight = 3
) {
  var entities: Vector[Entity] = Vector.empty

  var players: Vector[Player] = Vector.empty

  sealed trait State
  case object Starting extends State
  case object Playing  extends State
  case object GameOver extends State
  case object Quitting extends State

  var state: State = Starting

  def enterStartingState(): Unit = {
    // rensa, meddela "tryck space för start"
    clearWindow()
    drawCenteredText("Tryck space för start!", Colors.Blue)
  }

  def enterPlayingState(): Unit = {
    // rensa, rita alla entiteter
    clearWindow()
    entities.foreach(_.draw())
    state=Playing
  }

  def enterGameOverState(): Unit ={
    // meddela "game over"
    drawCenteredText("Game Over!", Colors.Blue)
    state=GameOver
  }

  def enterQuittingState(): Unit = {
    println("Goodbye!")
    pixelWindow.hide()
    state = Quitting
  }

def randomFreePos(): Pos = {
    var p = Pos.random(Dim(dim))
    var q = false
    val boolVector = entities.map(_.isOccupyingBlockAt(p))
    while (!q) {
      if(entities == entities.empty)
        q = true
      for (i <- boolVector.indices) {
        if (boolVector(i))
          p = Pos.random(Dim(dim))
        else q = true
      }
    }
    p
  }
  
  override def onKeyDown(key: String): Unit = {
    println(s"""key "$key" pressed""")
    state match {
      case Starting => if (key == " ") enterPlayingState()
      case Playing => players.foreach(_.handleKey(key))
      case GameOver =>
        if (key == " ") enterPlayingState()
        else if(key == "Escape") enterQuittingState()
      case _ =>
    }
  }

  override def onClose(): Unit = enterQuittingState()

  def isGameOver(): Boolean  // abstrakt metod, implementeras i subklass

  override def gameLoopAction(): Unit = {
    if (state == Playing) {

      // delme after debugging
      //players.foreach(_.snake.erase)
      //players.foreach(_.snake.update)
      //players.foreach(_.snake.draw)

      entities.foreach(_.erase)
      entities.foreach(_.update)
      entities.foreach(_.draw)
      if (isGameOver) enterGameOverState()
    }
  }
    

  def startGameLoop(): Unit = {
    pixelWindow.show()  // möjliggör omstart även om fönstret stängts...
    enterStartingState()
    gameLoop(stopWhen = state == Quitting)
  }

  def play(playerNames: String*): Unit // abstrakt, implementeras i subklass
}
