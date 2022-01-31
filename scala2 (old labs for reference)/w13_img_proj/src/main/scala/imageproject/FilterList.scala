package imageproject

import cslib.images._
import cslib.window._
import java.awt.Color
import scala.collection.mutable.ArrayBuffer

class  FilterList {
  var filters = ArrayBuffer[(ImageFilter, Array[Double])]()


    /** Adds a filter to the FilterList */
  def addFilter(filter: ImageFilter, args: Array[Double] = Array(0.0)): Unit = {
    filters += Tuple2(filter, args)
  }


    /** Applies all the filters on the given Image and draws it in a PixelWindow */
  def applyFilter(image: Image, sw: SimpleWindow): Unit = {
    val img: Image = image
    val temp: Array[Double] = Array[Double](0.0)
    for(f <- filters.indices){
      img.updateImage(filters(f)._1.apply(img.getColorMatrix, filters(f)._2))
    }

    sw.drawImage(img.image)
  }
}