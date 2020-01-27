package exercises

/*
head = first element of the list
tail - remainder of the list
isEmpty = is this list empty
add(int) => new list with this element added
toString => a string representation of the list
*/
abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: myTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: myTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: myPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  override def map[B](transformer: myTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: myTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(predicate: myPredicate[Nothing]): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (tail.isEmpty) "" + h
    else h + " " + t.printElements

  /*
  [1,2,3].map(n * 2)
  = new Cons(2, [2,3].map(n*2))
  = new Cons(2, new Cons(4, [3].map(n*2)))
  = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2))))
  = new Cons(2, new Cons(4, new Cons(6, Empty)))
   */

  override def map[B](transformer: myTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  /*
    [1,2].flatMap(n => [n, n+1]
  = [1,2] ++ [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,2,3]
  */
  override def flatMap[B](transformer: myTransformer[A, MyList[B]]): MyList[B] = transformer.transform(h) ++ t.flatMap(transformer)

  /*
  [1,2,3].filter(n % 2 == 0) =
  [2,3].filter (n % 2 == 0) =
  = new Cons (2,[3].filter(n % 2 == 0))
  = new Cons (2,Empty.filter(n % 2 == 0))
  = new Cons (2,Empty)
  */

  override def filter(predicate: myPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)


  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

trait myPredicate[-T] {
  def test(element: T): Boolean
}

trait myTransformer[-A, B] {
  def transform(element: A): B
}

case class EvenPredicate() extends myPredicate[Int] {
  override def test(element: Int): Boolean = if (element.isInstanceOf[Int]) true
  else false
}

case class StringToIntTransformer() extends myTransformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

case object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)
  println(listOfIntegers.map(new myTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new myPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new myTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons[Int](element, new Cons(element + 1, Empty))
  }).toString)

  println(cloneListOfIntegers == listOfIntegers)
}