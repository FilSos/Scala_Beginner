package lectures.part1_basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)

  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
