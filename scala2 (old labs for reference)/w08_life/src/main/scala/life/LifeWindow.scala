package life

import introprog.PixelWindow
import introprog.PixelWindow.Event
import introprog.Dialog._
import introprog.IO._

object LifeWindow {
  val EventMaxWait = 1 // milliseconds
  var NextGenerationDelay = 200 // milliseconds
  // lägg till fler användbara konstanter här tex färger etc.
  val lifeColor = new java.awt.Color(242,128,161)
  val deadCells = new java.awt.Color(0,0,0)
  val greyLine = new java.awt.Color(153,153,153)
  val title: String = "Game of Life"

}

class LifeWindow(rows: Int, cols: Int){
  import LifeWindow._

  var life = Life.empty(rows, cols)
  val cellSize: Int = 30
  val window: PixelWindow = new PixelWindow(
    cols * cellSize , rows * cellSize, title
  )
  var quit = false
  var play = false

  def drawGrid(): Unit = {
    for (x <- (0 to cols)) window.line(x * cellSize, 0, x * cellSize, cellSize * 40, greyLine)
    for (y <- (0 to rows)) window.line(0, y * cellSize, cellSize * 40, y * cellSize, greyLine)
  } 
  

  def drawCell(row: Int, col: Int): Unit = {
    if (life.cells(row,col)) window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, lifeColor)
    else window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, deadCells)
  }

 

  def update(newLife: Life): Unit = {
    val oldLife = life
    life = newLife
    life.cells.foreachIndex{ (r,c) => drawCell(r,c) }


  }

  def handleKey(key: String): Unit = {
    key match{
      case "Enter" => this.update(life.evolved())
      case " " => play = !play
      case "r" => this.update(Life.random(rows, cols))
      case "Backsteg" => this.update(Life.empty(Main.dim))
      case "k" => println(life.toString)
      case _ => ()
      
    }
  }

  def handleClick(pos: (Int, Int)): Unit = {
    life = life.toggled(pos._2 / cellSize, pos._1 / cellSize) ; update(life) 
  } 

  def loopUntilQuit(): Unit = while (!quit) {
    val t0 = System.currentTimeMillis
    if (play) update(life.evolved())
    window.awaitEvent(EventMaxWait)
    while (window.lastEventType != PixelWindow.Event.Undefined) {
      window.lastEventType match {
        case Event.KeyPressed  =>  handleKey(window.lastKey)
        case Event.MousePressed => handleClick(window.lastMousePos)
        case Event.WindowClosed => quit = true
        case _ =>
      }
      window.awaitEvent(EventMaxWait)
    }
    val elapsed = System.currentTimeMillis - t0
    Thread.sleep((NextGenerationDelay - elapsed) max 0)
  }

  def start(): Unit = {  drawGrid();  loopUntilQuit() }
}




  /* def drawCell(row: Int, col: Int): Unit = {
    window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, lifeColor)
  } */


  /* def drawBlack(row: Int, col: Int): Unit = {
    window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, deadCells)
  } */

  // if (oldLife(r,c) == true) drawCell(r,c) else drawBlack(r,c) }