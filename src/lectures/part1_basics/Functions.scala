package lectures.part1_basics

object Functions extends App {
  def function(a: String, b: Int): String = {
    a + " " + b
  }

  def functionWithoutParameters(): Int = 25

  println(functionWithoutParameters())
  println("bez nawiasów: " + functionWithoutParameters)


  def repeatedFunction(text: String, n: Int): String = {
    if (n == 1) text
    else text + "," + repeatedFunction(text, n - 1)
  }

  println(repeatedFunction("test", 4))

  def greetingFunction(name: String, age: Int): String = {
    "Hey, my name is " + name + " and I am " + age + " years old"
  }

  println(greetingFunction("Filip", 25))

  def factorialFunction(n: Int): Int = {
    if (n == 1) 1
    else n * factorialFunction(n - 1)
  }

  println(factorialFunction(5))

  def fibbonaciFunction(n: Int): Int = {
    if (n <= 2) 1
    else fibbonaciFunction(n - 1) + fibbonaciFunction(n - 2)
  }

  println(fibbonaciFunction(8))

  def primeNumberFunction(n: Int): String = {
    if (n % 2 != 0 || n == 2) "Prime"
    else "Not prime"
  }

  println(primeNumberFunction(37 * 17))
}
