package game

import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

object UserInterface {
  /** Prints the available choices, then reads an integer from the user */
  def readChoice(choices: Array[String]): Int = {
    val scanner = new Scanner(System.in)
    println("1. Play game \n 2. View high scores \n 
    3. View high scores for specific player \n 4. Quit")
    scanner.nextLine().toLowerCase match {
      case "1" => 1
      case "2" => 2
      case "3" => 3
      case "4" => 4



  }

  /** Reads a string from the user */
  def readString(): String = ???

  /** Prints scores in descending order */
  def showHighScores(scores: java.util.ArrayList[Game]): Unit = ???

  /** Prints scores achieved by a specific player in descending order */
  def showHighScores(scores: java.util.ArrayList[Game], player: String): Unit = ???

    
  def newGame = {
    val scanner = new Scanner(System.in)
    println("Pick a new picture? (Y/n)")
    scanner.nextLine().toLowerCase match {
      case "y" => main(Array(""))
      case "n" => System.exit(0)
    }

}

