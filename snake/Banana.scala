package snake

class Banana(using ctx: SnakeGame, settings: Settings) extends CanTeleport:
    def shape() = Vector(pos, pos + North, pos + South)
    
    override def draw(): Unit = {
      ctx.drawBlock(pos.x, pos.y, Colors.Yellow)
      ctx.drawBlock(pos.x, (pos + North).y, Colors.Yellow)
      ctx.drawBlock(pos.x, (pos + South).y, Colors.Yellow)
    }
    override def erase(): Unit = { 
      ctx.eraseBlock(pos.x, pos.y)
      ctx.eraseBlock(pos.x, (pos + North).y)
      ctx.eraseBlock(pos.x, (pos + South).y)
    }
    override def isOccupyingBlockAt(p: snake.Pos): Boolean = { 
    (p.x == pos.x && p.y == pos.y)           || 
    (p.x == pos.x && p.y == (pos + North).y) || 
    (p.x == pos.x && p.y == (pos + South).y)
    }
    override def teleport(): snake.Pos = { ctx.randomFreePos() }
    override def teleportAfterSteps: Int = { 500 } // kanske ska använda teleportAfterStepRange i Settings här