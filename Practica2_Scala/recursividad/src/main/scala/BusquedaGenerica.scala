import scala.::

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

    // Tamaño de bloque es la raíz cuadrada de la longitud de la colección
    val tam_bloque = math.sqrt(coleccion.length).toInt

    // Se van dando saltos por la colección haciendo la búsqueda
    saltosColeccion(buscado,coleccion,tam_bloque)
  }

  def saltosColeccion(buscado: Int, coleccion: List[Int], tam_bloque: Int)  : Int = {

    // Comparar el elemento buscado con el final del primer bloque
    val primer_bloque = coleccion.take(tam_bloque)

    if (buscado == primer_bloque.last) {
      // Si el elemento buscado es el ultimo elemento de este bloque
      tam_bloque
    }
    else if (buscado > primer_bloque.last) {
      // Si el elemento buscado es mayor entonces se salta este bloque
      val restante = coleccion.takeRight(coleccion.length-tam_bloque)
      tam_bloque+saltosColeccion(buscado, restante,tam_bloque)
    }
    else {
      // Si el elemento buscado es menor al último elemento del bloque entonces
      // se hace una busqueda lineal en el bloque actual
      busquedaLineal(buscado, primer_bloque)
    }
  }

  def busquedaLineal(buscado: Int, coleccion: List[Int]) : Int = {
    var pos : Int = -1
    for (i <- coleccion.indices) {
      if (coleccion(i) == buscado) {
        pos = i
      }
    }
    pos
  }





  def numeroFibonacciSuperior(n : Int) : Int = {
    for (i <- 0 to n) {
      if (fibonacci(i) >= n) {
        return fibonacci(i)
      }
    }
    -1
  }

  def anteriorFibonacci(n : Int) : Int = {
    var anterior = fibonacci(0)
    for (i <- 0 to n) {
      if (fibonacci(i) >= n) {
        return anterior
      }
      else {
        anterior = fibonacci(i)
      }
    }
    anterior
  }

  def busquedaMetodoFibonacci(buscado: Int, coleccion: List[Int])  : Int = {

    // sea n el numero de elementos en la lista o secuencia de elementos en los que buscar (que deben estar ordenada).
    val n =  coleccion.length

    // Se determina el primero numero de Fibonacci superior o igual a n. Sea este numero f2 y los dos anteriores f1 y f0.
    val f2 = numeroFibonacciSuperior(n)
    val f1 = anteriorFibonacci(f2)
    val f0 = anteriorFibonacci(f1)

    println("f2: "+f2)
    println("f1: "+f1)
    println("f0: "+f0)

    // Se hace que la variable inicio tenga valor -1
    val inicio = -1

    metodoFibonnaci(buscado,coleccion,f0,f1,inicio)
  }

  def metodoFibonnaci(buscado : Int, coleccion : List[Int], v_f0 : Int,v_f1 : Int, v_inicio : Int) : Int = {
    var f0 = v_f0
    var f1 = v_f1
    var inicio = v_inicio

    // se determina el valor de indice a considerar, calculando el minimo entre (f0 + inicio)
    // y el tamaño de la secuencia n.
    var indice = Math.min(f0+inicio, coleccion.length)
    println(indice)

    // si el valor almacenado en la posicion dada indice por coincide con el valor a buscar, hemos terminado.
    if (coleccion(indice) == buscado) {
      println("ola"+indice)
      indice
    }
    else if (coleccion(indice) > buscado) {
      // si el valor a buscar es mayor, entonces los numeros de Fibonacci a considerar para la siguiente iteracion son
      // f0 = f1-f0 y f1 = f0 y el nuevo valor de inicio a usar será el tomado por indice.
      f0 = f1-f0
      f1 = f0
      inicio = indice
      metodoFibonnaci(buscado,coleccion,f0,f1,inicio)
    }
    else {
      // en caso contrario, los numeros de Fibonacci a considerar son f0 = f0-(f1-f0) y f1 = f1-f0  no cambia el valor de inicio.
      f0 = f0-(f1-f0)
      f1 = f1-f0
      metodoFibonnaci(buscado,coleccion,f0,f1,v_inicio)
    }
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
