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

  //higher-order functions(map,flatMap,filter)
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

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

  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
    [1,2].flatMap(n => [n, n+1]
  = [1,2] ++ [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,2,3]
  */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)

  /*
  [1,2,3].filter(n % 2 == 0) =
  [2,3].filter (n % 2 == 0) =
  = new Cons (2,[3].filter(n % 2 == 0))
  = new Cons (2,Empty.filter(n % 2 == 0))
  = new Cons (2,Empty)
  */

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)


  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}


//case class EvenPredicate() extends myPredicate[Int] {
//  override def test(element: Int): Boolean = if (element.isInstanceOf[Int]) true
//  else false
//}
//
//case class StringToIntTransformer() extends myTransformer[String, Int] {
//  override def transform(element: String): Int = element.toInt
//}

case object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)
  println(listOfIntegers.map(elem => elem * 2).toString)

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(elem => Cons(elem, Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)
}