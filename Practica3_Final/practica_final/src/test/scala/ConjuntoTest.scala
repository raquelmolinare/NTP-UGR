import org.scalatest.funsuite.AnyFunSuite

class ConjuntoTest extends AnyFunSuite {

  // se asigna las distintas funciones características a usar
  val f_negativos: Int => Boolean = (x : Int) => x < 0
  val f_pares: Int => Boolean = (x : Int) => x % 2 == 0
  val f_impares: Int => Boolean = (x : Int) => x % 2 != 0
  val f_naturales: Int => Boolean = (x : Int) => x >= 0
  val f_negativos_pares: Int => Boolean = (x : Int) => (x < 0) && (x % 2 == 0)
  val f_negativos_impares: Int => Boolean = (x : Int) => (x < 0) && (x % 2 != 0)


  test("Test apply: buscado=1, conjunto= números naturales") {
    assert(Conjunto(f_naturales).apply(1))
  }

  test("Test union: c1=negativos, c2=pares") {
    val c1 = Conjunto(f_negativos)
    val c2 = Conjunto(f_pares)
    val union = c1.union(c2)

    // para [-LIMITE, LIMITE] -->  (c1(x) || c2(x)) será true o false si esta en uno de los dos o en ninguno
    // el mismo resultado que devuelva (c1(x) || c2(x)) tendrá que devolver union(x)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => (c1(x) || c2(x)) == union(x)))
  }

  test("Test union: c1=impares, c2=pares") {
    val c_pares = Conjunto(f_pares)
    val c_impares = Conjunto(f_impares)
    val union = c_pares.union(c_impares)

    // para [-LIMITE, LIMITE] --> todos los valores tienen que estar en la union
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => union(x)))
  }

  test("Test intersección: c1=negativos, c2=pares") {
    val c1 = Conjunto(f_negativos)
    val c2 = Conjunto(f_pares)
    val inter = c1.interseccion(c2)

    // Primera forma
    // para [-LIMITE, LIMITE] -->  (c1(x) && c2(x)) será true o false si está en ambos conjuntos o en ninguno
    // el mismo resultado que devuelva (c1(x) && c2(x)) tendrá que devolver inter(x)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => (c1(x) && c2(x)) == inter(x)))

    // Segunda forma:
    // la intersección de los negativos y los pares son aquellos pares que son negativos
    // por lo que si el valor está en inter también tiene que estar en c_negativos_pares y si no está en uno
    // no está en ninguno de los dos
    val c_negativos_pares = Conjunto(f_negativos_pares)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => inter(x) == c_negativos_pares(x)))

  }

  test("Test intersección: c1=impares, c2=pares") {
    val c_pares = Conjunto(f_pares)
    val c_impares = Conjunto(f_impares)
    val inter = c_pares.interseccion(c_impares)

    // la intersección de los números pares e impares debe ser un conjunto vacía
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => !inter(x)))
  }

  test("Test diferencia: c1=negativos, c2=pares") {
    val c1 = Conjunto(f_negativos)
    val c2 = Conjunto(f_pares)
    val diferencia = c1.diferencia(c2)

    // Primera forma
    // para [-LIMITE, LIMITE] -->  (c1(x) && !c2(x)) será true o false si está en c1 pero no en c2
    // el mismo resultado que devuelva (c1(x) && !c2(x)) tendrá que devolver diferencia(x)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => (c1(x) && !c2(x)) == diferencia(x)))

    // Segunda forma:
    // la diferencia de los negativos y los pares son aquellos negativos que son impares
    // por lo que si el valor está en diferencia también tiene que estar en c_negativos_impares y si no está en uno
    // no está en ninguno de los dos
    val c_negativos_impares = Conjunto(f_negativos_impares)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => diferencia(x) == c_negativos_impares(x)))
  }

  test("Test filtrar: conjunto=negativos, condición=números pares") {
    val c = Conjunto(f_negativos)
    val filtrados = c.filtrar(f_pares)

    // Primera forma
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => (c(x) && f_pares(x)) == filtrados(x)))


    // Segunda forma:
    // al filtrar el conjunto de negativos con la condición de números se deben obtener un conjunto de números pares negativos
    // por lo que si el valor está en filtrados también tiene que estar en c_negativos_pares y si no está en uno
    // no está en ninguno de los dos
    val c_negativos_pares = Conjunto(f_negativos_pares)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => filtrados(x) == c_negativos_pares(x)))
  }

  test("Test paraTodo: conjunto=naturales, predicado=números pares") {
    val c_naturales = Conjunto(f_naturales)
    assert(!c_naturales.paraTodo(f_pares))
  }

  test("Test paraTodo: conjunto=negativos, predicado=(x : Int) => x*(-1)> 0") {
    val c_negativos = Conjunto(f_negativos)
    assert(c_negativos.paraTodo((x : Int) => x*(-1)> 0))
  }

  test("Test existe: conjunto=naturales, predicado=números pares") {
    val c_naturales = Conjunto(f_naturales)
    assert(c_naturales.existe(f_pares))
  }

  test("Test existe: conjunto=naturales, predicado=números negativos") {
    val c_naturales = Conjunto(f_naturales)
    assert(!c_naturales.existe(f_negativos))
  }

  test("Test map: conjunto=negativos, predicado=(x : Int) => x*(-1)") {
    val c_negativos = Conjunto(f_negativos)
    val predicado = (x : Int) => x*(-1)
    val mapeados = c_negativos.map(predicado)

    // Primera forma
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => c_negativos.existe((y : Int) => predicado(y) == x) == mapeados(x)))


    // Segunda forma:
    // el predicado (x : Int) => x*(-1) en map para los números negativos los convierte en positivos
    // por lo que si el valor está en mapeados también tiene que estar en c_positivos y si no está en uno
    // no está en ninguno de los dos
    val c_positivos = Conjunto((x : Int) => x > 0)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => mapeados(x) == c_positivos(x)))
  }

  test("Test map: conjunto=negativos, predicado=(x : Int) => x*2") {
    val c_negativos = Conjunto(f_negativos)
    val predicado = (x : Int) => x*2
    val mapeados = c_negativos.map(predicado)

    // Primera forma
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => c_negativos.existe((y : Int) => predicado(y) == x) == mapeados(x)))


    // Segunda forma:
    // el predicado (x : Int) => x*2 en map convierte todos los valores del conjunto en valores pares
    // por lo que si el valor está en mapeados también tiene que estar en c_negativos_pares y si no está en uno
    // no está en ninguno de los dos
    val c_negativos_pares = Conjunto(f_negativos_pares)
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(x => mapeados(x) == c_negativos_pares(x)))
  }

  test("Test conjuntoUnElemento:") {
    val unico = Conjunto.conjuntoUnElemento(1)

    assert(unico(1))
    assert(!unico.existe((x : Int) => (x > 1) || (x < 1)))
  }

}
