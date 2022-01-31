package imageproject

import cslib.images._
import java.awt._



class GreyScaleFilter(name: String, args: Int) extends ImageFilter(name, args) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val out: Array[Array[Color]] = colors
    val intensity: Array[Array[Short]] = computeIntensity(out)
    for (i <- 0 until colors.length; j <- 0 until colors(i).length) {
      val temp: Int = intensity(i)(j)
      out(i)(j) = new Color(temp, temp, temp)
    }
    out
  }

}
