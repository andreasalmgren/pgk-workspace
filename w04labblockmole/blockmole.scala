package blockmole
import java.awt.Color as JColor

object Color {
    //Skapar olika färger som behövs i övriga moduler

        val black = new JColor(0, 0, 0) 
        val mole = new JColor(51, 51, 0)
        val soil = new JColor(153, 102, 51)
        val tunnel = new JColor(204, 153, 102)
        val grass = new JColor(25, 130, 35)
        val sky = new JColor(0, 0, 255) 
}

object BlockWindow {
    //Har ett introprog.PixelWindow och ritar blockgrafik

    import introprog.PixelWindow

    val windowSize = (30, 50) // (width, height) in number of blocks
    val blockSize = 10 // number of pixels per block

    val window = new PixelWindow(300, 500, "Digging Blockmole")

    type Pos = (Int, Int)

    def block(pos: Pos)(color: JColor = JColor.gray): Unit = {
        val x = pos._1 * blockSize// räkna ut fönstrets x-koordinat i pixlar istf block
        val y = pos._2 * blockSize// räkna ut fönstrets y-koordinat i pixlar istf block 

        window.fill(x, y, blockSize, blockSize, color)
    }

    def rectangle(leftTop: Pos)(size: (Int, Int))(color: JColor = JColor.gray): Unit = 
        for (y <- 0 to size._2 ) 
            for (x <- 0 to size._1) 
                block(x + leftTop._1, y + leftTop._2)(color)

    val maxWaitMillis = 10

    def waitForKey(): String = {
        window.awaitEvent(maxWaitMillis)
        while (window.lastEventType != PixelWindow.Event.KeyPressed) {
            window.awaitEvent(maxWaitMillis) //skip other events
        }
        println(s"Keypressed:" + window.lastKey)
        window.lastKey
    }
            
}

object Mole {
    // Representerar en blockmullvad som kan gräva

    def dig(): Unit = {
        var x = BlockWindow.windowSize._1 / 2
        var y = BlockWindow.windowSize._2 / 2
        var quit = false


        while (!quit) {
            
            BlockWindow.block(x, y)(Color.mole)
            
            val key = BlockWindow.waitForKey()
            

            // the following four lines of code "fixes" colors in the game when the mole changes zone from "underground"
            // "gorund" and "sky"

            if y == 3 then BlockWindow.block(x, y)(Color.sky) // if mole "jumps" into the sky, the color painted after the mole hits ground is blue

            if y >= 6 then BlockWindow.block(x, y)(Color.tunnel)  
            else if y == 5 then BlockWindow.block(x, y)(Color.grass)
            else if y == 4 then BlockWindow.block(x, y)(Color.grass)
            
            // the following three lines are meant to hinder the moles movement outside of the screen. 
            //
            //Something seems wrong here
            //
            if x < 0 then x += 2
            if x > 29 then x = 28
            
            if y < 5 then y = 4
            


            if (key == "w") then y -= 1
            else if (key == "a") x -= 1
            else if (key == "s") y += 1
            else if (key == "d") x += 1
            else if (key == "q") quit = true
        }
    }
}

object Variables {
    //def Sky(sky: Int = 7): Unit
}

object Main {
    def drawWorld(): Unit = {
        BlockWindow.rectangle(0, 0)(size = (30, 3))(Color.sky) //starts drawing at 0,0 and draw from x = 0 to x = 30 and y = 0 to y = 3
        BlockWindow.rectangle(0, 7)(size = (30, 6))(Color.grass)
        BlockWindow.rectangle(0, 8)(size = (30, 46))(Color.soil)
    }

    def main(args: Array[String]): Unit = {
    
        drawWorld()
        Mole.dig()
    }
}
