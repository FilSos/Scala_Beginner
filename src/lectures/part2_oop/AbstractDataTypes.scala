package lectures.part2_oop

object AbstractDataTypes extends App {

  //abstract
  abstract class Animal {
    val creatureType: String

    def eat: Unit
  }

  class Dog extends Animal {
    //override not necessary here
    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch")
  }

  //traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    def eat: Unit = println("nomnomnom")

    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //trait vs abstract class
  //1 - traits do not have constructor parameters
  //2 - multiple traits, one class only
  //3 - traits describes behaviors, class describes "things"
}
