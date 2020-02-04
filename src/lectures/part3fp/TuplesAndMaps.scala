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
  val names = List("Bob", "James", "Jim", "Mary", "Daniel")

  println(names.groupBy(name => name.charAt(0)))

  //exercises
  val filipPhonebook = Map("FILIP" -> 444, "Filip" -> 123)
  println(filipPhonebook.map(pair => pair._1.toLowerCase -> pair._2))


  case class Person(name: String, friendsList: Map[String, Person]) {

    def add(socialMedia: Map[String, Person]): Map[String, Person] = {
      socialMedia + (name -> Person.this)
      // println(s"Lista osob: $socialMedia")
      //updatedSocialMedia

    }

    def remove(socialMedia: Map[String, Person]): Unit = {
      socialMedia + (name -> Person.this)
    }

    def friend(socialMedia: Map[String, Person], person: Person): Map[String, Person] = {
      val getPerson = socialMedia(Person.this.name)
      val personFriendsList = getPerson.friendsList
      val updatedList = personFriendsList + (person.name -> person)
      println(s"${Person.this.name} dodał ${person.name} jako przyjaciela. Bieżąca lista przyjaciół: $updatedList")
      updatedList
    }

    def unfriend(socialMedia: Map[String, Person], person: Person): Map[String, Person] = {
      val getPerson = socialMedia(Person.this.name)
      val personFriendsList = getPerson.friendsList
      val updatedList = personFriendsList - person.name
      println(s"${Person.this.name} usunał ${person.name} z listy przyjaciół.Bieżąca lista przyjaciół: $updatedList")
      updatedList
    }

    def changeName(newName: String): Person = Person(newName, friendsList)

    def getNumbersOfFriends() = {
      println(Person.this.friendsList.size)
    }

  }

  def mostFriends(): Unit = {
    // println(socialMedia.)
  }

  val newMap: Map[String, Person] = Map()
  val newMap2: Map[String, Person] = Map()
  val ja1 = Person("Filip", newMap)
  val ja2 = Person("Błażej", newMap2)
  val previewMap: Map[String, Person] = Map()

  val socialMedia: Map[String, Person] = ja2.add(ja1.add(previewMap))
  println(socialMedia.size)
  ja1.friend(socialMedia, ja2)
  ja1.unfriend(socialMedia, ja2)
  println(ja1.friendsList)
}
