

/**
 * Clase companion para representar conjuntos
 * @param funcionCaracteristica dato miembro que permite almacenar la función característica que representa el conjunto
 */
class Conjunto (val funcionCaracteristica: Int => Boolean) {

  /**
   * Método que indica si un valor dado pertenece o no al conjunto
   * @param x valor entero a comprobar
   * @return true o false si este valor pertenece o no al conjunto
   */
  def apply(x : Int): Boolean = funcionCaracteristica(x)

  /**
   * Método que ofrece una visión del contenido del conjunto
   * se itera sobre un rango de valores dado la constante LIMITE --> [-LIMITE, +LIMITE]
   * Filtrar los valores en [-LIMITE, +LIMITE] y obtener aquellos que cumplan la función característica (método apply)
   * @return se muestran aquellos que pertenecen al conjunto
   */
  override def toString: String = (-Conjunto.LIMITE to Conjunto.LIMITE).filter(this.apply).mkString("Conjunto{", ",", "}")

  /**
   * Dados dos objetos de la clase Conjunto produce su unión
   * @param conjunto2 el otro conjunto con el que realizar la union
   * @return Conjunto union de ambos
   */
  def union(conjunto2: Conjunto): Conjunto = new Conjunto((x : Int) => this(x) || conjunto2(x))

  /**
   * Dados dos objetos de la clase Conjunto produce su intersección
   * @param conjunto2 el otro conjunto con el que realizar la intersección
   * @return Conjunto intersección de ambos
   */
  def interseccion(conjunto2: Conjunto): Conjunto = new Conjunto((x : Int) => this(x) && conjunto2(x))

  /**
   * Dados dos objetos de la clase Conjunto produce su diferencia
   * el conjunto resultante está formado por aquellos valores que pertenecen al primer conjunto, pero no al segundo
   * @param conjunto2 el otro conjunto con el que realizar la diferencia
   * @return Conjunto diferencia de ambos
   */
  def diferencia(conjunto2: Conjunto): Conjunto = new Conjunto((x : Int) => this(x) && !conjunto2(x))

  /**
   * Método para filtrar el conjunto
   * @param condicion condición de filtrado
   * @return Conjunto con los elementos que cumplen la condición indicada
   */
  def filtrar(condicion: Int => Boolean): Conjunto = new Conjunto((x : Int) => this(x) && condicion(x))


  /**
   * Método que  comprueba si un determinado predicado se cumple para todos los elementos del conjunto
   * @param predicado condición
   * @return true o false si se cumple o no el predicado o condición
   */
  def paraTodo(predicado: Int => Boolean): Boolean = {
    @annotation.tailrec
    def iterar(x : Int) : Boolean = {
      if(x > Conjunto.LIMITE) true      // Si se supera el límite, todos los valores del conjunto cumplen el predicado
      else if (!this(x)) iterar(x+1)    // Si el valor actual no pertenece en el conjunto se sigue iterando
      else predicado(x) && iterar(x+1)  // Si el valor si pertenece al conjunto se comprueba si el valor cumple el predicado
                                        // y se sigue iterando para comprobar el resto del conjunto
    }
    iterar(-Conjunto.LIMITE)
  }

  /**
   * Método que  determina si un conjunto contiene al menos un elemento para el que se cumple un cierto predicado
   * Se basa en el método paraTodo:
   *    usando como predicado para el método paraTodo el contrario al predicado de este método existe y el resultado negado
   *    de esta manera se comprueba que si no el conjunto completo cumple la condición contraria significa que hay algún elemento
   *    del conjunto que si cumple la condición actual
   * @param predicado condición
   * @return true o false si se cumple o no el predicado o condición para al menos un elemento del conjunto
   */
  def existe(predicado: Int => Boolean): Boolean = !paraTodo((x : Int) => !predicado(x))

  /**
   * Método que transforma un conjunto en otro aplicando una cierta función
   * Se basa en el método existe:
   *    creando un nuevo conjunto donde la función característica determina que debe existir un elemento que al
   *    aplicarle la función f sea igual al valor x, de esta forma los valores del nuevo conjunto tendrán aplicada la función f
   * @param f función a aplicar
   * @return Conjunto tras aplicar la función
   */
  def map(f: Int => Int): Conjunto = new Conjunto((x : Int) => this.existe((y : Int) => f(y) == x))

}

/**
 * Objeto companion de la clase Conjunto: para variables y métodos estáticos
 */
object Conjunto {

  /**
   * Constante que representa un rango de valores
   */
  final val LIMITE = 10

  /**
   * Método factoría apply que permite crear instancias sin usar la palabra new
   * este método es independiente al método apply de la clase Conjunto
   * @param funcionCaracteristica
   * @return
   */
  def apply(funcionCaracteristica: Int => Boolean) : Conjunto = {
    new Conjunto(funcionCaracteristica)
  }

  /**
   * Creación de un conjunto dado por un único elemento
   * Método estático de Conjunto
   * @return
   */
  def conjuntoUnElemento(x: Int): Conjunto = {
    // La representación de este conjunto es: valor == x
    new Conjunto((valor: Int) => valor == x)
  }


  /**
   * Método main: No es necesario pero se usa para tener un feedback visual
   * @param args argumentos
   */
  def main(args: Array[String]): Unit = {

    val negativos = Conjunto((x : Int) => x < 0)
    println(negativos)

    val unico = Conjunto.conjuntoUnElemento(1)
    println(unico)

    val union = unico.union(negativos)
    println(union)

    val interseccion = unico.interseccion(negativos)
    println(interseccion)

    val c2 = Conjunto.conjuntoUnElemento(-4)

    val interseccion2 = c2.interseccion(negativos)
    println(interseccion2)

    val diferencia = negativos.diferencia(c2)
    println(diferencia)

    val filtrados = negativos.filtrar((x : Int) => x % 2 == 0)
    println(filtrados)

    val resultado = negativos.paraTodo((x : Int) => x % 2 == 0)
    println(resultado)

    val existe = negativos.existe((x : Int) => x == -1)
    println(existe)

    val mapeado = negativos.map((x : Int) => x*(-1))
    println(mapeado)

    val f_pares = (x : Int) => x % 2 == 0
    val f_negativos = (x : Int) => x < 0

    val c1 = Conjunto(f_negativos)
    val c33 = Conjunto(f_pares)
    val inter = c1.interseccion(c33)
    println(c1(1))
    println(c33(1))
    println(inter(1))
    println(Conjunto((x : Int) => (x > 1) || (x < 1)))

  }

}
