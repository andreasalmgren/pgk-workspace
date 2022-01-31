package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0,
      // TODO: count points when e.g. eating apple
):

  def handleKey(key: String): Unit = // om key ingår i keyMap så uppdatera snake.dir
    if keyMap.dir.contains(key) && snake.dir != keyMap.reverseDir(key) then 
      snake.dir = keyMap.dir(key)
  
 

object Player:
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South)
    val reverseDir = Map(left -> East, right -> West, up -> South, down -> North)
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")
