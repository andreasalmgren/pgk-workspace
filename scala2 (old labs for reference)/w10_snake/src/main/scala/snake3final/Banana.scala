package snake

  class Banana(val game:SnakeGame) extends CanTeleport {
    
    override def draw(): Unit = {
      game.drawBlock(pos.x, pos.y, Colors.Banana)
      game.drawBlock(pos.x, pos.y + 1, Colors.Banana)
      game.drawBlock(pos.x, pos.y + 2, Colors.Banana)
    }

    override def isOccupyingBlockAt(p: Pos): Boolean = {
      (p.x == pos.x && p.y==pos.y) || (p.x==pos.x && p.y==pos.y+1) || (p.x==pos.x && p.y==pos.y+2)
    }

    override def erase(): Unit = { 
        game.eraseBlock(pos.x, pos.y)
        game.eraseBlock(pos.x, pos.y + 1)
        game.eraseBlock(pos.x, pos.y + 2)
    }

    override def reset(): Unit = {
      nbrOfStepsSinceLastTeleport = 0
      pos = Pos.random(Dim(game.dim._1,game.dim._2-2))
    }

    val teleportAfterSteps: Int = 400

  }