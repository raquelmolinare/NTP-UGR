import org.scalatest.funsuite.AnyFunSuite

class TrianguloDePascalTest extends AnyFunSuite  {

  // se asigna la función a usar
  val function: (Int, Int) => Long = TrianguloDePascal.calcularValorTrianguloPascal

  // Prueba 1: se calcula el valor de la columna 0 y fila 2
  test("Triangulo De Pascal: fila=2, columna=0") {
    assert(function(2,0) === 1)
  }

  // Prueba 2: calculo del valor de columna 1 y fila 2
  test("Triangulo De Pascal: fila=2, columna=1") {
    assert(function(2,1) === 2)
  }

  // Prueba 3: calculo de valor de columna 1, fila 3
  test("Triangulo De Pascal: fila=3, columna=1") {
    assert(function(3,1) === 3)
  }

  // Prueba 4: calculo de valor de columna 5, fila 10
  test("Triangulo De Pascal: fila=10, columna=5") {
    assert(function(10,5) === 252)
  }

  // Prueba 5: calculo del valor de columna 10 y fila 15
  test("Triangulo De Pascal: fila=15, columna=10") {
    assert(function(15, 10) === 3003)
  }

  // Prueba 6: calculo de valores extremos
  test("Triangulo De Pascal: Valores Extremos") {
    assert(function(0,0) === 1)
    assert(function(5,0) === 1)
    assert(function(5,5) === 1)
    assert(function(10, 0) === 1)
    assert(function(10, 10) === 1)
    assert(function(15, 0) === 1)
    assert(function(15, 15) === 1)
  }

  // Prueba 6: calculo de un valor invalido : cuando la fila es menor a la columna
  test("Triangulo De Pascal: Valor Invalido (fila < columna)") {
    assert(function(5, 10) === -1)
  }

}
