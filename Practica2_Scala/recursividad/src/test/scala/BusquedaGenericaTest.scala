import FuncionesRecursivas.{busquedaASaltosGenerica, busquedaMetodoFibonacci}
import org.scalatest.funsuite.AnyFunSuite

class BusquedaGenericaTest extends  AnyFunSuite {

  // se asigna la función a usar
  val saltos: (Int, List[Int]) => Int = busquedaASaltosGenerica

  val fibonacci: (Int, List[Int]) => Int = busquedaMetodoFibonacci

  // BÚSQUEDA GENÉRICA A SALTOS
  // Prueba 1: se la posocion de los distintos valores de la lista [1,2,3,4,5,6,7,8,9,10]
  test("Busqueda Generica: buscado=1, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(1, List(1,2,3,4,5,6,7,8,9,10)) === 0)
  }

  test("Busqueda Generica: buscado=2, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(2, List(1,2,3,4,5,6,7,8,9,10)) === 1)
  }

  test("Busqueda Generica: buscado=3, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(3, List(1,2,3,4,5,6,7,8,9,10)) === 2)
  }

  test("Busqueda Generica: buscado=4, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(4, List(1,2,3,4,5,6,7,8,9,10)) === 3)
  }

  test("Busqueda Generica: buscado=5, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(5, List(1,2,3,4,5,6,7,8,9,10)) === 4)
  }

  test("Busqueda Generica: buscado=6, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(6, List(1,2,3,4,5,6,7,8,9,10)) === 5)
  }

  test("Busqueda Generica: buscado=7, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(7, List(1,2,3,4,5,6,7,8,9,10)) === 6)
  }

  test("Busqueda Generica: buscado=8, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(8, List(1,2,3,4,5,6,7,8,9,10)) === 7)
  }

  test("Busqueda Generica: buscado=9, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(9, List(1,2,3,4,5,6,7,8,9,10)) === 8)
  }

  test("Busqueda Generica: buscado=10, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(saltos(10, List(1,2,3,4,5,6,7,8,9,10)) === 9)
  }


  // Prueba 2: se la posición de los distintos valores de la sucesión de fibonacci [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]
  test("Busqueda Generica: buscado=55, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(saltos(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 9)
  }

  test("Busqueda Generica: buscado=233, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(saltos(233, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 12)
  }

  test("Busqueda Generica: buscado=2, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(saltos(2, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 2)
  }

  // Prueba 3: se obtiene la posición del valor 4 en [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987] VALOR NO INCLUIDO EN LA LISTA
  test("Busqueda Generica: buscado=4, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(saltos(4, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === -1)
  }

  // Prueba 4: se obtiene la posición del valor 1 en [] LISTA VACÍA
  test("Busqueda Generica: buscado=1, coleccion=[]") {
    assert(saltos(4, List()) === -1)
  }



  // BÚSQUEDA MÉTODO FIBONACCI
  // Prueba 1: se la posocion de los distintos valores de la lista [1,2,3,4,5,6,7,8,9,10]
  test("Busqueda Método Fibonacci: buscado=1, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(1, List(1,2,3,4,5,6,7,8,9,10)) === 0)
  }

  test("Busqueda Método Fibonacci: buscado=2, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(2, List(1,2,3,4,5,6,7,8,9,10)) === 1)
  }

  test("Busqueda Método Fibonacci: buscado=3, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(3, List(1,2,3,4,5,6,7,8,9,10)) === 2)
  }

  test("Busqueda Método Fibonacci: buscado=4, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(4, List(1,2,3,4,5,6,7,8,9,10)) === 3)
  }

  test("Busqueda Método Fibonacci: buscado=5, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(5, List(1,2,3,4,5,6,7,8,9,10)) === 4)
  }

  test("Busqueda Método Fibonacci: buscado=6, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(6, List(1,2,3,4,5,6,7,8,9,10)) === 5)
  }

  test("Busqueda Método Fibonacci: buscado=7, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(7, List(1,2,3,4,5,6,7,8,9,10)) === 6)
  }

  test("Busqueda Método Fibonacci: buscado=8, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(8, List(1,2,3,4,5,6,7,8,9,10)) === 7)
  }

  test("Busqueda Método Fibonacci: buscado=9, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(9, List(1,2,3,4,5,6,7,8,9,10)) === 8)
  }

  test("Busqueda Método Fibonacci: buscado=10, coleccion=[1,2,3,4,5,6,7,8,9,10]") {
    assert(fibonacci(10, List(1,2,3,4,5,6,7,8,9,10)) === 9)
  }


  // Prueba 2: se la posición de los distintos valores de la sucesión de fibonacci [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]
  test("Busqueda Método Fibonacci: buscado=55, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(fibonacci(55, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 9)
  }

  test("Busqueda Método Fibonacci: buscado=233, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(fibonacci(233, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 12)
  }

  test("Busqueda Método Fibonacci: buscado=2, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(fibonacci(2, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === 2)
  }

  // Prueba 3: se obtiene la posición del valor 4 en [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987] VALOR NO INCLUIDO EN LA LISTA
  test("Busqueda Método Fibonacci: buscado=4, coleccion=[1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987]") {
    assert(fibonacci(4, List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)) === -1)
  }

  // Prueba 4: se obtiene la posición del valor 1 en [] LISTA VACÍA
  test("Busqueda Método Fibonacci: buscado=1, coleccion=[]") {
    assert(fibonacci(4, List()) === -1)
  }

}
