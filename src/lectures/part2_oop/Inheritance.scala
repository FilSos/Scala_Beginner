package lectures.part2_oop

object Inheritance extends App {

  sealed class Animal {
    val creatureType = "wild"

    def eat = println("omnom")
  }

  class Cat extends Animal {

    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  //constructors
  class Person(name: String, age: Int)

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  //overriding
  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType = "domestic"
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution(polymorphism)
  val unknownAnimal: Animal = new Dog("K99")
  unknownAnimal.eat

  //preventing overrides
  // 1 - final on member
  // 2 - final on class
  //3(NEW) - seal the class - extend classes in THIS FILE, prevent extension in other files
}
