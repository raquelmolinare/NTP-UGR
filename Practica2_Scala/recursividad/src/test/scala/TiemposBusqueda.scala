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
  val coleccion_big = (1 to 750000).map(i => i).toList
  val buscado_big = 350000

  val coleccion_small = (1 to 100).map(i => i).toList
  val buscado_small = 20

  // se prueba el metodo de busqueda
  val saltos_big = standardConfig measure{
    busquedaASaltosGenerica(buscado_big,coleccion_big, mayor)
  }

  val fibonacci_big = standardConfig measure {
    busquedaMetodoFibonacci(buscado_big,coleccion_big, mayor, menor)
  }

  val saltos_small = standardConfig measure{
    busquedaASaltosGenerica(buscado_small,coleccion_small, mayor)
  }

  val fibonacci_small = standardConfig measure {
    busquedaMetodoFibonacci(buscado_small,coleccion_small, mayor, menor)
  }

  // muestra los tiempos
  println("TIEMPO DE BÚSQUEDA PARA UNA COLECCIÓN GRANDE")
  println("Tiempo búsqueda a saltos genérica: " + saltos_big)
  println("Tiempo búsqueda método fibonacci: " + fibonacci_big)

  println("\nTIEMPO DE BÚSQUEDA PARA UNA COLECCIÓN PEQUEÑA")
  println("Tiempo búsqueda a saltos genérica: " + saltos_small)
  println("Tiempo búsqueda método fibonacci: " + fibonacci_small)
}