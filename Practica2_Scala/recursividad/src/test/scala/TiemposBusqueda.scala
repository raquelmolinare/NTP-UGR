import BusquedaGenerica.{busquedaASaltosGenerica, busquedaMetodoFibonacci}
import org.scalameter.{Key, Warmer, config}

object TiemposBusqueda extends App{
  // se define la configuracion estandar de las pruebas
  val standardConfig = config(
    Key.exec.maxWarmupRuns := 5,
    Key.exec.maxWarmupRuns := 10,
    Key.exec.benchRuns := 10,
    Key.verbose := true
  ) withWarmer new Warmer.Default

  // se fija el valor a buscar y la colección a buscar
  val buscado : Int = 10946
  var coleccion: List[Int] = List(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229)

  // se prueba el metodo de busqueda
  val saltos = standardConfig measure{
    busquedaASaltosGenerica(buscado,coleccion)
  }

  val fibonacci = standardConfig measure {
    busquedaMetodoFibonacci(buscado,coleccion)
  }

  // muestra los tiempos
  println("Tiempo búsqueda a saltos genérica: " + saltos)
  println("Tiempo búsqueda método fibonacci: " + fibonacci)
}