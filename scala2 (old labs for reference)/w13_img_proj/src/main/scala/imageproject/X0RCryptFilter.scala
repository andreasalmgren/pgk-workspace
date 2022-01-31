package imageproject

import scala.util.Random
import java.awt._
import cslib.images._



class XORCryptFilter(var1: String, var2: Int) extends ImageFilter(var1, var2) {

  override def apply(colors: Array[Array[Color]], doubles: Array[Double]): Array[Array[Color]] = {
    val out: Array[Array[Color]] = colors
    val seed: Int = Math.round(doubles(0)).toInt
    val rnd: Random = new Random(seed)
    

    for (i <- 0 until colors.length; j <- 0 until colors(i).length) {
      val rndNbr1: Int = Math.abs(rnd.nextInt())
      val rndNbr2: Int = Math.abs(rnd.nextInt())
      val rndNbr3: Int = Math.abs(rnd.nextInt())
       
      val red: Int = ((out(i)(j).getRed ^ rndNbr1) % 256)
      val green: Int = ((out(i)(j).getGreen ^ rndNbr2) % 256)
      val blue: Int = ((out(i)(j).getBlue ^ rndNbr3) % 256)
      out(i)(j) = new Color(red, green, blue)
    }
    out
  }

}
