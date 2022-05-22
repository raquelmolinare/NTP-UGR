/**
 * Ejercicio 3: Búsqueda genérica en colecciones ordenadas
 */
object BusquedaGenerica {

  def fibonacci(n:Int): Int = {
    if (n == 0) {
      0
    }
    else if(n == 1) {
      1
    }
    else{
      fibonacci(n-1)+fibonacci(n-2)
    }
  }


  def busquedaASaltosGenerica(buscado: Int, coleccion: List[Int])  : Int = {

    def metodoSaltos(coleccion: List[Int], tam_bloque: Int)  : Int = {

      // Comparar el elemento buscado con el final del primer bloque
      val primer_bloque = coleccion.take(tam_bloque)

      if (buscado == primer_bloque.last) {
        // Si el elemento buscado es el ultimo elemento de este bloque
        primer_bloque.length-1
      }
      else if (buscado > primer_bloque.last) {
        // Si el elemento buscado es mayor entonces se salta este bloque
        val restante = coleccion.takeRight(coleccion.length-tam_bloque)
        val pos_siguiente_bloque = metodoSaltos(restante, tam_bloque)
        if (pos_siguiente_bloque != -1) {
          tam_bloque+pos_siguiente_bloque
        }
        else {
          -1
        }
      }
      else {
        // Si el elemento buscado es menor al último elemento del bloque entonces
        // se hace una busqueda lineal en el bloque actual
        busquedaLineal(primer_bloque)
      }
    }

    def busquedaLineal(coleccion: List[Int]) : Int = {
      for (i <- coleccion.indices) {
        if (coleccion(i) == buscado) {
          return i
        }
      }
      -1
    }

    if (coleccion.isEmpty) {
      return -1
    }

    // Tamaño de bloque es la raíz cuadrada de la longitud de la colección
    val tam_bloque = math.sqrt(coleccion.length).toInt

    // Se van dando saltos por la colección haciendo la búsqueda
    metodoSaltos(coleccion,tam_bloque)


  }

  def busquedaMetodoFibonacci(buscado: Int, coleccion: List[Int])  : Int = {

    def metodoFibonnaci(_f0: Int, _f1: Int, _f2: Int, _inicio :Int, _n : Int ) : Int = {

      var f0 = _f0
      var f1 = _f1
      var f2 = _f2
      var inicio = _inicio

      // se determina el valor de indice a considerar, calculando el minimo entre (f0 + inicio)
      // y el tamaño de la secuencia n (como es un indice sera n-1)
      val indice = Math.min(f0 + inicio, _n-1)

      if (buscado > coleccion(indice)) {
        // si el valor a buscar es mayor, entonces los numeros de Fibonacci a considerar para la siguiente iteracion son
        // f0 = f1-f0 y f1 = f0 y el nuevo valor de inicio a usar será el tomado por indice.
        f2  = f1
        f1 = f0
        f0 = f2-f0
        inicio = indice
      }
      else if (buscado < coleccion(indice)) {
        // en caso contrario, si el valor buscado es menor, los numeros de Fibonacci a considerar son f0 = f0-(f1-f0)
        // y f1 = f1-f0  no cambia el valor de inicio
        f2  = f0
        f1 = f1-f0
        f0 = f2-f1
      }
      else {
        // si el valor almacenado en la posicion dada indice por coincide con el valor a buscar, hemos terminado la busqueda
        return indice
      }

      if (f2 > 1) {
        return metodoFibonnaci(f0, f1, f2, inicio, _n)
      }

      -1
    }

    if (coleccion.isEmpty) {
      return -1
    }

    // sea n el numero de elementos en la lista o secuencia de elementos en los que buscar (que deben estar ordenada).
    val n =  coleccion.length

    // Se determina el primero numero de Fibonacci superior o igual a n. Sea este numero f2 y los dos anteriores f1 y f0.

    var f0 = 0 // Primer número de la sucesión de fibonacci
    var f1 = 1 // Segundo número de la sucesión de fibonacci
    var f2 = f0 + f1 // Tercer número de la sucesión de fibonacci

    while (f2 < n) {
      f0 = f1
      f1 = f2
      f2  = f0 + f1
    }

    // Se hace que la variable inicio tenga valor -1
    val inicio = -1

    metodoFibonnaci(f0,f1,f2,inicio,n)
  }


  /**
   * Método main: No es necesario pero se usa para tener un feedback visual
   * @param args argumentos
   */
  def main(args: Array[String]) {
    println("................... Búsqueda genérica ...................")

    val total : Int = 16
    val buscado : Int = 55
    var coleccion: List[Int] = List()

    for (i <- 1 to total) {
      coleccion= coleccion :+ fibonacci(i)
    }

    println("Buscado: "+buscado)
    print("Colección: ")
    coleccion.foreach(item => print(item+" "))
    println()
    println()
    println("SOLUCIÓN BÚSQUEDA A SALTOS:")
    println("Posición: "+busquedaASaltosGenerica(buscado, coleccion))
    println()
    println("SOLUCIÓN BÚSQUEDA MÉTODO DE FIBONACCI:")
    println("Posición: "+busquedaMetodoFibonacci(buscado, coleccion))

  }


}
