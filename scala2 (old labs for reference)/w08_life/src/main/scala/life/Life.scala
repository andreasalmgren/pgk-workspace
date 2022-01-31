 package life

case class Life(cells: Matrix[Boolean]) {

  /** Ger true om cellen på plats (row, col) är vid liv annars false.
    * Ger false om indexeringen är utanför universums gränser.
    */
  def apply(row: Int, col: Int): Boolean = {
    // (cells.data(row)(col) && row < Main.dim._1 && row >= 0 && col < Main.dim._2 && col >= 0)
    if (row >= 0 && row < Main.dim._1 && col >= 0 && col < Main.dim._2) cells(row,col) else false
     
  }
  /** Sätter status på cellen på plats (row, col) till value. */
  def updated(row: Int, col: Int, value: Boolean): Life = Life(cells.updated(row, col)(value))

  /** Växlar status på cellen på plats (row, col). */
  def toggled(row: Int, col: Int): Life = {
    if (cells.data(row)(col)) Life(cells.updated(row,col)(false))
    else Life(cells.updated(row,col)(true))
  }

   // Life(cells.updated(row,col)(!cells.data(row)(col)))


  /** Räknar antalet levande grannar till cellen i (row, col). */
 def nbrOfNeighbours(row: Int, col: Int): Int = {
    var neighbours = 0 
    for (i <- row-1 to row+1) {
                      if ( i >= 0 && i < Main.dim._1) {
                        for (j <- col-1 to col+1) {
                          if (j >= 0 && j < Main.dim._2) {
                            if ((i,j) != (row,col))
                              if (cells.data(i)(j)) neighbours += 1
                          }
                        }
                      }
                    }
    neighbours
  }

  

  /** Skapar en ny Life-instans med nästa generation av universum.
    * Detta sker genom att applicera funktionen \code{rule} på cellerna.
    */
  def evolved(rule: (Int, Int, Life) => Boolean = Life.defaultRule): Life = {
    var nextGeneration = Life.empty(cells.dim)
    cells.foreachIndex { (r,c) => nextGeneration = nextGeneration.updated(r,c, rule(r,c, this))
    }
    nextGeneration
  }

  /** Radseparerad text där 0 är levande cell och - är död cell. */
  override def toString = cells.data.map(_.mkString(" ")).mkString("\n").replace("false", "-").replace("true", "0")

  
}

object Life {
  /** Skapar ett universum med döda celler. */
  def empty(dim: (Int, Int)): Life = Life(Matrix.fill(dim._1, dim._2)(false))

  /** Skapar ett unviversum med slumpmässigt liv. */
  def random(dim: (Int, Int)): Life = {
    var randomLife = Life.empty(dim)
      for(i <- 0 to dim._1 - 1){
        for(j <- 0 to dim._2 - 1) randomLife = randomLife.updated(i,j, math.random < 0.5)
      }
      randomLife
  } 

  /** Implementerar reglerna enligt Conways Game of Life. */
    
  // if (nbrOfNeighbours(row,col) == 2 || nbrOfNeighbours(row,col) == 3) true
  
  
 // }
  def defaultRule(row: Int, col: Int, current: Life): Boolean = {
    if (current(row,col)) (current.nbrOfNeighbours(row,col) == 2 || current.nbrOfNeighbours(row,col) == 3)
    else (current.nbrOfNeighbours(row,col) == 3)


  /*
   val currentNeighbours = current.nbrOfNeighbours(row,col)
   val conditionOfCell = current.cells(row,col)
   var lifestatus = false
   if (status && (neighbours == 3 || neighbours == 2)) {
     lifestatus = true
   }
   else if (status == false && neighbours == 3) {
     lifestatus = true
   }
   lifestatus
*/
  } 

}
