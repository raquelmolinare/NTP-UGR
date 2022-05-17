/**
 * Ejercicio 2: Contador de posibles cambios de moneda
 */
object CambiosDeMoneda {

  /**
   * Función para calcular los cambios posibles de moneda mediante recursividad
   * @param cantidad valor a devolver
   * @param monedas lista de tipos de monedas
   * @return
   */
  def listarCambiosPosibles(cantidad: Int, monedas: List[Int] ) : Int = {

    //Formas de dar cambio de un valor 0
    if (cantidad == 0) {
      // Solo se puede devolver una cantidad de 0 de 1 forma: Devolviendo 0
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
   * Método main: No es necesario pero se usa para tener un feedback visual
   * @param args argumentos
   */
  def main(args: Array[String]) {
    println("................... Cambio de Moneda ...................")

    val cantidad : Int = 4
    val monedas : List[Int] =  List(1,2,3)

    println("Cantidad: "+cantidad)
    println("Monedas: "+monedas)
    println("Número de cambios: "+ listarCambiosPosibles(cantidad,monedas))
  }

}
