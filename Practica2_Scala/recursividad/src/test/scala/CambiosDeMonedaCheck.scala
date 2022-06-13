import FuncionesRecursivas.listarCambiosPosibles
import org.scalacheck.Prop.all
import org.scalacheck.Properties

object CambiosDeMonedaCheck extends Properties("Cambios De Moneda Check") {

  property("Numero de cambio de monedas") = {
    val check_cambio1 = listarCambiosPosibles(4, List(1,2)) == 3
    val check_cambio2 = listarCambiosPosibles(4, List(1,2,3)) == 4
    val check_cambio3 = listarCambiosPosibles(50, List(10,20,30)) == 5
    val check_cambio4 = listarCambiosPosibles(200, List(20,50,100)) == 6
    val check_cambio5 = listarCambiosPosibles(5, List(10,20)) == 0
    val check_cambio6 = listarCambiosPosibles(5, List()) == 0

    all(check_cambio1,check_cambio2,check_cambio3,check_cambio4,check_cambio5,check_cambio6)
  }

}
