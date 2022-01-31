/* package blockbattle

object Worm {
     def nextRandomPos(): Pos = {
        import scala.util.Random.nextInt
        val x = nextInt(window.windowSize._1)
        val y = nextInt(BlockWindow.window.windowSize._2 - 7) + 7

    } 

    var pos = nextRandomPos()

    def isHere(p:Pos): Boolean = pos == p

    def draw(): Unit = pixelWindow.setBlock(pos, Color.worm)
    def erase(): Unit = window.setBlock(pos, Color.soil)

    val teleportProbability = 0.02

    def randomTeleport(notHere: Pos): Unit = 
    if (math.random() < Worm.teleportProbability) {
        erase()
        do pos = nextRandomPos() while (pos == notHere)
        draw ()
    }

} 
*/