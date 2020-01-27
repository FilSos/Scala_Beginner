package lectures.part2_oop

object Generics extends App {

  class MyList[+A] {

    def add[B >: A](element: B): MyList[B] = ???

    //    A = Cat
    //    B = Animal
  }

  class MyMap[Key, Value] {

  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  //1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  //animalList.add(new Dog) ??? HARD QUESTION => we return a list of animals

  //2.   no = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. Hell, no! CONTRAVARIANCE
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  //bounded types
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

  class Car
//generic type needs proper bounded type
 // val newCage = new Cage(new Car)

  //expand MyList to be generic
}
