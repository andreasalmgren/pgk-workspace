package snake

  class Apple(val game:SnakeGame) extends CanTeleport {
  
    override def draw(): Unit = {
      game.drawBlock(pos.x, pos.y, Colors.Apple)
    }

    override def isOccupyingBlockAt(p: Pos): Boolean = {
      p == pos
    }

    def eat(snake: Snake): Unit = {
      if(pos == snake.body.head)
        update()
    }

    override def erase(): Unit = {
      if(isOccupyingBlockAt(pos))
        game.drawBlock(pos.x, pos.y, Colors.Background)
    }
    val teleportAfterSteps: Int = 200
  }
