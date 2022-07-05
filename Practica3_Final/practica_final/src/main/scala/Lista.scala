import scala.annotation.tailrec

/**
 * Interfaz genérica para la lista
 * sealed limita la posibilidad de extender una clase al mismo archivo en que se declara
 * trait es un tipo de clase que permite la herencia múltiple
 * +A es una sofisticación, permite que una lista de Nothing extienda de Lista, que una lista de enteros
 * pueda heredar de esta lista..
 *
 * @param A
 */
sealed trait Lista[+A]

/**
 * Objeto para definir lista vacía
 */
case object Nil extends Lista[Nothing]

/**
 * Clase para definir la lista como compuesta por elemento inicial
 * (cabeza) y resto (cola)
 * @param cabeza elemento inicial
 * @param cola elementos menos el elemento inicial
 * @param A
 */
case class Cons[+A](cabeza : A, cola : Lista[A]) extends Lista[A]

/**
 * Companion object Lista
 */
object Lista {

  /**
   * Método para permitir crear listas sin usar new
   * mediante el uso de función parcial asociada al uso de la expresión match
   * @param elementos secuencia de elementos a incluir en la lista
   * @param A
   * @return
   */
   def apply[A](elementos : A*) : Lista[A] = elementos.size match {
     case 0 => Nil // Lista vacía
     case _ => Cons(elementos.head, apply(elementos.tail: _*))
   }

  /**
   * Método para obtener la longitud de una lista
   * @param lista
   * @param A
   * @return
   */
  def longitud[A](lista : Lista[A]) : Int = lista match {
    case Nil => 0
    case Cons(_, cola) => 1+longitud(cola)
  }

  /**
   * Método para sumar los valores de una lista de enteros
   * @param enteros
   * @return
   */
  def sumaEnteros(enteros : Lista[Int]) : Double = enteros match {
    case Nil => 0
    case Cons(cabeza,cola) => cabeza + sumaEnteros(cola)
  }

  /**
   * Método para multiplicar los valores de una lista de enteros
   * @param enteros
   * @return
   */
  def productoEnteros(enteros : Lista[Int]) : Double = enteros match {
    case Nil => 1
    case Cons(cabeza,cola) => cabeza * productoEnteros(cola)
  }

  /**
   * Metodo para agregar el contenido de dos listas
   * De este modo se va concatenando cabeza con cola de la lista1, cuando esté la lista 1 completa se incluye como
   * cola la lista2
   * @param lista1
   * @param lista2
   * @param A
   * @return
   */
  def concatenar[A](lista1: Lista[A], lista2: Lista[A]): Lista[A] = lista1 match {
    case Nil => lista2
    case Cons(cabeza, cola) => Cons(cabeza, concatenar(cola,lista2))
  }

  /**
   * Función de utilidad para aplicar una función de forma sucesiva a los
   * elementos de la lista con asociatividad por la derecha
   * se aplica la función a la cabeza de la lista y se vuelve a llamar a la función de manera recursiva hasta termina
   * donde ya se devuelve el valor neutro
   * @param lista
   * @param neutro
   * @param funcion
   * @param A
   * @param B
   * @return
   */
  def foldRight[A, B](lista : Lista[A], neutro : B)(funcion : (A, B) => B): B = lista match {
    case Nil => neutro
    case Cons(cabeza, cola) => funcion(cabeza, foldRight(cola, neutro)(funcion))
  }

  /**
   * Suma mediante foldRight
   * @param listaEnteros
   * @return
   */
  def sumaFoldRight(listaEnteros : Lista[Int]) : Double = foldRight(listaEnteros, 0)((x,y) => x + y)

  /**
   * Producto mediante foldRight
   * @param listaEnteros
   * @return
   */
  def productoFoldRight(listaEnteros : Lista[Int]) : Double = foldRight(listaEnteros, 1)((x,y) => x * y)


  /**
   * Reemplaza la cabeza por nuevo valor. Se asume que si la lista esta vacía
   * se devuelve una lista con el nuevo elemento
   *
   * @param lista
   * @param cabezaNueva
   * @param A
   * @return
   */
  def asignarCabeza[A](lista : Lista[A], cabezaNueva : A) : Lista[A] = lista match {
    case Nil => Cons(cabezaNueva, Nil)
    case Cons(_, cola) => Cons(cabezaNueva, cola)
  }

  /**
   * Devuelve el primer elemento de la lista
   * (si no esta vacía). Por eso se devuelve Option
   *    Option tiene dos casos (Representados como dos subclases): Some o None
   * @param lista
   * @tparam A
   * @return
   */
  def head[A](lista : Lista[A]) : Option[A] = lista match {
    case Nil => None
    case Cons(cabeza, _) => Some(cabeza)
  }

  /**
   * Elimina el elemento cabeza de la lista
   * @param lista
   * @param A
   * @return
   */
  def tail[A](lista : Lista[A]): Lista[A] = lista match {
    case Nil => Nil
    case Cons(_,cola) => cola
  }

  /**
   * Elimina los n primeros elementos de una lista
   * Va eliminando la cabeza de la lista hasta que n sea 0
   * @param lista lista con la que trabajar
   * @param n numero de elementos a eliminar
   * @param A tipo de datos
   * @return
   */
  @tailrec
  def eliminar[A](lista : Lista[A], n: Int) : Lista[A] = lista match {
    case Nil => Nil
    case Cons(_, cola) => if (n > 0) eliminar(cola, n-1) else lista
  }

  /**
   * Elimina elementos mientras se cumple la condición pasada como
   * argumento
   * va eliminando la cabeza de la lista mientras se cumpla la condición para el primer elemento
   * @param lista lista con la que trabajar
   * @param criterio predicado a considerar para continuar con el borrado
   * @param A tipo de datos a usar
   * @return
   */
  @tailrec
  def eliminarMientras[A](lista : Lista[A], criterio: A => Boolean) : Lista[A] = lista match {
    case Nil => Nil
    case Cons(cabeza, cola) => if(criterio(cabeza)) eliminarMientras(cola,criterio) else lista
  }

  /**
   * Elimina el ultimo elemento de la lista. Aquí no se pueden compartir
   * datos en los objetos y hay que generar una nueva lista copiando
   * datos
   * Genera una nueva lista a partir de la actual incluyendo la cabeza en cada llamada, cuando en la cola queda el
   * último elemento se devuelve Nil para no incluirlo
   * @param lista lista con la que trabajar
   * @param A tipo de datos de la lista
   * @return
   */
  def eliminarUltimo[A](lista : Lista[A]) : Lista[A] = lista match {
    case Nil => Nil
    case Cons(cabeza, cola) => if (longitud(lista) > 1) Cons(cabeza, eliminarUltimo(cola)) else Nil
  }

  /**
   * foldLeft con recursividad tipo tail
   * va atravesando el conjunto de izquierda a derecha aplicando la función
   * @param lista lista con la que trabajar
   * @param neutro elemento neutro
   * @param funcion funcion a aplicar
   * @param A parametros de tipo de elementos de la lista
   * @param B parametro de tipo del elemento neutro
   * @return
   */
  @annotation.tailrec
  def foldLeft[A, B](lista : Lista[A], neutro: B)(funcion : (B, A) => B): B = lista match {
    case Nil => neutro
    case Cons(cabeza, cola) => foldLeft(cola, funcion(neutro,cabeza))(funcion)
  }

}