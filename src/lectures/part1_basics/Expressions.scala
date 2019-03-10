package lectures.part1_basics

object Expressions extends App {

  val x = 1 + 2 //Expression
  println(x)
  println(1 + 5 * 4)
  println(-10 >> 2)
  println(-10 >>> 2)
  var variable = 0
  val weirdValue = (variable = 3) //Type Unit === void
  println(weirdValue)

  //podczas pisania zmiennych oraz petli while powstaja "side effects" czyli efekty uboczne które w rezultacje
  //zwracaja jedyny typ, ktory val jest w tym momencie w stanie przetrzymywac, tj Unit. Pozostalosc po imperatywnych
  //jezykach. Zachowuja sie jak intrukcje ale chca tak naprawde zwrocic nam jakas wartosc

  val codeBlock = {
    val y = 1
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }
  println(codeBlock)

  val boolValue = 2 > 3
  println(boolValue)

  val anotherBlock = {
    if(boolValue) 22 else 33
    66
  }
  println(anotherBlock)

}
