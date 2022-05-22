import FuncionesRecursivas.{busquedaASaltosGenerica, busquedaMetodoFibonacci, mayor, mayorChar, menor, menorChar}
import org.scalacheck.Prop.all
import org.scalacheck.Properties

  object BusquedaCheck extends Properties("Búsqueda Check") {

  property("Búsqueda A Saltos Generica") = {
    val check_busqueda1 = busquedaASaltosGenerica(1, List(1,2,3,4,5,6,7,8,9,10), mayor) == 0
    val check_busqueda2 = busquedaASaltosGenerica(6, List(1,2,3,4,5,6,7,8,9,10), mayor) == 5
    val check_busqueda3 = busquedaASaltosGenerica(10, List(1,2,3,4,5,6,7,8,9,10), mayor) == 9
    val check_busqueda4 = busquedaASaltosGenerica(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor) == 9
    val check_busqueda5 = busquedaASaltosGenerica(4, List(1,1,2,3,5), mayor) == -1
    val check_busqueda6 = busquedaASaltosGenerica(4, List(), mayor) == -1
    val check_busqueda7 = busquedaASaltosGenerica('f', List('a','b','c','d','f','j'), mayorChar) == 4
    val check_busqueda8 = busquedaASaltosGenerica('m', List('a','b','c','d','f','j'), mayorChar) == -1

    all(check_busqueda1,check_busqueda2,check_busqueda3,check_busqueda4,check_busqueda5,check_busqueda6,check_busqueda7,check_busqueda8)
  }

  property("Búsqueda método Fibonacci") = {
    val check_busqueda1 = busquedaMetodoFibonacci(1, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) == 0
    val check_busqueda2 = busquedaMetodoFibonacci(6, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) == 5
    val check_busqueda3 = busquedaMetodoFibonacci(10, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) == 9
    val check_busqueda4 = busquedaMetodoFibonacci(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor, menor) == 9
    val check_busqueda5 = busquedaMetodoFibonacci(4, List(1,1,2,3,5), mayor, menor) == -1
    val check_busqueda6 = busquedaMetodoFibonacci(4, List(), mayor, menor) == -1
    val check_busqueda7 = busquedaMetodoFibonacci('f', List('a','b','c','d','f','j'), mayorChar, menorChar) == 4
    val check_busqueda8 = busquedaMetodoFibonacci('m', List('a','b','c','d','f','j'), mayorChar,menorChar) == -1

    all(check_busqueda1,check_busqueda2,check_busqueda3,check_busqueda4,check_busqueda5,check_busqueda6,check_busqueda7,check_busqueda8)
  }

}
