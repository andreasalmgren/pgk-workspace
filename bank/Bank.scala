package bank

import scala.annotation.varargs
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.collection.mutable.ArrayBuffer
/**
* Creates a new bank with no accounts and no history.
*/
class Bank() {
  // def setNum: Int = 
  //   var text = io.Source.fromFile("bank_log.txt").getLines.mkString.split(" ").toVector
  //   var arr = Array[Int](0)
  //   for i <- 0 to text.size -1 do
  //     if text(i).toString == "N" then
  //       arr = arr :+ text(i+3).toInt
  //   var apa = arr.last.toInt + 1 
  //   apa

  // var accountNumber: Int = setNum

  def findAllAccs =
    var text = io.Source.fromFile("bank_log.txt").getLines.mkString.split(" ").toVector
    for i <- 0 to text.size -1 do
      if text(i).toString == "N" then
        // print(s"${text(i+1)} ") // id
        // print(s"${text(i+2)} ") // name
        // print(s"${text(i+3)} ") // acc num
        // println(" ")
        var customer = new Customer(text(i+2), text(i+1).toLong)
        accs = accs :+ (new BankAccount(customer))
        println(customer)
    accs
    println(accs)

  var custm: Vector[Customer]   = Vector()
  var accs: Vector[BankAccount] = Vector() //ArrayBuffer[BankAccount] = findAllAccs //
  //var accs2 = accs.toVector
/**
* Returns a list of every bank account in the bank.
* The returned list is sorted in alphabetical order based
* on customer name.
*/
  def allAccounts(): Vector[BankAccount] = 
    println(accs)
    //accs.toVector.sortBy(_.holder.name)
    accs.sortBy(_.holder.name)
/**
* Returns the account holding the provided account number.
*/
  def findByNumber(accountNbr: Int): Option[BankAccount] = 
    println(allAccounts())
    allAccounts().find(p => p.accountNumber == accountNbr) 
/**
* Returns a list of every account belonging to
* the customer with the provided id.
*/
  def findAccountsForHolder(id: Long): Vector[BankAccount] = 
    allAccounts().filter(p => p.holder.id == id).toVector
/**
* Returns a list of all customers whose names match
* the provided name pattern.
*/
  def findByName(namePattern: String): Vector[Customer] = 
    allAccounts().map(x => x.holder).filter(_.name.contains(namePattern))
/**
* Executes an event in the bank.
* Returns a string describing whether the
* event was successful or failed.
*/
  def doEvent(event: BankEvent): String = 
    event match {
    case Deposit(account, amount) => 
      //println(account)
      k.findByNumber(account).get.deposit(amount)
      makeHistory(Date.now(), event)
      event.toNaturalFormat
      
    case Withdraw(account, amount) =>
      k.findByNumber(account).get.withdraw(amount)
      makeHistory(Date.now(), event)
      event.toNaturalFormat

    case Transfer(accFrom, accTo, amount) =>  //withdraw(accfrom, amount) ; deposit(accTo, amount)
      if k.findByNumber(accFrom).get.withdraw(amount) then
        k.findByNumber(accTo).get.deposit(amount) 
        makeHistory(Date.now(), event) 
      else
        println("Medges ej")
      event.toNaturalFormat 
      
    case NewAccount(id, name, accountNumber) => 
      var c = Customer(name, id)
      var b = BankAccount(c)
      var acc2num = Map(b -> accountNumber)
      var saldo = Map(acc2num -> 0)

      accs = accs :+ b
      makeHistory(Date.now(), event)
      event.toNaturalFormat
      
    case DeleteAccount(account) => 
      accs = accs.filterNot(a => a.accountNumber == account)
      event.toNaturalFormat
}

/**
* Returns a log of all changes to the bank's state.
*/
  def history(): Vector[HistoryEntry] = ???
/**
* Resets the bank to the state it had at the provided date.
* Returns a string describing whether the event was
* successful or failed.
*/
  def makeemptyhist = 
    var l = io.Source.fromFile("bank_log.txt").getLines.toVector
    var empty: Vector[String] = Vector.empty
    Files.write(Paths.get("bank_log.txt"), empty.toString.getBytes(StandardCharsets.UTF_8))

  def returnToState(returnDate: Date) = 
    var hist = io.Source.fromFile("bank_log.txt").getLines.toVector
    var history = hist.map(_.split(" ").toVector)
    makeemptyhist

    for x <- history.indices do 
        if (Date(history(x)(0).toInt, history(x)(1).toInt, history(x)(2).toInt, history(x)(3).toInt, history(x)(4).toInt)).compare(returnDate) <= 0 then 
          history(x)(5) match 
            case "N" => k.doEvent(NewAccount(history(x)(6).toInt , history(x)(7), history(x)(8).toInt))  
            case "D" => k.doEvent(Deposit(history(x)(6).toInt, history(x)(7).toInt))
            case "W" => k.doEvent(Withdraw(history(x)(6).toInt, history(x)(7).toInt))
            case "T" => k.doEvent(Transfer(history(x)(6).toInt, history(x)(7).toInt, history(x)(8).toInt))
    }
    
    