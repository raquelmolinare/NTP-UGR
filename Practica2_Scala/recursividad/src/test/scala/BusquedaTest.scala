import FuncionesRecursivas.{busquedaASaltosGenerica, busquedaMetodoFibonacci, mayor, menor}
import org.scalatest.funsuite.AnyFunSuite

class BusquedaTest extends  AnyFunSuite {

  // BÚSQUEDA GENÉRICA A SALTOS
  // Prueba 1: se la posocion de los distintos valores de la lista [1,2,3,4,5,6,7,8,9,10]
  test("Busqueda Generica: buscado=1, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(1, List(1,2,3,4,5,6,7,8,9,10), mayor) === 0)
  }

  test("Busqueda Generica: buscado=2, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(2, List(1,2,3,4,5,6,7,8,9,10), mayor) === 1)
  }

  test("Busqueda Generica: buscado=3, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(3, List(1,2,3,4,5,6,7,8,9,10), mayor) === 2)
  }

  test("Busqueda Generica: buscado=4, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(4, List(1,2,3,4,5,6,7,8,9,10), mayor) === 3)
  }

  test("Busqueda Generica: buscado=5, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(5, List(1,2,3,4,5,6,7,8,9,10), mayor) === 4)
  }

  test("Busqueda Generica: buscado=6, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(6, List(1,2,3,4,5,6,7,8,9,10), mayor) === 5)
  }

  test("Busqueda Generica: buscado=7, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(7, List(1,2,3,4,5,6,7,8,9,10), mayor) === 6)
  }

  test("Busqueda Generica: buscado=8, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(8, List(1,2,3,4,5,6,7,8,9,10), mayor) === 7)
  }

  test("Busqueda Generica: buscado=9, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(9, List(1,2,3,4,5,6,7,8,9,10), mayor) === 8)
  }

  test("Busqueda Generica: buscado=10, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaASaltosGenerica(10, List(1,2,3,4,5,6,7,8,9,10), mayor) === 9)
  }


  // Prueba 2: se la posición de los distintos valores de la sucesión de fibonacci [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]
  test("Busqueda Generica: buscado=55, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaASaltosGenerica(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor) === 9)
  }

  test("Busqueda Generica: buscado=233, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaASaltosGenerica(233, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor) === 12)
  }

  test("Busqueda Generica: buscado=2, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaASaltosGenerica(2, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor) === 2)
  }

  // Prueba 3: se obtiene la posición del valor 4 en [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987] VALOR NO INCLUIDO EN LA LISTA
  test("Busqueda Generica: buscado=4, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaASaltosGenerica(4, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor) === -1)
  }

  // Prueba 4: se obtiene la posición del valor 1 en [] LISTA VACÍA
  test("Busqueda Generica: buscado=1, coleccion=[]") {
    assert(busquedaASaltosGenerica(4, List(), mayor) === -1)
  }



  // BÚSQUEDA MÉTODO FIBONACCI
  // Prueba 1: se la posocion de los distintos valores de la lista [1,2,3,4,5,6,7,8,9,10]
  test("Busqueda Método Fibonacci: buscado=1, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(1, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 0)
  }

  test("Busqueda Método Fibonacci: buscado=2, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(2, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 1)
  }

  test("Busqueda Método Fibonacci: buscado=3, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(3, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 2)
  }

  test("Busqueda Método Fibonacci: buscado=4, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(4, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 3)
  }

  test("Busqueda Método Fibonacci: buscado=5, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(5, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 4)
  }

  test("Busqueda Método Fibonacci: buscado=6, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(6, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 5)
  }

  test("Busqueda Método Fibonacci: buscado=7, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(7, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 6)
  }

  test("Busqueda Método Fibonacci: buscado=8, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(8, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 7)
  }

  test("Busqueda Método Fibonacci: buscado=9, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(9, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 8)
  }

  test("Busqueda Método Fibonacci: buscado=10, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(busquedaMetodoFibonacci(10, List(1,2,3,4,5,6,7,8,9,10), mayor, menor) === 9)
  }


  // Prueba 2: se la posición de los distintos valores de la sucesión de fibonacci [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]
  test("Busqueda Método Fibonacci: buscado=55, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaMetodoFibonacci(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor, menor) === 9)
  }

  test("Busqueda Método Fibonacci: buscado=233, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaMetodoFibonacci(233, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor, menor) === 12)
  }

  test("Busqueda Método Fibonacci: buscado=2, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaMetodoFibonacci(2, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor, menor) === 2)
  }

  // Prueba 3: se obtiene la posición del valor 4 en [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987] VALOR NO INCLUIDO EN LA LISTA
  test("Busqueda Método Fibonacci: buscado=4, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(busquedaMetodoFibonacci(4, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987), mayor, menor) === -1)
  }

  // Prueba 4: se obtiene la posición del valor 1 en [] LISTA VACÍA
  test("Busqueda Método Fibonacci: buscado=1, coleccion=[]") {
    assert(busquedaMetodoFibonacci(4, List(), mayor, menor) === -1)
  }

}
