package lectures.part3fp

object WhatsAFunction extends App {
  //DREAM: use functions as first class elements
  //problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  //function types = Function1[A,B]- up to 22 parameters
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(word: String): Int = word.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(val1: Int, val2: Int): Int = val1 + val2
  }
  println(adder(3, 6))

  val concatenateStrings = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  val superAdder = new (Int => ((Int) => Int)) {
    override def apply(x: Int): (Int) => Int = new (Int => Int) {
      override def apply(y: Int): Int = x + y
    }
  }
  val finalAdder = superAdder(3)
  println(finalAdder(4))
  println(superAdder(4)(7)) //curried function
}

// Function types Function2[A,B,R] === (A,B) => R
// ALL SCALA FUNCTIONS ARE OBJECTS
trait MyFunction[A, B] {
  def apply(element: A): B
}
