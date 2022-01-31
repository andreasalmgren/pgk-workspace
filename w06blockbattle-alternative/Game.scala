package blockbattle
import java.awt.{Color => JColor}

object Game {
    val windowSize = (30,50)
    val windowTitle = "BLOCK BATTLE COOL COOL"
    val blockSize  = 14
    val skyRange = 0 to 7
    val grassRange = 8 to 10

    object Color { 

        val black = new JColor(0, 0, 0)
        val mole = new JColor(51, 51, 0)
        val soil = new JColor(153, 102, 51)
        val tunnel = new JColor(204, 153, 102)
        val grass = new JColor(25, 130, 35)
        val worm = new JColor(230, 100, 250)
        val sky = new JColor(30, 200, 255)

    }
   // def backgroundColorAtDepth(y: Int): java.awt.Color = ??

}

class Game(
    val leftPlayerName: String = "LEFT",
    val rightPlayerName: String = "RIGHT"
)   {
    import Game._

    val window = new BlockWindow(windowSize, windowTitle, blockSize)
    var quit = false
    val delayMillis = 80
    val keyControlLeft = new KeyControl("a","d","w","s")
    val keyControlRight = new KeyControl("Key.Left","Key.Right","Key.Up","Key.Down")
    val leftMole = new Mole(leftPlayerName, blockbattle.Pos(windowSize._1 / 3, windowSize._2/2), (-1,0), Color.mole, keyControlLeft)
    val rightMole = new Mole(rightPlayerName, blockbattle.Pos(windowSize._1 * 2/3, windowSize._2/2), (1,0), Color.black, keyControlRight)

     def rectangle(leftTop: Pos)(rightBottom: Pos)(color: JColor = Color.grass) = {
        for (y <- leftTop.y to rightBottom.y ) {
            for (x <- leftTop.x to rightBottom.x ) {
            window.setBlock(Pos(x,y), color)
            }
        }
    } 

    def showPoints = ???

    def countPoints(mole: Mole): String = {
    var nextBlock = Color.black
    if(mole.nextPos.y < windowSize._2 && mole.nextPos.x < windowSize._1 && mole.nextPos.x < 0) nextBlock = window.getBlock(mole.nextPos)
    if (nextBlock == Color.soil) (mole.points = mole.points + 10).toString()
    else "a"
}

    def gameOver(): Unit = { 
        if (leftMole.points >= 1000 || rightMole.points >= 1000) quit = true
        else quit = false
    }

 

    def drawWorld(): Unit = {
    rectangle(Pos(0, 0))(Pos(30, 7))(Color.sky) 
    rectangle(Pos(0, 7))(Pos(30, 10))(Color.grass)
    rectangle(Pos(0, 10))(Pos(30, 50))(Color.soil)
    window.setBlock(leftMole.pos, leftMole.color)
    window.setBlock(rightMole.pos, rightMole.color)


  
    }

    def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = ???

    def update(mole: Mole): Unit = {


        while (!quit) {
            window.setBlock(leftMole.pos, leftMole.color)
            window.setBlock(rightMole.pos, rightMole.color)
            val key = BlockWindow.waitForKey()
        if (key == keyControlLeft.left) 
        if (mole.nextPos.x == 0) mole.reverseDir
        if (mole.nextPos.x == windowSize._1) mole.reverseDir
        if (mole.nextPos.y == 0) mole.reverseDir
        if (mole.nextPos.y == windowSize._2 - skyRange.length) mole.reverseDir
        }
    }

    

    def handleEvents(): Unit = {
        var e = window.nextEvent()
        while (e != BlockWindow.Event.Undefined) {
            e match {
                case BlockWindow.Event.KeyPressed(key) => leftMole.setDir(key)
                case BlockWindow.Event.WindowClosed => quit = true; println("quit")

            }
            e = window.nextEvent()
        }
    }


    def gameLoop(): Unit = { 
        while (!quit) {
            val t0 = System.currentTimeMillis
            handleEvents()
            update(leftMole)
            update(rightMole)
            countPoints(leftMole)
            countPoints(rightMole)

            val elapsedMillis = (System.currentTimeMillis - t0).toInt
            Thread.sleep((delayMillis - elapsedMillis) max 0)
        }
    }

    def start(): Unit = {
    println("Start digging!")
    println(s"$leftPlayerName ${leftMole.keyControl}")
    println(s"$rightPlayerName ${rightMole.keyControl}")
    drawWorld()
    gameLoop()
    }
}
