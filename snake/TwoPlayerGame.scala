package snake

import introprog.PixelWindow

class TwoPlayerGame extends SnakeGame(Settings.default):  // ska ärva SnakeGame

  given SnakeGame = this

  val playerName1 = introprog.Dialog.input("Choose")
  val playerName2 = introprog.Dialog.input("Choose")
  
  val Player1 = new Player(playerName1, Player.KeyMap.Letters, new Snake(Pos(dim._1/3, dim._2/2, Dim(dim)), North, Colors.Green, Colors.DarkGreen))
  val Player2 = new Player(playerName2, Player.KeyMap.Arrows, new Snake(Pos(dim._1*2/3, dim._2/2, Dim(dim)), South, Colors.Blue, Colors.Yellow))
  val apples  = new Apple()
  val monster = new Monster()
  val banan   = new Banana()

  // def pointDisplay() =
  //   val x = ???
  //   val y = ???
  //   val z = ???
  //   val w = ???
  //   drawTextInMessageArea(s"${Player1}'s Points = ${Player1.points}", x, y, Colors.DarkGreen)
  //   drawTextInMessageArea(s"${Player2}'s Points = ${Player2.points}", x, y, Colors.DarkGreen)



  def isGameOver: Boolean =
    // Player1.points == 50 || Player2.points == 50 // points finns i Player.scala

    Player1.snake.isHeadCollision(Player2.snake) ||
    Player1.snake.isTailCollision(Player2.snake) || 
    Player2.snake.isTailCollision(Player1.snake) ||
    Player1.snake.isTailCollision(Player1.snake) || 
    Player2.snake.isTailCollision(Player2.snake) 

  override def play(playerNames: String*): Unit = // la till override här
    start(Player1, Player2)(Player1.snake, Player2.snake, apples, monster, banan)


