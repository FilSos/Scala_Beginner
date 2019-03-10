package lectures.part2_oop

object OOBasics extends App {

  val person = new Person("Filip", 25)
  println(person.x)
  person.greet("Wojtek")
  person.greet()

  val writer = new Writer("Filip", "Sosnowski", 26)
  val imposter = new Writer("Filip", "Sosnowski", 26)
  writer.fullName()

  val novel = new Novel(author = writer, name = "Przeminęło z wiatrem", yearOfRelease = 1994)
  novel.authorAge()
  println(novel.isWrittenBy(writer))
  println(novel.isWrittenBy(imposter))

  novel.copy(2019)

  val counter = new Counter()
  counter.increment().print
  counter.increment(3).increment().print
}

class Person(name: String, age: Int) {
  val x = 2
  println(4)


  def greet(name: String): Unit = println(s"${this.name} says: Hey, $name")

  def greet(): Unit = println(s"Hey, $name")

}

//nie mozesz dostac sie do zmiennych z klasy, aby to zrobic musisz dopisac "val" przed parametrem

class Writer(firstName: String, secondName: String, val year: Int) {
  def fullName() = println(firstName + " " + secondName)
}

class Novel(name: String, val yearOfRelease: Int, author: Writer) {
  def authorAge() = println("Author is " + author.year + " years old")

  def isWrittenBy(author: Writer) = author == this.author

  def copy(newYear: Int): Novel = new Novel(this.name, newYear, author)


}

class Counter(val number: Int = 0) {

  def increment() = new Counter(number + 1)

  def decrement() = new Counter(number - 1)

  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment().increment(n - 1)
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement().decrement(n - 1)
  }

  def print = println(number)
}