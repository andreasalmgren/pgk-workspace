package bank

import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.util.{Try, Success, Failure}
import scala.io.StdIn.readLine
import java.nio.file.StandardOpenOption

val k = Bank()
// val x = Customer("Andy", 123412312) 
// val h = BankAccount(x) 
// val d = Date.now()

def makeHistory(date: Date, event: BankEvent): Unit =
  val l = io.Source.fromFile("bank_log.txt").getLines.toVector
  val p = HistoryEntry(date, event)
  val x = p.toLogFormat
  val k = HistoryEntry.fromLogFormat(x)
  val d = k.toLogFormat //.toSeq????
  Files.write(Paths.get("bank_log.txt"), d.toString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND)
  Files.write(Paths.get("bank_log.txt"), "\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND)

val menu = """
    - - - - - - - - - - - - - - - - - - - - - - - - - - -
    1.  Find account(nbr)
    2.  Find account(id)  
    3.  Search customer 
    4.  Deposit cash 
    5.  Withdraw cash 
    6.  Transfer cash 
    7.  Create account 
    8.  Delete account
    9.  Print all accounts 
    10. Print history 
    11. Print date 
    12. Quit
    - - - - - - - - - - - - - - - - - - - - - - - - - - -
    """
@main
def run: Unit =
  var x = io.Source.fromFile("bank_log.txt").getLines.toVector
  var y = x.map(_.split(" ").toVector)
  k.findAllAccs
  println(s"Options: \n $menu")

  var quit = false 
  while quit != true do 
    print("Select nbr of option: \n > ")
    val selected = readLine()
    selected match 
      case "1"  => 
        println("Accountnumber: ")
        val accnbr = readLine()
        println((k.findByNumber(accnbr.toInt)).get)
      case "2"  => 
        println("Id: ")
        val id = readLine()
        k.findAccountsForHolder(id.toLong).foreach(x => println(x))
      case "3"  => 
        println("Name: ")
        val name = readLine()
        k.findByName(name).foreach(x => println(x))
      case "4"  => 
        println("Accountnumber: ")
        val accnbr = readLine()       // vid jättestor insättning blir balance något helt annat tal
        println("Amount: ")
        val amount = BigInt(readLine())

        k.doEvent(Deposit(accnbr.toInt, amount.toInt))
      case "5"  => 
        println("Accountnumber: ")
        val accnbr = readLine()       // vid jättestor insättning blir balance något helt annat tal
        println("Amount: ")
        val amount = BigInt(readLine())
        k.doEvent(Withdraw(accnbr.toInt, amount.toInt))
      case "6"  => 
        println("From: ")
        val accFrom = readLine()
        println("To: ")
        val accTo = readLine()
        println("Amount: ")
        val amount = readLine()
        k.doEvent(Transfer(accFrom.toInt, accTo.toInt, amount.toInt))
      case "7"  => 
        println("Id: ")
        val id = readLine()
        println("Name: ")
        val name = readLine()
        val z = NewAccount(id.toLong, name) 
        println(k.doEvent(z))
      case "8"  => 
        println("Accountnumber: ")
        val account = readLine()
        k.doEvent(DeleteAccount(account.toInt))
      case "9"  => for j <- k.allAccounts().indices do println(k.allAccounts()(j)) 
      case "10" => io.Source.fromFile("bank_log.txt").getLines.toVector.foreach(x => println(x))
      case "11" => 
        println("Date: ")
        val returndatedate = readLine()
        val date = Date.fromLogFormat(returndatedate)
        k.returnToState(Date(y.last(0).toInt, y.last(1).toInt, y.last(2).toInt, y.last(3).toInt, y.last(4).toInt))
        k.returnToState(date)
      case "12" => 
        println("Thank you for your visit.")
        quit = true
      case _ => println("The chosen option does not exist. Choose one that does.");()