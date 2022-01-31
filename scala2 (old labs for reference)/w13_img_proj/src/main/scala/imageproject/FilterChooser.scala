package imageproject

import java.util.Scanner
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.awt.Color
import cslib.images._

/** Creates a FilterChooser with all the available filters */
class FilterChooser(filters: Array[ImageFilter]) {

/** Shows which filters are available and lets the user choose filters
* until an escape sequence has been given and returns a FilterList which
* contain the chosen filters
* Example:
* 0. för Blått-filter
* 2. för Kontrast-filter
* 3. för Gauss-filter
* 4. för Sobel-filter
* 5. om du inte vill ha fler filter
*/

  def chooseFilter(): FilterList = {
    val out: FilterList = new FilterList()

    val scanner: Scanner = new Scanner(System.in)

    var choosing = true

    val optOut = filters.length.toString

    while(choosing) {

      for(f <- filters.indices) {
        var arguments = ""
        if(filters(f).getNumberOfArguments > 0) arguments = s"(require ${filters(f).getNumberOfArguments} arguments)"

        println(s"$f. for ${filters(f).getName} $arguments")
      }
      println(s"${filters.length}. if you don't want to pick more filters ")

      scanner.nextLine().split(' ').toVector match {
        case Vector(`optOut`) => choosing = false
        case option +: args if (option.union(args).mkString(" ").matches("([\\d](\\p{Space})?)+")) => {
          if (option.toInt < filters.length) {
            if (args.length == filters(option.toInt).getNumberOfArguments) {
              out.addFilter(filters(option.toInt), args.map(x => x.toDouble).toArray)
            } else println("Invalid input, try again!")
          }
          else println("Invalid input, try again!")
        }
        case Vector(_*) => println("Invalid input, try again!")
      }
    }
      out
  }

}