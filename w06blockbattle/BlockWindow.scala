package blockbattle

class BlockWindow(
    val nbrOfBlocks: (Int, Int),
    val title: String = "BLOCK WINDOW",
    val blockSize: Int = 14
):

    import introprog.PixelWindow

    val pixelWindow = new PixelWindow(
        nbrOfBlocks._1 * blockSize, nbrOfBlocks._2 * blockSize, title)

    // ska mha pixelWindow.fill fylla i ett kvadratiskt område med sidan blockSize pixlar 
    // på en position pos i block-kooridnater med en färg color
    def setBlock(pos: Pos, color: java.awt.Color): Unit = {
        pixelWindow.fill(pos._1 * blockSize, pos._2 * blockSize, blockSize, blockSize, color)
    }
    
    // ska mha pixelWindow.getPixel ge färgen för övre vänstar hörnet i blocket på position
    // pos i block-koordinater
    def getBlock(pos: Pos): java.awt.Color = {
        pixelWindow.getPixel(pos._1 * blockSize, pos._2 * blockSize)
    }
    
    def write(
        text: String,
        pos: Pos,
        color: java.awt.Color,
        textSize: Int = blockSize
    ): Unit = pixelWindow.drawText(
        text, pos._1 * blockSize, pos._2 * blockSize, color, textSize)

    def nextEvent(maxWaitMillis: Int = 10): BlockWindow.Event.EventType =
        import BlockWindow.Event._
        pixelWindow.awaitEvent(maxWaitMillis)
        pixelWindow.lastEventType match
            case PixelWindow.Event.KeyPressed => KeyPressed(pixelWindow.lastKey)
            case PixelWindow.Event.WindowClosed => WindowClosed
            case _ => Undefined

object BlockWindow:
    def delay(millis: Int): Unit = Thread.sleep(millis)

    object Event:
        trait EventType
        case class KeyPressed(key: String) extends EventType
        case object WindowClosed extends EventType
        case object Undefined extends EventType