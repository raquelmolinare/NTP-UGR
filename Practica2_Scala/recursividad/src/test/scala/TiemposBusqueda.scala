import FuncionesRecursivas.{busquedaASaltosGenerica, busquedaMetodoFibonacci, mayor, menor}
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
  val coleccion = (1 to 750000).map(i => i).toList
  val buscado = 350000

  // se prueba el metodo de busqueda
  val saltos = standardConfig measure{
    busquedaASaltosGenerica(buscado,coleccion, mayor)
  }

  val fibonacci = standardConfig measure {
    busquedaMetodoFibonacci(buscado,coleccion, mayor, menor)
  }

  // muestra los tiempos
  println("Tiempo búsqueda a saltos genérica: " + saltos)
  println("Tiempo búsqueda método fibonacci: " + fibonacci)
}