import org.scalatest.funsuite.AnyFunSuite

class ListaTest extends AnyFunSuite {

  test("Test apply") {
    assert(Lista(1, 2, 3, 4).isInstanceOf[Cons[Int]])
    assert(Lista('a', 'b', 'c', 'd').isInstanceOf[Cons[Char]])
    assert(Lista(1.5, 2.5, 3.5, 4.5).isInstanceOf[Cons[Double]])
    assert(Lista("Nuevas", "Tecnologías", "de", "programación").isInstanceOf[Cons[String]])
  }

  test("Test longitud") {
    assert(Lista.longitud(Lista(1, 2, 3, 4)) == 4)
    assert(Lista.longitud(Lista()) == 0)
    assert(Lista.longitud(Lista("a")) == 1)
  }

  test("Test sumaEnteros") {
    assert(Lista.sumaEnteros(Lista(1, 2, 3, 4)) == 10)
    assert(Lista.sumaEnteros(Lista(0)) == 0)
    assert(Lista.sumaEnteros(Lista()) == 0)
  }

  test("Test productoEnteros") {
    assert(Lista.productoEnteros(Lista(1, 2, 3, 4)) == 24)
    assert(Lista.productoEnteros(Lista(0,0)) == 0)
    assert(Lista.productoEnteros(Lista()) == 1)
  }

  test("Test concatenar") {
    val lista1 = Lista(0,1,2)
    val lista2 = Lista(3,4,5)
    val lista_vacia = Lista()

    assert(Lista.concatenar(lista1,lista2) == Lista(0,1,2,3,4,5))
    assert(Lista.concatenar(lista2,lista1) == Lista(3,4,5,0,1,2))
    assert(Lista.concatenar(lista_vacia,lista_vacia) == Nil)
    assert(Lista.concatenar(lista_vacia,lista_vacia) == Lista())
    assert(Lista.concatenar(lista_vacia,lista2) == Lista(3,4,5))

  }

  test("Test foldRight") {
    val lista = Lista(1,1,1)
    def f(a: Int, b: Int) = a + b + 1
    assert(Lista.foldRight(lista,0)(f) == 6)
  }

  test("Test sumaFoldRight") {
    val lista = Lista(1,1,1)
    assert(Lista.sumaFoldRight(lista) == 3)

    val lista_vacia = Lista()
    assert(Lista.sumaFoldRight(lista_vacia) == 0)
  }

  test("Test productoFoldRight") {
    val lista = Lista(3,4,5)
    assert(Lista.productoFoldRight(lista) == 60.0)

    val lista_vacia = Lista()
    assert(Lista.productoFoldRight(lista_vacia) == 1.0)
  }

  test("Test asignarCabeza y head") {
    val lista = Lista(1,1,1)
    val nueva_cabeza = 0
    val nueva_lista = Lista.asignarCabeza(lista,nueva_cabeza)
    assert(Lista.head(nueva_lista) == Some(nueva_cabeza))
  }

  test("Test head") {
    assert(Lista.head(Lista()) == None)
  }

  test("Test tail") {
    assert(Lista.tail(Lista()) == Nil)
    assert(Lista.tail(Lista(-1,0,1)) == Lista(0,1))
    assert(Lista.tail(Lista("hola","adios")) == Lista("adios"))
  }

  test("Test eliminar") {
    assert(Lista.eliminar(Lista(4,5,5,6),2) == Lista(5,6))
    assert(Lista.eliminar(Lista("hola","adios"),2) == Nil)
    assert(Lista.eliminar(Lista("hola","adios"),10) == Nil)
  }

  test("Test eliminarMientras") {
    assert(Lista.eliminarMientras(Lista(4,5,5,6), (x : Int) => x % 2 == 0) == Lista(5,5,6))
    assert(Lista.eliminarMientras(Lista(0,2,4,6), (x : Int) => x % 2 == 0) == Nil)
    assert(Lista.eliminarMientras(Lista("hola", "hola","adios"),(x : String) => x == "hola") == Lista("adios"))
  }

  test("Test eliminarUltimo") {
    assert(Lista.eliminarUltimo(Lista(4,5,5,6)) == Lista(4,5,5))
    assert(Lista.eliminarUltimo(Lista(6)) == Nil)
    assert(Lista.eliminarUltimo(Lista("hola", "hola","adios")) == Lista("hola", "hola"))
  }

  test("Test foldLeft") {
    val lista = Lista(1,2,3)
    def f(a: Int, b: Int) = a - b + 1
    assert(Lista.foldLeft(lista,0)(f) == -3)
  }
}
