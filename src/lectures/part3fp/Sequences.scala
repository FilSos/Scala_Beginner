package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  //Seq

  val aSequence = Seq(3, 1, 2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(4, 5, 6))
  println(aSequence.sorted)

  //Ranges

  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  //Lists

  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  //Arrays

  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  val threeElementsStrings = Array.ofDim[String](3)
  threeElements.foreach(println)
  threeElementsStrings.foreach(println)

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0
  println(numbers.mkString(" "))

  //arrays and seq

  val numberSeq: Seq[Int] = numbers //implicit conversion
  println(numberSeq)

  //vectors

  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  //pros
  // keeps reference to tail
  //cons
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  //pros
  // depth of the tree is small
  //cons
  // need to replace an entire 32-elements chunk

  println(getWriteTime(numbersVector))
}
