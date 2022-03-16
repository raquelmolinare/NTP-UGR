import java.util.ArrayList;
import java.util.Optional;

/**
 * clase para almacenar recorridos de ciudades
 */
public class Ruta {
   /**
    * dato miembro para almacenar las ciudades recorridas
    */
   private ArrayList<Punto> secuencia;

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
    * @param punto
    */
   public void agregar(Punto punto, double coste){
      secuencia.add(punto);
      this.coste+=coste;
   }

   /**
    * determina si la ruta contiene al punto pasado
    * como argumento
    * @param punto
    * @return
    */
   public boolean contiene(Punto punto){
      return secuencia.contains(punto);
   }

   /**
    * devuelve la longitud de la secuencia, es decir,
    * el numero de puntos que contiene
    * @return
    */
   public int calcularLongitud(){
      return secuencia.size();
   }

   /**
    * devuelve el punto que ocupa una determinada posicion
    * @param indice
    * @return
    */
   public Punto obtenerPunto(int indice){
      return secuencia.get(indice);
   }

   /**
    * metodo para asignar el coste de la ruta
    * @param coste
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

   // NUEVO
   public Punto obtenerUltimo() {
      if(this.secuencia.size() > 0) {
         return this.secuencia.get(this.secuencia.size()-1);
      }
      else {
         return null;
      }
   }

   /**
    * TODO
    */
   public void quitarFin(){
      secuencia.remove(secuencia.size()-1);
   }

   /**
    * metodo toString
    * @return
    */
   public String toString(){
      String salida = "Secuencia: ";
      for(int i=0; i < secuencia.size(); i++){
         Punto punto = secuencia.get(i);
         salida += punto.toString();
         if(i != secuencia.size()-1){
            salida += " - ";
         }
      }
      salida += "\nCoste asociado: " + coste +"\n";

      // se devuelve la cadena
      return salida;
   }
}
