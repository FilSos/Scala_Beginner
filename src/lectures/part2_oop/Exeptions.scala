package lectures.part2_oop

object Exeptions extends App {

  val x: String = null
  // println(x.length)
  //this will crash with a NPE
  // 1. throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    println("finally")
  }
  println(potentialFail)

  // 3. how to define your own exceptions
  //  class MyException extends Exception
  //
  //  val exeption = new MyException
  //  throw exeption

  //Out of memory exception
  //val array = Array.ofDim(Int.MaxValue)

  //Stack overflow exception
  //  def infinite: Int = 1 + infinite
  //  val noLimit = infinite

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int) =
      try {
        if (x > 0 && y > 0 && x + y < 0) throw new OverflowException
        else if (x < 0 && y < 0 && x + y > 0) throw new UnderflowException
        else
          x + y
      } catch {
        case e: RuntimeException => "Najgorzej"
      }

    def substract(x: Int, y: Int) =
      try {
        if (x > 0 && y < 0 && x - y < 0) throw new OverflowException
        else if (x < 0 && y > 0 && x - y > 0) throw new UnderflowException
        else
          x - y
      } catch {
        case e: RuntimeException => "Bang bang!"
      }
  }


  println(PocketCalculator.add(Int.MaxValue, 3))
  println(PocketCalculator.substract(2, 0))
}
