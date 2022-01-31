package life

import introprog.PixelWindow
import introprog.PixelWindow.Event
import introprog.Dialog
import introprog.IO

object LifeWindow:
  val EventMaxWait = 1 // milliseconds
  var NextGenerationDelay = 200 // milliseconds
  val alive = new java.awt.Color(242, 128, 161)
  val dead = new java.awt.Color(0, 0, 0)
  val grey = new java.awt.Color(153, 153, 153)
  val title: String = "Game of Life"

class LifeWindow(rows: Int, cols: Int):
  import LifeWindow._
  
  var life = Life.empty(rows, cols)
  val cellSize: Int = 30
  val window: PixelWindow = new PixelWindow(
    cols * cellSize , rows * cellSize, title
  )
  var quit = false
  var play = false

  def drawGrid(): Unit =
    for x <- (0 to cols) do window.line(x * cellSize, 0, x * cellSize, cellSize * rows, grey) 
    for y <- (0 to rows) do window.line(0, y * cellSize, cellSize * cols, y * cellSize, grey)

  def drawCell(row: Int, col: Int): Unit =
    if life.cells(row,col) then window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, alive)
    else window.fill(col*cellSize+1, row*cellSize+1, cellSize-1, cellSize-1, dead)

  def update(newLife: Life): Unit =
    val oldLife = life
    life = newLife
    life.cells.foreachIndex{ (r,c) => drawCell(r,c) }

  def handleKey(key: String): Unit =
    key match
      case "Enter" => update(life.evolved())
      case " " => play = !play
      case "r" => update(Life.random(rows, cols))
      case "Backspace" => update(Life.empty(Main.dim))
      case "p" => println(life.toString)
      case "s" => introprog.IO.saveString(text = life.toString, fileName = (introprog.Dialog.file("Save Life", startDir = life.toString)))
      case _ => ()

  def handleClick(pos: (Int, Int)): Unit =
    life = life.toggled(pos._2 / cellSize, pos._1 / cellSize) ; update(life)

  def loopUntilQuit(): Unit = while !quit do
    val t0 = System.currentTimeMillis

    if play then update(life.evolved())

    window.awaitEvent(EventMaxWait)
    while window.lastEventType != PixelWindow.Event.Undefined do
      window.lastEventType match
        case Event.KeyPressed  =>  handleKey(window.lastKey)
        case Event.MousePressed => handleClick(window.lastMousePos)
        case Event.WindowClosed => quit = true
        case _ =>
      window.awaitEvent(EventMaxWait)
    
    val elapsed = System.currentTimeMillis - t0

    Thread.sleep((NextGenerationDelay - elapsed) max 0)

  def start(): Unit = { drawGrid(); loopUntilQuit() }