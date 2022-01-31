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
        game.eraseBlock(pos.x, pos.y)
    }
    val teleportAfterSteps: Int = 450
  }
