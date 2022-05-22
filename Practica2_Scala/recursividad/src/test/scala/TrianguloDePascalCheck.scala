import TrianguloDePascal.calcularValorTrianguloPascal
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object TrianguloDePascalCheck extends Properties("Triangulo De Pascal Check") {

  // Se establece el máximo
  val MAXIMO = 20

  // Se generan los valores de fila y columna para los bordes
  val coordenadasExtremos: Gen[(Int, Int)] = for {
    // se genera numero de fila: valor comprendido entre 0 y MAXIMO
    // (MAXIMO no esta incluido)
    fila <- Gen.choose(0, MAXIMO)

    // se genera numero de columna: o 0 o el valor de fila. Esto
    // asegura que se trata de un valor de los extremos (X,0) o
    // (X,X)
    columna <- Gen.oneOf(0, fila)
  } yield (fila, columna)

  property("Elementos en lados del triangulo valen 1") = {
    forAll(coordenadasExtremos) { i => {
        val resultado = calcularValorTrianguloPascal(i._1, i._2)
        println("Fila = "+i._1 +"\tColumna = "+ i._2+ " \tResultado = "+resultado)
        resultado == 1
      }
    }
  }


  // Se generan los valores de fila y columna para los valores interiores
  val coordenadasInternas: Gen[(Int, Int)] = for {
    // se genera numero de fila: valor comprendido entre 2 y MAXIMO (1,MAXIMO) o [2,MAXIMO)
    fila <- Gen.choose(2, MAXIMO)

    // se genera numero de columna: valor comprendido entre 1 y la fila-1 [1,fila)
    columna <- Gen.oneOf(1, fila-1)
  } yield (fila, columna)

  property("Elementos internos del triangulo valen la suma de los dos elementos superiores") = {
    forAll(coordenadasInternas) { i => {
        val sup_izq = calcularValorTrianguloPascal(i._1-1, i._2-1)
        val sup_der = calcularValorTrianguloPascal(i._1-1, i._2)
        val resultado = calcularValorTrianguloPascal(i._1, i._2)
        println("Fila = "+i._1 +"\tColumna = "+ i._2+ " \tResultado = "+resultado)
        resultado == (sup_izq+sup_der)
      }
    }
  }

}
