package lectures.part2_oop

import java.util.Date
import java.sql.{Date => sqlDate}

object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Filip", "RockTheJVM", 2020)

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  val date = new Date()
  val sqlDate = new sqlDate(2020, 5, 12)

}
