package snake
import java.awt.Color
import scala.io.Source

class Settings(configs: Map[String, String]):
  def getOrElse[T](key: String, default: T)(using p: Settings.Parser[T]): T = 
    configs.get(key).flatMap(p.fromString).getOrElse(default)

  var windowTitle: String          = getOrElse("windowTitel", "Snake")
  var windowSize: (Int, Int)       = getOrElse("windowSize", (50,30))
  var blockSize: Int               = getOrElse("blockSize", 30)
  var background: Color            = getOrElse("background", Colors.Black) 
  var framesPerSecond: Int         = getOrElse("framesPerSecond", 50)
  var messageAreaHeight: Int       = getOrElse("messageAreaHeight", 3)
  var messageAreaBackground: Color = getOrElse("messageAreaBackground", Colors.DarkGray)
  
  object onePlayer:
    val applesNeededToWin: Int  = getOrElse("onePlayer.applesNeededToWin", 5) 
  
  object apple:
    val color: Color            = getOrElse("apple.color", Colors.Red)
    val teleportAfterSteps: Int = getOrElse("apple.teleportAfterSteps", 500)
  
  object banana:
    val color: Color = getOrElse("banana.color", Colors.Yellow)
    val teleportAfterStepRange: (Int, Int) = 
      getOrElse("banana.teleportAfterStepRange", (500, 1000))
  
  object snake:
    val initLength: Int        = getOrElse("snake.initLength", 18)
    val growEvery: Int         = getOrElse("snake.growEvery", 200)
    val startGrowingAfter: Int = getOrElse("snake.startGrowingAfter", 400)

  object monster: 
    val color: Color = getOrElse("monster.color", Colors.Pink)

object Settings:
  def configsFromFile(): Map[String, String] = Map() // TODO: read from file
  val lines = Source.fromFile("snake.txt").getLines.toVector
  val splitLines = lines.map(x => x.filter(f => f != ' ').split(",")).filter(p => p.length == 2)
  val tempMap = scala.collection.mutable.Map.empty[String, String]
  for x <- splitLines do 
    if (!(tempMap.contains(x(0))))
      tempMap += x(0) -> x(1)
    tempMap.toMap
  given default: Settings = Settings(configsFromFile())

  trait Parser[T]:
    def fromString(value: String): Option[T]

  object Parser:
    given intParser: Parser[Int] with
      def fromString(value: String): Option[Int] = value.toIntOption   

    given stringParser: Parser[String] with
      def fromString(value: String): Option[String] = Some(value)   

    given intPairParser: Parser[(Int, Int)] with
      def fromString(value: String): Option[(Int, Int)] = Option(value(0).toInt, value(1).toInt)

    given colorParser: Parser[Color] with 
      def fromString(value: String): Option[Color] = Option(Colors.colorMap(value))