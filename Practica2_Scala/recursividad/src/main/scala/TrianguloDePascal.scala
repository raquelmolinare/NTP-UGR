/**
 * Ejercicio 1: Triángulo de Pascal
 */
object TrianguloDePascal {

  /**
   * Función para calcular el valor de una posición en el triangulo de pascal
   * usando la ecuación:  fila! / (columna! * (fila − columna)!)
   * @param fila posición de la fila (empezando por 0)
   * @param columna posición de la columna (empezando por 0)
   * @return valor de la posición
   */
  def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
    // Cálculo del valor a partir de la ecuación y la función recursiva para obtener el factorial
    factorial(fila) / (factorial(columna) * factorial(fila-columna))
  }

  /**
   * Función para calcular el factorial de un número usando recursividad
   * usando la fórmula: (n! = n * (n-1)!)
   * @param n numero para calcular el factorial
   * @return n!
   */
  def factorial(n: Int): Int = {
    if (n == 0) {
      1
    } else {
      n * factorial(n-1)
    }
  }


  /**
   * Método main: No es necesario pero se usa para tener un feedback visual
   * @param args argumentos
   */
  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

    val filas : Int  = 10

    // Se muestra el triangulo según el número de filas establecido
    for (row <- 0 to filas) {
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(row, col) + " ")
      println()
    }

    // Se muestra el valor que debe ocupar la fila 10 y columna 5
    print(calcularValorTrianguloPascal(10, 5))
  }

}
