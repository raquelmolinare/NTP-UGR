import BusquedaGenerica.{busquedaASaltosGenerica, busquedaMetodoFibonacci}
import org.scalacheck.Prop.all
import org.scalacheck.Properties

  object BusquedaGenericaCheck extends Properties("Búsqueda Check") {

  property("Búsqueda A Saltos Generica") = {
    val check_busqueda1 = busquedaASaltosGenerica(1, List(1,2,3,4,5,6,7,8,9,10)) == 0
    val check_busqueda2 = busquedaASaltosGenerica(6, List(1,2,3,4,5,6,7,8,9,10)) == 5
    val check_busqueda3 = busquedaASaltosGenerica(10, List(1,2,3,4,5,6,7,8,9,10)) == 9
    val check_busqueda4 = busquedaASaltosGenerica(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) == 9
    val check_busqueda5 = busquedaASaltosGenerica(4, List(1,1,2,3,5)) == -1
    val check_busqueda6 = busquedaASaltosGenerica(4, List()) == -1

    all(check_busqueda1,check_busqueda2,check_busqueda3,check_busqueda4,check_busqueda5,check_busqueda6)
  }

  property("Búsqueda método Fibonacci") = {
    val check_busqueda1 = busquedaMetodoFibonacci(1, List(1,2,3,4,5,6,7,8,9,10)) == 0
    val check_busqueda2 = busquedaMetodoFibonacci(6, List(1,2,3,4,5,6,7,8,9,10)) == 5
    val check_busqueda3 = busquedaMetodoFibonacci(10, List(1,2,3,4,5,6,7,8,9,10)) == 9
    val check_busqueda4 = busquedaMetodoFibonacci(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) == 9
    val check_busqueda5 = busquedaMetodoFibonacci(4, List(1,1,2,3,5)) == -1
    val check_busqueda6 = busquedaMetodoFibonacci(4, List()) == -1

    all(check_busqueda1,check_busqueda2,check_busqueda3,check_busqueda4,check_busqueda5,check_busqueda6)
  }

}
