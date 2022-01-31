package snake

class Apple(using ctx: SnakeGame, settings: Settings) extends CanTeleport:

    override def draw(): Unit = { ctx.drawBlock(pos.x, pos.y, Colors.Red) }
    override def erase(): Unit = { ctx.eraseBlock(pos.x, pos.y) }
    override def isOccupyingBlockAt(p: Pos): Boolean = { p == pos }
    override def teleport(): Pos = { ctx.randomFreePos() }
    override def teleportAfterSteps: Int = { settings.apple.teleportAfterSteps } // Int = 450 // guess we can have another count
    
    // def eat(): Unit = { ctx.players.foreach ( a => if a.snake.body.head == pos then erase())
    // Varje gång ett äpple äts upp av en orm så teleporteras äpplet till en ny position och kan ätas igen.
