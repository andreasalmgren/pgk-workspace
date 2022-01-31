package snake

class OnePlayerGame extends SnakeGame("OnePlayerGame") { // ska ärva SnakeGame
  val snake = new Snake(Pos(dim._1/3, dim._2/2, Dim(dim)), East, Colors.Green, Colors.DarkGreen, this)

  val apple = new Apple(this)
  // val apples = Vector.fill(1)(new Apple(this))
  //val bananas = Vector.fill(1)(new Banana(this))
  // orm, äpple, ev. bananer etc
  
  
  def isGameOver(): Boolean = snake.isTailCollision(snake)

  def play(playerNames: String*): Unit = { // ska överskugga play i SnakeGame

    val playerName = if(playerNames.length>1) playerNames(0) else "Player"

    val player = new Player(playerName, "L", "Ö", "Ä", "P", snake)

    players :+= player

    if((entities.contains(apple)) == false) { 
      entities :+= apple }
    
    //entities ++= bananas

    entities :+= snake

    startGameLoop() 
  }
}