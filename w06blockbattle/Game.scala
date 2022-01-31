package blockbattle

import java.awt.Color as JColor

object Game:
    val windowSize = (30, 50)
    val windowTitle = "EPIC BLOCK BATTLE"
    val blockSize = 14
    val skyRange = 0 to 7
    val grassRange = 8 to 8
    object Color { 
        val black = new JColor( 0, 0, 0)
        val mole  = new JColor( 51, 51, 0)
        val soil  = new JColor( 153, 102, 51)
        val tunnel= new JColor( 204, 153, 102)
        val grass = new JColor(25, 130, 35)
        val sky   = new JColor(30, 200, 255)
    }
    /** Used with the different ranges and eraseBlocks */
    def backgroundColorAtDepth(y: Int): java.awt.Color = ???

class Game(
    val leftPlayerName: String = "LEFT",
    val rightPlayerName: String = "RIGHT"
):
    import Game._ // direkt tillgång till namn på medlemmar i kompanjon

    val window = new BlockWindow((30, 50), "BLOCKBATTLE", 10)
    val leftMole: Mole = new Mole("LEFT", Pos(5, 25), (1, 0), Color.mole, KeyControl(right = "d", left = "a", up = "w", down = "s")) 
    val rightMole: Mole = new Mole("RIGHT", Pos(25, 25), (-1, 0), Color.mole, KeyControl(right = "Right", left = "Left", up = "Up", down = "Down"))
    
    def rectangle(leftTop: Pos)(size: (Int, Int))(color: JColor): Unit =
        for (y <- leftTop._2  to size._2) 
            for (x <- leftTop._1 to size._1) 
                window.setBlock(Pos(x, y), color)

    def drawWorld(): Unit = 
        rectangle(Pos(0, 0))(size = (30, 7))(Color.sky)
        rectangle(Pos(0, 7))(size = (30, 8))(Color.grass)
        rectangle(Pos(0, 8))(size = (30, 50))(Color.soil)
        window.setBlock(leftMole.pos, Color.mole)
        window.setBlock(rightMole.pos, Color.black)


    /** Use to erase old points, e.g updated score */

    def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = 
        var points = ???

    var points = 0
    def update(mole: Mole): Unit = 

        if mole.nextPos._1 > 29 then mole.reverseDir()
        if mole.nextPos._1 < 0 then mole.reverseDir()
        if mole.nextPos._2 > 49 then mole.reverseDir()
        if mole.nextPos._2 < 7 then mole.reverseDir()
        
        
        if window.getBlock(mole.nextPos) == Color.soil then mole.points += 1
        if window.getBlock(mole.nextPos) == Color.grass then mole.points -= 1
        
        window.setBlock(mole.nextPos, Color.mole) // draw new
        window.setBlock(mole.pos, Color.tunnel) // erase old
        mole.move() // update // update, draw new, erase old
        
        window.write(s"■■", Pos(12, 1), Color.sky, 20)
        val xdelayMillis = 80
        window.write(s"leftMole ${leftMole.points}", Pos(1, 1), JColor.black, 20)

        window.write(s"■■", Pos(27, 1), Color.sky, 20)
        val ydelayMillis = 80
        window.write(s"rightMole ${rightMole.points}", Pos(15, 1), JColor.black, 20)

    var quit = false
    val delayMillis = 80

    def handleEvents(): Unit = {
        var e = window.nextEvent()
        while (e != BlockWindow.Event.Undefined) {
            e match {
                case BlockWindow.Event.KeyPressed(key) =>
                leftMole.setDir(key)
                rightMole.setDir(key) // ändra riktning på resp. mullvad (OLIKA VERSIONER)
                
                case BlockWindow.Event.WindowClosed =>
                quit == true // avsluta spel-loopen (OLIKA VERSIONER)
            }
            e = window.nextEvent()
        }
    }

    def gameLoop(): Unit = {

        var quit = false

        while(!quit) {
            val t0 = System.currentTimeMillis
            handleEvents() // ändrar riktning vid tangenttryck etc.
            update(leftMole) // flyttar, ritar, suddar, etc.
            update(rightMole)

            if leftMole.points == 50 then 
                window.write("leftMole wins!!! Game over rightMole", Pos(10, 10), JColor.black, 20)
                quit = true 
        
            
            if rightMole.points == 50 then 
                window.write("rightMole wins!!! Game over leftMole", Pos(10, 10), JColor.black, 20)
                quit = true

            val elapsedMillis = (System.currentTimeMillis - t0).toInt
            Thread.sleep((delayMillis - elapsedMillis) max 0)
        }
    }

    def start(): Unit =
        println("Start digging!")
        println(s"$leftPlayerName ${leftMole.keyControl}")
        println(s"$rightPlayerName ${rightMole.keyControl}")
        drawWorld()
        gameLoop()