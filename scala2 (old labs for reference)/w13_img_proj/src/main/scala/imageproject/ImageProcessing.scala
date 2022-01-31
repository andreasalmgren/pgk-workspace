package imageproject

import java.util.Scanner
import cslib.images._
import cslib.window._

object ImageProcessing {
  def main(args: Array[String]): Unit = {
    val img = new Image(ImageUI.getImage)
    val filters: Array[ImageFilter] = Array(
      new BlueFilter("Blue filter",0),
      new InvertFilter("Invert filter",0),
      new GreyScaleFilter("Greyscale filter",0),
      new XORCryptFilter("XORCrypt filter", 1),
      new GaussFilter("Gauss filter", 1),
      new SobelFilter("Sobel filter", 1) 
    )
    val fc = new FilterChooser(filters)
    val fl = fc.chooseFilter()
    val sw = new SimpleWindow(img.width, img.height, "ImageFilter")
    fl.applyFilter(img, sw)

    val scanner = new Scanner(System.in)
    println("Pick a new picture? (Y/n)")
    scanner.nextLine().toLowerCase match {
      case "y" => main(Array(""))
      case "n" => System.exit(0)
    }
  }
} 