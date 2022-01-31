package imageproject

import java.awt._
import cslib.images._

class BlueFilter(name: String, args: Int) extends ImageFilter(name, args) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val out: Array[Array[Color]] = Array.ofDim[Color](colors.length, colors(0).length)
    for (i <- 0 until colors.length; j <- 0 until colors(i).length) {
      out(i)(j) = new Color(0, 0, colors(i)(j).getBlue)
    }
    out
  }

}

