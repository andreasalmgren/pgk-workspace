package snake 

import scala.util.Random.nextInt as random

class Monster(using ctx: SnakeGame, settings: Settings) extends CanMove:
    val initPos = ctx.randomFreePos()
    var pos     = initPos
    val color   = settings.monster.color
    val x       = random(4)+1
    val dir     = {  
         x  match{
         case 1 => North 
         case 2 => South
         case 3 => East
         case 4 => West
         }
    }
    def shape() = Vector(pos, pos + North + East, pos + North + West, pos + South + East, pos + South + West)

    override def move(): Unit = { pos = pos + dir }
    override def draw(): Unit = {
        ctx.drawBlock(pos.x, pos.y, color)
        ctx.drawBlock((pos + North + East).x, (pos + North + East).y, color)
        ctx.drawBlock((pos + North + West).x, (pos + North + West).y, color)
        ctx.drawBlock((pos + South + East).x, (pos + South + East).y, color)
        ctx.drawBlock((pos + South + West).x, (pos + South + West).y, color) 
    }
    override def erase(): Unit = {
        ctx.eraseBlock(pos.x, pos.y)
        ctx.eraseBlock((pos + North + East).x, (pos + North + East).y)
        ctx.eraseBlock((pos + North + West).x, (pos + North + West).y)
        ctx.eraseBlock((pos + South + East).x, (pos + South + East).y)
        ctx.eraseBlock((pos + South + West).x, (pos + South + West).y) 
    }
    override def isOccupyingBlockAt(p: snake.Pos): Boolean = { 
    (p.x == pos.x                  && p.y == pos.y)                  || 
    (p.x == (pos + North + East).x && p.y == (pos + North + East).y) || 
    (p.x == (pos + North + West).x && p.y == (pos + North + West).y) ||
    (p.x == (pos + South + East).x && p.y == (pos + South + East).y) ||
    (p.x == (pos + South + West).x && p.y == (pos + South + West).y)
    }
    override def reset(): Unit = 
      erase()
      pos = initPos
      draw()    