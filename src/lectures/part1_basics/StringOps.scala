package lectures.part1_basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"
  println(str.charAt(2))

  val number = "2"

  println('a' +: number :+ 'z')
  println("a" +: number :+ "z")

  //Scala-specific: String interpolators

  //S-interpolators
  val name = "Filip"
  val age = 25
  val greeting = s"Hello my name is $name and I am $age years old"
  println(greeting)

  //F-interpolators

  val weight = 8.4f
  val textAboutMe = f"$name weight is $weight%4f"
  println(textAboutMe)


  //Raw-interpolators
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")


}
