import scala.annotation.tailrec

object FuncionesRecursivas {

  /**
   * Ejercicio 1: Triángulo de Pascal
   */

  /**
   * Función para calcular el valor de una posición en el triangulo de pascal
   * usando la ecuación:  fila! / (columna! * (fila − columna)!)
   * @param fila posición de la fila (empezando por 0)
   * @param columna posición de la columna (empezando por 0)
   * @return valor de la posición
   */
  def calcularValorTrianguloPascal(fila: Int, columna: Int): BigInt = {

    /**
     * Función factorial tail recursive usando la fórmula: (n! = n * (n-1)!)
     * @param n numero para calcular el factorial
     * @return n!
     */
    def factorial(n: BigInt): BigInt = {
      // función auxiliar que permite recursividad tail recursive
      @tailrec
      def go(n: BigInt, acum: BigInt) :BigInt = {
        if (n == 0 || n == 1) acum
        else go(n-1, n*acum)
      }
      // ejecución de la función auxiliar
      go(n,acum = 1)
    }

    // Comprobación de valor correcto de fila y columna
    if( fila < columna) {
      println("ERROR: la fila no puede ser menor a la columna")
      return -1 //ERROR: la fila no puede ser menor a la columna
    }
    // Cálculo del valor a partir de la ecuación y la función recursiva para obtener el factorial
    factorial(fila) / (factorial(columna) * factorial(fila-columna))
  }


  /**
   * Ejercicio 2: Contador de posibles cambios de moneda
   */

  /**
   * Función para calcular los cambios posibles de moneda mediante recursividad
   * @param cantidad valor a devolver
   * @param monedas lista de tipos de monedas
   * @return el numero de cambios posibles
   */
  def listarCambiosPosibles(cantidad: Int, monedas: List[Int] ) : Int = {

    //Formas de dar cambio de un valor 0
    if (cantidad == 0) {
      // Solo se puede devolver una cantidad de 0 de 1 forma
      1
    }
    else if ( (cantidad>=0 && monedas.isEmpty) || cantidad < 0) {
      // Formas de dar cambio de un valor positivo si no hay monedas
      // y formas de dar cambio a un valor negativo
      // => la condición puede ser también (monedas.isEmpty) || cantidad < 0)
      0
    }
    else {
      // Sumar las formas de cambio posibles actuales con:
      // 1: el mismo tipo de monedas para un valor igual a la cantidad menos el valor de la primera moneda de la lista
      // 2: la misma cantidad con los tipos de monedas restantes al eliminar el primer tipo
      listarCambiosPosibles(cantidad - monedas.head, monedas) + listarCambiosPosibles(cantidad, monedas.tail)

      // De esta forma se tiene en cuenta la varianza de la cantidad y los diferentes tipos de monedas que pueden
      // sumar esa cantidad
    }

  }


  /**
   * Ejercicio 3: Búsqueda en colecciones ordenadas
   */

  /**
   * Búsqueda en colecciones ordenadas mediante el método de busqueda generica a saltos
   * @param buscado elemento a buscar
   * @param coleccion colección donde se busca DEBE ESTAR ORDENADA
   * @param mayor función de condición de ordenación mayor
   * @tparam A
   * @return posición del elemento a buscar en la colección, si no se encuentra se devuelve -1
   */
  def busquedaASaltosGenerica [A] (buscado: A, coleccion: List[A], mayor: (A, A) => Boolean)  : Int = {

    def busquedaLineal(coleccion: List[A]) : Int = {
      for (i <- coleccion.indices) {
        if (coleccion(i) == buscado) {
          return i
        }
      }
      -1
    }

    def go(coleccion: List[A], tam_bloque: Int)  : Int = {

      // Si la colección es vacía no se va a encontrar el elemento
      if (coleccion.isEmpty) {
        return -1
      }

      // Comparar el elemento buscado con el final del primer bloque
      val primer_bloque = coleccion.take(tam_bloque)

      if (buscado == primer_bloque.last) {
        // Si el elemento buscado es el ultimo elemento de este bloque el resultado es la posición del elemento
        primer_bloque.length-1
      }
      else if ( mayor(buscado, primer_bloque.last)) {
        // Si el elemento buscado es mayor entonces se salta este bloque
        val restante = coleccion.takeRight(coleccion.length-tam_bloque)
        val pos_siguiente_bloque = go(restante, tam_bloque)
        // Si se encuentra en el siguiente bloque se devuelve la posición
        if (pos_siguiente_bloque != -1) {
          tam_bloque+pos_siguiente_bloque
        }
        else {
          // Si no se encuentra se devuelve -1
          -1
        }
      }
      else {
        // Si el elemento buscado es menor al último elemento del bloque entonces
        // se hace una búsqueda lineal en el bloque actual
        busquedaLineal(primer_bloque)
      }
    }


    // Si la colección es vacía no se va a encontrar el elemento
    if (coleccion.isEmpty) {
      return -1
    }

    // Tamaño de bloque es la raíz cuadrada de la longitud de la colección
    val tam_bloque = math.sqrt(coleccion.length).toInt

    // Se van dando saltos por la colección haciendo la búsqueda
    go(coleccion,tam_bloque)

  }


  /**
   * Búsqueda en colecciones ordenadas mediante el método fibonacci
   * @param buscado   valor a buscar
   * @param coleccion colección donde se busca DEBE ESTAR ORDENADA
   * @param mayor     función de condición de ordenación mayor
   * @param menor     función de condición de ordenación menor
   * @tparam A
   * @return posición del elemento a buscar en la colección, si no se encuentra se devuelve -1
   */
  def busquedaMetodoFibonacci[A] (buscado: A, coleccion: List[A],  mayor: (A, A) => Boolean, menor: (A, A) => Boolean )  : Int = {

    def go(_f0: Int, _f1: Int, _f2: Int, _inicio :Int, _n : Int ) : Int = {

      var f0 = _f0
      var f1 = _f1
      var f2 = _f2
      var inicio = _inicio

      // se determina el valor de indice a considerar, calculando el minimo entre (f0 + inicio)
      // y el tamaño de la secuencia n (como es un indice sera n-1)
      val indice = Math.min(f0 + inicio, _n-1)

      if (mayor(buscado, coleccion(indice))) {
        // si el valor a buscar es mayor, entonces los numeros de Fibonacci a considerar para la siguiente iteracion son
        // f0 = f1-f0 y f1 = f0 y el nuevo valor de inicio a usar será el tomado por indice.
        // por lo tanto tomando f2 = f1 entonces f1 = f0 y f0 = f2-f0 = f1(anterior)-f0
        f2  = f1
        f1 = f0
        f0 = f2-f0
        inicio = indice
      }
      else if (menor(buscado, coleccion(indice))) {
        // en caso contrario, si el valor buscado es menor, los numeros de Fibonacci a considerar son f0 = f0-(f1-f0)
        // y f1 = f1-f0  no cambia el valor de inicio
        // por lo tanto f2 = f0 entonces f1 = f1-f0 y f0 = f2-f1 = f0-(f1-f0)
        f2  = f0
        f1 = f1-f0
        f0 = f2-f1
      }
      else {
        // si el valor almacenado en la posicion dada indice por coincide con el valor a buscar, hemos terminado la busqueda
        return indice
      }

      if (f2 > 1) {
        return go(f0, f1, f2, inicio, _n)
      }

      -1
    }


    // Si la colección esta vacia no se va a encontrar el elemento
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

    // Comienza la búsqueda con el método fibonacci
    go(f0,f1,f2,inicio,n)
  }

  //Funcion de ordenación mayor
  def mayor(a : Int, b : Int): Boolean = a > b

  //Función de ordenación menor
  def menor(a : Int, b : Int): Boolean = a < b



  /**
   * Método main: No es necesario pero se usa para tener un feedback visual
   * @param args argumentos
   */
  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

    val filas : Int  = 15

    // Se muestra el triangulo según el número de filas establecido
    for (row <- 0 to filas) {
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(row, col) + " ")
      println()
    }
    println()

    // Se muestra el valor que debe ocupar la fila 10 y columna 5
    println("El valor de la fila 10 columna 5 es = "+calcularValorTrianguloPascal(10, 5))


    println("\n................... Cambio de Moneda ...................")

    val cantidad : Int = 4
    val monedas : List[Int] =  List(1,2,3)

    println("Cantidad: "+cantidad)
    println("Monedas: "+monedas)
    println("Número de cambios posibles: "+ listarCambiosPosibles(cantidad,monedas))


    println("\n................... Búsqueda genérica ...................")

    val buscado : Int = 55
    val coleccion: List[Int] = List(1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987)

    println("Buscado: "+buscado)
    print("Colección: ")
    coleccion.foreach(item => print(item+" "))
    println()
    println()
    println("SOLUCIÓN BÚSQUEDA A SALTOS:")
    val pos_saltos = busquedaASaltosGenerica(buscado, coleccion, mayor)
    if (pos_saltos == -1) println("No se ha encontrado ese elemento en la lista")
    else println("Posición: "+pos_saltos)
    println()
    println("SOLUCIÓN BÚSQUEDA MÉTODO DE FIBONACCI:")
    val pos_fibonacci = busquedaMetodoFibonacci(buscado, coleccion, mayor, menor)
    if (pos_fibonacci == -1) println("No se ha encontrado ese elemento en la lista")
    else println("Posición: "+pos_fibonacci)


  }

}
