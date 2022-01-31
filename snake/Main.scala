package snake

def createTwoPlayerGame(): Unit = 
  Settings.default.windowTitle = "Snake: Two Player"
  TwoPlayerGame().play()

@main
def run: Unit =
  val buttons = 
    Seq("Two", "Cancel", "DON'T PRESS")
  val selected = 
    introprog.Dialog.select("Number of players?", buttons, "Snake")
  selected match 
    case "Two"            => createTwoPlayerGame()
    case "DON'T PRESS 42" => println("I told you not to press this button!")
    case  _               => println("Goodbye!")