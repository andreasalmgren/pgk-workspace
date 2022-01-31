package snake

object Main {
  def main(args: Array[String]): Unit = {
    val orm = new Snake(Pos.random(Dim(20, 30)), West, Colors.Blue, Colors.Green, null)
    println(orm.body)
    orm.reset
    println(orm.body)
    println(orm)
    val buttons = Seq("One Player","Two Players", "Cancel")
    val selected =
      introprog.Dialog.select("Number of players?", buttons, "Snake")
    selected match {
      case "One Player" => (new OnePlayerGame).play("Green")
      case "Two Players" => (new TwoPlayerGame).play("Green", "Blue")
      case _ => println("Exiting main... Goodbye!")
    }
  }
}