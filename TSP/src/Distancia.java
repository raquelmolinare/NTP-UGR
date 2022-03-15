/**
 * interfaz para calculo de distancia entre dos puntos
 */
public interface Distancia {
   /**
    * metodo de calculo de distancia
    * @param origen
    * @param destino
    * @return
    */
   public double calcular(Punto origen, Punto destino);
}
