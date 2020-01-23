package lectures.part2_oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMove: String, val age: Int = 1) {
    def likes(move: String): Boolean = move == favoriteMove

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(operator: String): Person = new Person(s"${name}(${operator})", favoriteMove)

    def unary_! : String = s"$name what the heck?!"

    def unary_+ : Person = new Person(name, favoriteMove, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and i like $favoriteMove"

    def apply(times: Int): String = s"Hi, my name is $name and i like $favoriteMove ${times} times"

    def learns(language: String): String = s"Mary is learning ${language}"

    def learnsScala: String = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  //infix notation = operator notation (syntactic sugar)

  //"operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  //prefix notation
  val x = -1 //equivalent with 1._unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  //only for method without parameters
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) //equivalent

  println((mary + "the rockstar") ())

  println(mary.unary_+.age)

  println((+mary).age)

  println(mary.learnsScala)
  println(mary learnsScala)

  println(mary.apply(10))

}


