package imageproject

import java.awt._
import cslib.images._

class SobelFilter(name: String, args: Int) extends ImageFilter(name, args) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val outPixels: Array[Array[Color]] = Array.ofDim[Color](colors.length, colors(0).length)

    for (i <- 0 until colors.length; j <- 0 until colors(0).length) outPixels(i)(j) = new Color(255, 255, 255)

    val intensity: Array[Array[Short]] = computeIntensity(colors)
    val x_sobel: Array[Array[Short]] = Array(Array(-1, 0, 1), Array(-2, 0, 2), Array(-1, 0, 1))
    val y_sobel: Array[Array[Short]] = Array(Array(-1, -2, -1), Array(0, 0, 0), Array(1, 2, 1))

    for (i <- 1 until intensity.length - 1; j <- 1 until intensity(i).length - 1) {
      val x: Int = Math.abs(convolve(intensity, i, j, x_sobel, 1))
      val y: Int = Math.abs(convolve(intensity, i, j, y_sobel, 1))
      outPixels(i)(j) = {
        if ((x + y) > doubles(0)) new Color(0, 0, 0)
        else new Color(255, 255, 255)
      }
    }
    outPixels
  }

} 
