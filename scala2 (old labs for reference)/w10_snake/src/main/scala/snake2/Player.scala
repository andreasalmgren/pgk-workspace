package snake

case class Player(
  name: String,
  Left: String, Down: String, Right: String, Up: String, // control keys
  snake: Snake
){
  def handleKey(key: String): Unit = { // mutate snake dir if control key
    key.toUpperCase match {
      case Left  => snake.dir = West
      case Right => snake.dir = East
      case Down  => snake.dir = South
      case Up    => snake.dir = North; println("?")
      case _       =>
    }
  }
}
