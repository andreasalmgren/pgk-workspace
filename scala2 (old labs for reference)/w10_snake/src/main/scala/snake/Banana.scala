package snake

  class Banana(override val game:SnakeGame) extends CanTeleport {


    override def draw(): Unit = {
      if(pos.y < game.dim._2 && pos.y > 0){
        game.drawBlock(pos.x, pos.y - 1, Colors.Banana)
        game.drawBlock(pos.x, pos.y, Colors.Banana)
        game.drawBlock(pos.x, pos.y + 1, Colors.Banana)
      }
    }

    def eat(snake: Snake): Unit = {
      if(isOccupyingBlockAt(snake.body.head))
        update()
    }

    override def isOccupyingBlockAt(p: Pos): Boolean = {
      p == pos
    }

    override def erase(): Unit = {
      if(!isOccupyingBlockAt(pos)) {
        game.eraseBlock(pos.x, pos.y - 1)
        game.eraseBlock(pos.x, pos.y)
        game.eraseBlock(pos.x, pos.y + 1)
      }
    }

    val teleportAfterSteps: Int = 15
  }