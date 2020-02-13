package lectures.part3fp

import scala.annotation.tailrec

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


  case class Person(name: String, friendsList: Set[String]) {
  case class Person(name: String, friendsList: Map[String, Set[String]]) {

    def add(socialMedia: Map[String, Person]): Map[String, Person] = {
      socialMedia + (name -> Person.this)
    }

    def remove(socialMedia: Map[String, Person]): Unit = {
      socialMedia + (name -> Person.this)
    }

    def friend(socialMedia: Map[String, Person], person: Person): Map[String, Person] = {
      val getPersonA = socialMedia(Person.this.name)
      val nameA = getPersonA.name
      val getPersonB = socialMedia(person.name)
      val nameB = getPersonB.name
      val personAFriendsListSet = getPersonA.friendsList
      val personBFriendsListSet = getPersonB.friendsList
      val newListASet = personAFriendsListSet + nameB
      val newListBSet = personBFriendsListSet + nameA
      //println(s"${Person.this.name} dodał ${person.name} jako przyjaciela. Bieżąca lista przyjaciół ${Person.this.name}: $updatedListA")
      //println(s"Osoba ${person.name}  również posiada teraz jako przyjaciela osobę o imieniu ${Person.this.name}. Bieżąca lista przyjaciół ${person.name}: $updatedListB")
      socialMedia + (nameA -> (Person.this + (nameA -> newListASet)))

    }

    def unfriend(socialMedia: Map[String, Person], person: Person): Map[String, Person] = {
      val getPerson = socialMedia(Person.this.name)
      val personFriendsList = getPerson.friendsList
      val updatedList = personFriendsList - person.name
      println(s"${Person.this.name} usunął ${person.name} z listy przyjaciół.Bieżąca lista przyjaciół: $updatedList")
      updatedList
    }

    def changeName(newName: String): Person = Person(newName, friendsList)

    def getNumbersOfFriends() = {
      println(Person.this.friendsList.size)
    }

    def nFriends(network: Map[String, Person], person: String): Int = {
      if (!network.contains(person)) 0
      else network(person).friendsList.size
    }

    def mostFriends(network: Map[String, Person]): String = {
      network.maxBy(pair => pair._2.friendsList.size)._1
    }

    def nPeopleWithNoFriends(network: Map[String, Person]): Int = {
      network.filterKeys(k => network(k).friendsList.isEmpty).toMap.size
    }
  //TODO ogarnac wraz ze zmianami z drugieko komputera
    def socialConnection(network: Map[String, Person], person: Person): Boolean = {
      @tailrec
      def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          if (person == target) true
          else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
          else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person).name)
        }
      }

      bfs(person.name, network(Person.this.name) + Person.this.name)
    }

  }




  val newMap: Map[String, Person] = Map()
  val newMap2: Map[String, Person] = Map()
  val osoba1 = Person("Filip", newMap)
  val osoba2 = Person("Błażej", newMap2)
  val previewMap: Map[String, Person] = Map()

  val socialMedia: Map[String, Person] = osoba2.add(osoba1.add(previewMap))
  println(socialMedia.size)
  ja1.friend(socialMedia, ja2)
  ja1.unfriend(socialMedia, ja2)
  println(ja1.friendsList)
  println(nPeopleWithNoFriends(socialMedia))
  println("dajesz: " + osoba1.friend(socialMedia, osoba2))
  osoba1.unfriend(socialMedia, osoba2)
  println(osoba1.friendsList)
}
