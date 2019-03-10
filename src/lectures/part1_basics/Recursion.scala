package lectures.part1_basics

object Recursion extends App {

  def factorial(n: Int): BigInt = {
    if (n <= 1) 1
    else n * factorial(n - 1) //NORMAL RECURSIVE
  }

  println(factorial(3000))

  def anotherFactorial(n: Int): Int = {
    def factorialHelper(x: Int, accumulation: Int): Int =
      if (x <= 1) accumulation
      else factorialHelper(x - 1, x * accumulation) //TAIL RECURSIVE

    factorialHelper(n, 1)
  }

  println(anotherFactorial(10))

  def tailRepeatedFunction(text: String, n: Int, cumulatedText: String): String = {
    if (n < 1) cumulatedText
    else tailRepeatedFunction(text, n - 1, text + cumulatedText)
  }

  println(tailRepeatedFunction("test", 2, ""))

  def isPrime(n: Int): Boolean = {
    def isPrimeTail(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTail(t - 1, n % t != 0 && isStillPrime)

    isPrimeTail(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))

  def fibonacciTailFunction(n: Int): Int = {
    def fibonacciCalc(t: Int, lastFibonacciNumber: Int, previousFibonacciNumber: Int): Int = {
      if (t >= n) lastFibonacciNumber
      else fibonacciCalc(t + 1, lastFibonacciNumber + previousFibonacciNumber, lastFibonacciNumber)
    }

    if (n <= 2) 1
    else fibonacciCalc(2, 1, 1)
  }

  println(fibonacciTailFunction(8))

}

