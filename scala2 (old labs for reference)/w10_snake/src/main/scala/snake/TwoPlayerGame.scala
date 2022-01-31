package snake

class TwoPlayerGame extends SnakeGame("TwoPlayerGame"){  // ska ärva SnakeGame

  // ormar och ev. äpple, bananer etc
    val snakeA= new Snake(initPos= Pos(dim._1/3,dim._2/2,Dim(dim)),
                           initDir= East,
                           headColor=Colors.Green,
                           tailColor= Colors.DarkGreen,
                           game=this
                        )
    val snakeB= new Snake(initPos= Pos(dim._12/3,dim._2/2,Dim(dim)),
                           initDir= South,
                           headColor=Colors.Blue,
                           tailColor= Colors.DarkBlue,
                           game=this
                        )

    val playerA= new Player("Player A",
                            left="Left",down= "Down", right="Right", up="Up",
                            snake=snakeA
                            )
    val playerB= new Player("Player B",
                            left="a",down= "s", right="d", up="w",
                            snake=snakeB
                            )

    // lägg till äpplen och bananer
//    val banana= new Banana(this)

  def play(playerNames: String): Unit ={
    // ska överskugga play i SnakeGame

      players :+= playerA
      players :+= playerB

      //entities :+= banana

      startGameLoop()

  }

  def isGameOver():Boolean={
    //println(snakeA)
    false
  }

}