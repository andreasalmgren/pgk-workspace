package life

object Main:
  val help = """
 #####################################
 ##### WELCOME TO GAME OF LIFE! ######
 #####################################
 #                                   #
 #     *Click on cell to toggle*     #
 #                                   #
 #  Press enter for next generation  #
 #  Press space to toggle play/stop  #
 #  Press r to create random life    #
 #  Press backspace to clear life    #
 #  Press p to print life            #
 #  Press s to save life to file     #
 #                                   #
 #      *Close window to EXIT*       #
 #                                   #
 #####################################
 #####  YOU'RE IN IT FOR LIFE.  ######
 #####################################
  """

  val dim = (30, 40)

  def main(args: Array[String]): Unit =
    println(help)
    val game = new LifeWindow(dim._1, dim._2)
    game.start()