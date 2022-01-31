package imageproject

import java.awt._
import cslib.images._


class GaussFilter(namn: String, args: Int) extends ImageFilter(namn, args) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val outPixels: Array[Array[Color]] = Array.ofDim[Color](colors.length, colors(0).length)

    for (i <- 0 until colors.length; j <- 0 until colors(0).length) outPixels(i)(j) = new Color(255, 255, 255)

    val k: Array[Array[Short]] = Array(Array(0, 1, 0), Array(1, doubles(0).toShort, 1), Array(0, 1, 0))
    val kSum: Int = k.map(_.sum).sum
    /* val kSum: Int = {
      for ( i <- 0 until 2; j <- 0 until 2) kSum += k(i)(j)
      kSum
    }
    */
    val red: Array[Array[Short]] = Array.ofDim[Short](colors.length, colors(0).length)
    val green: Array[Array[Short]] = Array.ofDim[Short](colors.length, colors(0).length)
    val blue: Array[Array[Short]] = Array.ofDim[Short](colors.length, colors(0).length)

    for (i <- 0 until colors.length; j <- 0 until colors(i).length) {
      red(i)(j) = colors(i)(j).getRed.toShort
      green(i)(j) = colors(i)(j).getGreen.toShort
      blue(i)(j) = colors(i)(j).getBlue.toShort
    }
    for (i <- 1 until colors.length - 1; j <- 1 until colors(i).length - 1) {
      val r: Int = Math.abs(convolve(red, i, j, k, kSum) % 256 )
      val g: Int = Math.abs(convolve(green, i, j, k, kSum) % 256 )
      val b: Int = Math.abs(convolve(blue, i, j, k, kSum) % 256)
      outPixels(i)(j) = new Color(r, g, b)
    }
    outPixels
  }

} 




