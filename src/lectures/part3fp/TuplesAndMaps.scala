package lectures.part3fp

object TuplesAndMaps extends App {

  //tuples = finite orderes "lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("Hello, Scala, 2)

  // maps - keys -> values

  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Filip", 777)).withDefaultValue(-1) //or  Map(("Jim" -> 555), ("Filip" -> 777))
  println(phonebook)

  //map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary"))

  //add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  //functionals on maps
  //map, flatMap, filter

  println(newPhonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.filterKeys(x => x.startsWith("F")).toMap)
  println(phonebook.mapValues(number => "0245" + number).toMap)

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Filip", 777)).toMap)
  val names = List("Bob","James","Jim","Mary","Daniel")

  println(names.groupBy(name => name.charAt(0)))


}
