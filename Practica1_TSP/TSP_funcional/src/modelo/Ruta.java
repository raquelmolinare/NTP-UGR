package modelo;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * clase para almacenar recorridos de ciudades
 */
public class Ruta {
   /**
    * dato miembro para almacenar las ciudades recorridas
    */
   private final ArrayList<Punto> secuencia;

   /**
    * dato miembro para almacenar el coste asociado al recorrido
    * hecho
    */
   private double coste;

   /**
    * constructor por defecto de la clase ruta
    */
   public Ruta(){
      // se inicializa array vacio
      secuencia = new ArrayList<>();

      // se indica que el coste es 0
      coste = 0;
   }

   /**
    * agrega punto nuevo a la ruta, sin hacer comprobacion
    * alguna
    * @param punto a agregar en la ruta
    * @param coste asociado a ir desde el último punto actual de la ruta al nuevo punto
    */
   public void agregar(Punto punto, double coste){
      secuencia.add(punto);
      this.coste+=coste;
   }

   /**
    * determina si la ruta contiene al punto pasado
    * como argumento
    * @param punto a comprobar si esta ya en la ruta
    * @return booleano
    */
   public boolean contiene(Punto punto){
      return secuencia.contains(punto);
   }

   /**
    * devuelve la longitud de la secuencia, es decir,
    * el numero de puntos que contiene
    * @return numero de puntos de puntos de la secuencia
    */
   public int calcularLongitud(){
      return secuencia.size();
   }

   /**
    * devuelve el punto que ocupa una determinada posicion
    * @param indice posicion a comprobar
    * @return el punto que ocupa la posicion de indice
    */
   public Punto obtenerPunto(int indice){
      return secuencia.get(indice);
   }

   /**
    * metodo para asignar el coste de la ruta
    * @param coste nuevo coste de la ruta
    */
   public void asignarCoste(double coste){
      this.coste = coste;
   }

   /**
    * metodo de acceso al coste
    */
   public double obtenerCoste(){
      return coste;
   }

   /**
    * Metodo para obtener el ultimo punto de la ruta
    * @return ultimo punto de la ruta
    */
   public Punto obtenerUltimo() {
      if(this.secuencia.size() > 0) {
         return this.secuencia.get(this.secuencia.size()-1);
      }
      else {
         return null;
      }
   }

   /**
    * Metodo para obtener la secuencia de la ruta
    * @return secuencia
    */
   public ArrayList<Punto> obtenerSecuencia(){
      return secuencia;
   }

   /**
    *  Metodo para comparar el coste
    * @param r ruta a comparar
    * @return -1 0 1 segun si es menor igual o mayor
    */
   public int compararCoste(Ruta r) {
     return Double.compare(this.coste, r.obtenerCoste());
   }

   /**
    * metodo toString
    * @return cadena de descripcion de la secuencia
    */
   public String toString(){
      String salida = secuencia.stream().map(Punto::toString).collect(Collectors.joining(" - "));
      salida  = "Secuencia: "+salida;
      salida += "\nCoste asociado: " + coste +"\n";

      // se devuelve la cadena
      return salida;
   }
}
