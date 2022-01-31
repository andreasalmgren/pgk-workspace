package imageproject

import java.awt.image.BufferedImage
import java.io.File
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import javax.imageio.ImageIO
import java.util.Scanner

object ImageUI {
  
  val greeting = "Pick one of following pictures by entering a number\n"
  
  /** Accepted file formats */
  val extensions = List("jpeg", "jpg")
  
  val scan = new Scanner(System.in)
  
  /** Lists the images from the images folder */
  var images = List[File]()
  val dir = new File(System.getProperty("user.dir") + "/images")
  if (dir.exists && dir.isDirectory) {
    images = dir.listFiles.filter(_.isFile).toList
    images = images.filter(file => extensions.exists(file.getName.endsWith(_)))
  }
  
  /**
   *  Returns a chosen image from the images folder.
   *  Prints: 
   *
   *  Välj en av följande bilder genom att mata in en siffra
   *
   *  0. boy.jpg
   *  1. car.jpg
   *  2. duck.jpg
   *  3. facade.jpg
   *  4. jay.jpg
   *  5. moon.jpg
   *  6. obidos.jpg
   *  7. sgrada.jpg
   *  8. shuttle.jpg
   *  Ditt val: 
   */
   def getImage: BufferedImage = {
    showFiles
    print("Your pick: ")
    val i = scan.next()
    val result = Try(ImageIO.read(images(i.toInt)))
    result match {
      case Success(_) => 
        println("Picture " + images(i.toInt).getName + " loaded\n")
        result.get
      case Failure(e) => 
        println("Error; wrong index, try again\n")
        getImage
    }
  }
  
  private def showFiles: Unit = {
    println(greeting)
    var i = 0
    for (file <- images) {
      println(s"$i. ${file.getName}")
      i += 1
    }
  }
}