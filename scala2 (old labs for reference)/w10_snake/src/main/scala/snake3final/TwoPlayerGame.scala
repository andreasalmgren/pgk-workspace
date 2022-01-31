package snake

class TwoPlayerGame extends SnakeGame("TwoPlayerGame"){  // ska ärva SnakeGame

  // ormar och ev. äpple, bananer etc
  val snakeA= new Snake(Pos(dim._1/3,dim._2/2,Dim(dim)), East, Colors.Green, Colors.DarkGreen, this)
  val snakeB= new Snake(Pos(dim._1*2/3,dim._2/2,Dim(dim)), South, Colors.Blue, Colors.DarkBlue,this)
  
    
  // lägg till äpplen och bananer
  val bananas1= Vector.fill(1)(new Banana(this))
  val bananas2= Vector.fill(1)(new Banana(this))
  val apples= Vector.fill(1)(new Apple(this))

  def play(playerNames: String*): Unit ={
    // ska överskugga play i SnakeGame
    
    val A= if(playerNames.length>1) playerNames(0) else "Player A"
    val B= if(playerNames.length>2) playerNames(1) else "Player B"

    val playerA= new Player(A, "L", "Ö", "Ä", "P",snakeA)
    val playerB= new Player(B, "A", "S", "D", "W", snakeB)
    
    players :+= playerA
    players :+= playerB

    entities :+= snakeA
    entities :+= snakeB

    entities ++= bananas1
    entities ++= bananas2
    entities ++= apples

    startGameLoop()

  }  
  
  
  def isGameOver():Boolean={
    // gameover om ormar krockar i varandra alt kör in i sig själva

    // överväg metoder i Snake-klass
    snakeA.isTailCollision(snakeB) || snakeB.isTailCollision(snakeA) || snakeA.isHeadCollision(snakeB) ||
    snakeA.isTailCollision(snakeA) || snakeB.isTailCollision(snakeB)
  }

}
