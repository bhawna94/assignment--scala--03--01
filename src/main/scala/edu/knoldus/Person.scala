package edu.knoldus

import java.io.{File, PrintWriter}

import org.apache.log4j.Logger

import org.json4s.JsonDSL._

import org.json4s._

import org.json4s.jackson.JsonMethods._

import org.json4s.jackson.Serialization

import scala.io.Source
//scalastyle:off

@SerialVersionUID(123)
class Person(val name: String, val age: Int,@transient val days:Int,
             val salary: Float,@transient val luckynumber: Int,
             val address: Address) extends Serializable

class Address(val street: String, val houseno: String) extends Serializable

object Person extends App {

 val log = Logger.getLogger(this.getClass)
 writeIntoFile()
 readFromFile()


private def writeIntoFile(): String = {

  val name = "bhawna"
  val age = 20
  val days = 7
  val luckynum = 9
  val salary = 4500
  val street = "abcdef"
  val housenum = "xyz"
  val person1 = new Person(name, age, days, salary,
    luckynum, new Address(street, housenum))
  implicit val formats = DefaultFormats
  val jsonstring = Serialization.write(person1)
  val pw = new PrintWriter(new File("output.txt"))
  pw.write(pretty(render(jsonstring)))
  pw.close
  s"file is created"

 }

private def readFromFile() : String = {

  for (line <- Source.fromFile("output.txt").getLines) {
   log.info(line)
  }
  s"done"
 }

}



