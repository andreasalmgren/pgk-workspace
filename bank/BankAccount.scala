package bank

import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.io.StdIn.readLine
/**
* Creates a new bank account for the customer provided.
* The account is given a unique account number and initially
* has a balance of 0 kr.
*/
import scala.util.Random.nextInt as random
class BankAccount(val holder: Customer) {
  def setNum: Int = 
    var text = io.Source.fromFile("bank_log.txt").getLines.mkString.split(" ").toVector
    var arr = Array[Int]().empty
    for i <- 0 to text.size -1 do
      if text(i).toString == "N" then
        arr = arr :+ text(i+3).toInt
    var apa = arr.last.toInt + 1
    apa

  var accountNumber: Int = setNum
  var account:Map[Int, BigInt] = Map(accountNumber -> 0)

/**
* Deposits the provided amount in this account.
*/
  def deposit(amount: BigInt): Unit = 
    var newamount = account(accountNumber) + amount
    account = Map(accountNumber -> newamount)

/**
* Returns the balance of this account.
*/
  def balance: BigInt = account(accountNumber)
  
/**
* Withdraws the provided amount from this account,
* if there is enough money in the account. Returns true
* if the transaction was successful, otherwise false.
*/
  def withdraw(amount: BigInt): Boolean = 
    var newamount = account(accountNumber) - amount 
    if account(accountNumber) > amount then 
      account = Map(accountNumber -> newamount)
    account(accountNumber) > amount 
  override def toString(): String =  
    s"Banknummer: $accountNumber , Saldo: $balance" 
}

object BankAccount{
  var temp = io.Source.fromFile("accountNbr.txt").getLines.toVector
  var newAccNumber = temp(0).toInt
}