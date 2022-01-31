package imageproject

import cslib.images._
import java.awt._


class InvertFilter(name: String, args: Int) extends ImageFilter(name, args) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val out: Array[Array[Color]] = colors
    for (i <- 0 until colors.length; j <- 0 until colors(i).length) {
      out(i)(j) = new Color(255 - out(i)(j).getRed, 255 - out(i)(j).getGreen, 255 - out(i)(j).getBlue)
    }
    out
  }

}
