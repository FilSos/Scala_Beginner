package lectures.part2_oop

object Objects extends App {

  //SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { //typo + its only instance
    //"static"/"class" level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }


  class Person(val name: String) {
    //instance-level functionality
  }

  //COMPANIONS - same name, same scope
  println(Person.N_EYES)
  println(Person.canFly)

  //Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")

  val bobby = Person.apply(mary, john)
  println(bobby.name)
}
