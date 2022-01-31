import blockbattle.*

object Main:

    def main(args: Array[String]) = {
        
        var spel = new Game("LEFT","RIGHT")
            spel.start()
    }
        /**
        val kvadrat = BlockWindow((100,100))
        kvadrat.setBlock(Pos(10,10), java.awt.Color(51, 51, 0))
        
        var quit = false
    
        while(!quit) {

            var event = kvadrat.nextEvent()
        
            event match {
                case BlockWindow.Event.KeyPressed(key) => println(event); BlockWindow.delay(200)
                case BlockWindow.Event.WindowClosed => quit = true ; println(quit)
                case BlockWindow.Event.Undefined => BlockWindow.delay(200)
            }
        }
        **/