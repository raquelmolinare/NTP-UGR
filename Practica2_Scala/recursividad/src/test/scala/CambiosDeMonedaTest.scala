import org.scalatest.funsuite.AnyFunSuite

class CambiosDeMonedaTest extends AnyFunSuite {

  // se asigna la función a usar
  val function: (Int, List[Int]) => Int = CambiosDeMoneda.listarCambiosPosibles

  // Prueba 1: se calcula el numero de cambios posibles para un valor de 4 con monedas de 1 y 2
  test("Cambios De Moneda: valor=4, tipos_monedas=[1,2]") {
    assert(function(4, List(1,2)) === 3)
  }

  // Prueba 2: se calcula el numero de cambios posibles para un valor de 4 con monedas de 1, 2 y 3
  test("Cambios De Moneda: valor=4, tipos_monedas=[1,2,3]") {
    assert(function(4, List(1,2,3)) === 4)
  }

  // Prueba 3: se calcula el numero de cambios posibles para un valor de 50 con monedas de 10, 20, 30
  test("Cambios De Moneda: valor=50, tipos_monedas=[10,20,30]") {
    assert(function(50, List(10,20,30)) === 5)
  }

  // Prueba 4: se calcula el numero de cambios posibles para un valor de 200 con monedas de 20,50 y 100
  test("Cambios De Moneda: valor=200, tipos_monedas=[20,50,100]") {
    assert(function(200, List(20,50,100)) === 6)
  }

  // Prueba 5: se calcula el numero de cambios posibles para un valor de 5 con monedas de 10 y 20
  test("Cambios De Moneda: valor=5, tipos_monedas=[10,20]") {
    assert(function(5, List(10,20)) === 0)
  }

  // Prueba 6: se calcula el numero de cambios posibles para un valor de 5 con ningún tipo de monedas
  test("Cambios De Moneda: valor=5, tipos_monedas=[]") {
    assert(function(5, List()) === 0)
  }


}
