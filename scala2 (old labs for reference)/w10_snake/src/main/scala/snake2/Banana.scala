/* package snake

  class Banana(val game:SnakeGame) extends CanTeleport {
    
    override def draw(): Unit = {
      if(pos.y < game.dim._2 && pos.y > 1){
        game.drawBlock(pos.x, pos.y - 1, Colors.Banana)
        game.drawBlock(pos.x, pos.y, Colors.Banana)
        game.drawBlock(pos.x, pos.y + 1, Colors.Banana)
      }
    }

    /* def eat(snake: Snake): Unit = {
      if(isOccupyingBlockAt(snake.body.head))
        update()
    } */

    override def isOccupyingBlockAt(p: Pos): Boolean = {
      (p == pos) || (p.x == pos.x && p.y - 1 == pos.y - 1) || (p.x == pos.x && p.y + 1 == pos.y + 1)
    }

    override def erase(): Unit = { 
    if(pos.y < game.dim._2 && pos.y > 0){
      if(isOccupyingBlockAt(pos)) {
        game.eraseBlock(pos.x, pos.y - 1)
        game.eraseBlock(pos.x, pos.y)
        game.eraseBlock(pos.x, pos.y + 1)
      }
    }
    }
    val teleportAfterSteps: Int = 300
  } */


package snake

  class Banana(val game:SnakeGame) extends CanTeleport {
    
    override def draw(): Unit = {
      while(pos.y+2 > game.dim._2) pos= game.randomFreePos()
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

    val teleportAfterSteps: Int = 300000
  }