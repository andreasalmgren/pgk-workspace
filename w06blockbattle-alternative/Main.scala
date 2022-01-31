package blockbattle






object Main{
    
    /* val kvadrat = new BlockWindow((100,100),"BLOCK WINDOW", 14)
    var quit = false
    var event = kvadrat.nextEvent()
    while(!quit) {
        var event = kvadrat.nextEvent()
        event match {
            case BlockWindow.Event.KeyPressed(key) => println(event); BlockWindow.delay(200)
            case BlockWindow.Event.WindowClosed => quit = true ; println(quit)
            case BlockWindow.Event.Undefined => BlockWindow.delay(200)
        }
    } */

    def main (args: Array[String]): Unit = {
        var spel = new Game("LEFT","RIGHT")
        spel.drawWorld
        spel.start




    }
        
} 

