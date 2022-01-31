package game

import java.net.URL
import java.util.ArrayList
import java.util.Set
import java.util.HashSet
import java.util.Scanner
import java.util.HashMap

object UserInterface {
  /** Prints the available choices, then reads an integer from the user */
  def readChoice(choices: Array[String]): Int = {
    val scanner = new Scanner(System.in)
    println(" 1. Play game \n 2. View high scores \n 3. View high scores for specific player \n 4. Quit")
    scanner.nextLine().toLowerCase match {
      case "1" => 1
      case "2" => 2
      case "3" => 3
      case "4" => 4
    }
  }

  /** Prints scores in descending order */
  def showHighScores(highscore:  HashMap[String, Integer], scoresArg: ArrayList[Integer], players: ArrayList[String] ): Unit = {

    println("")
    for (i <- 0 until highscore.size) {
    println(s"Name: ${players.get(i)} Score: ${highscore.get(players.get(i))}")
    }
    println("")

   /* var test = -1000
    var index = 0
    var k = 0
    var playerName: String = ""
    var scores: Array[Int] = scoresArg */



    /*for (i <- 0 until highscore.size()) scores = scores.+:(highscore.get(players.get(i)))
    scores.sorted
    println(s" Name: ${} Score: ${scores(highscore.size()-1)}")


    for (i <- 0 until highscore.values().size) scores + highscore
    highscore.values() */


     /* while (k < scores.size) {
        for (i <- 0 until scores.size) {
          if (scores(i) > test) {
            test = scores(i)
            index = i
            scores = scores.sorted.reverse.drop(1)
            scores = scores.:+(0)
            playerName = playerscores.get(test)
          }
        }
        println(s"\n Name: $playerName Score: $test")
        k += 1
      } */

  } 

  /** Prints scores achieved by a specific player in descending order */
  def showHighScores2(highscore: HashMap[String,Integer] , player: String): Unit = {

  println(s"\nName: $player  Score: ${highscore.get(player)} \n")
  }
    


}

