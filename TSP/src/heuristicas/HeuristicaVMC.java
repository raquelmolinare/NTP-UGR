package heuristicas;

import modelo.MapaTSP;
import modelo.Punto;
import modelo.Ruta;

import java.util.ArrayList;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * del vecino mas cercano
 */
public class HeuristicaVMC implements HeuristicaTSP{
   /**
    * referencia al problema a resolver
    */
   private final MapaTSP mapa;

   /**
    * constructor de la clase
    * @param mapa mapa del problema
    */
   public HeuristicaVMC(MapaTSP mapa){
      // se asigna el mapa
      this.mapa = mapa;
   }

   /**
    * metodo de resolucion con heuristica del vecino
    * mas cercano
    * @return la ruta optima
    */
   public Ruta resolver(){
      System.out.println("Interfaz heuristicas.HeuristicaVMC");
      System.out.println("resolucion mediante vecino mas cercano");

      // NOTA: por implementar. Pueden implementarse los
      // metodos auxiliares que se considere oportuno

      Ruta optima = new Ruta();
      ArrayList<Ruta> rutas = new ArrayList<>();

      // Para cada ciudad i
      for( int i=0; i < mapa.obtenerDimension(); i++) {

         Ruta resultado = new Ruta();

         // La primera ciudad de la ruta será vi
         Punto vi = mapa.obtenerPunto(i);

         // Añadir la primera ciudad al recorrido
         double coste = resultado.calcularLongitud() == 0 ?
                 0.0 : this.mapa.calcularDistancia(resultado.obtenerUltimo(),vi);
         resultado.agregar(vi, coste);

         // Completar la ruta
         this.completarRuta(resultado);

         // Se agrega la ruta nueva a la coleccion de rutas
         rutas.add(resultado);
      }

      // Una vez aqui el array de rutas tiene una ruta de vecino mas cercano por cada ciudad del mapa
      // Obtener de todas las rutas la ruta optima, es decir la de menor coste
      optima = this.obtenerRutaMenorCoste(rutas);

      System.out.println("RESULTADO VCM:");
      System.out.println(optima.toString());

      // se devuelve la ruta de minimo coste
      return optima;
   }

   /**
    * Metodo recursivo para completar la ruta con las ciudades del mapa
    * @param ruta a completar
    */
   private void completarRuta(Ruta ruta){
      // Si la ruta no está completa
      if(ruta.calcularLongitud() < this.mapa.obtenerDimension() ){
         // Obtener el punto mas cercano al anterior (que no haya sido visitada)
         Punto masCercana = this.puntoMasCercanoValido(ruta);

         // Añadirlo al recorrido
         double coste = ruta.calcularLongitud() == 0 ?
                 0.0 : this.mapa.calcularDistancia(ruta.obtenerUltimo(),masCercana);
         ruta.agregar(masCercana, coste);

         // Recursividad: seguir completando la ruta
         this.completarRuta(ruta);
      }
      else {
         // Si la ruta ya esta completa
         // Volvemos a añadir la primera ciudad para finalizar el recorrido
         double coste = ruta.calcularLongitud() == 0 ?
                 0.0 : this.mapa.calcularDistancia(ruta.obtenerUltimo(),ruta.obtenerPunto(0));
         ruta.agregar(ruta.obtenerPunto(0), coste);
      }
   }

   /**
    * Metodo para obtener el siguiente punto del mapa más cercano al último punto de la ruta y que no haya sido visitado
    * @param ruta ruta actual
    * @return devuelve el punto mas cercano no visitado
    */
   private Punto puntoMasCercanoValido(Ruta ruta) {
      Punto masCercano = null;
      double distanciaMinima = Double.MAX_VALUE;

      // Se recorren los puntos del mapa
      for(Punto p: this.mapa.obtenerPuntos()) {
         // Si es un punto no contenido ya en la ruta y tiene una distancia menor
         if( !ruta.contiene(p) && this.mapa.calcularDistancia(ruta.obtenerUltimo(), p) < distanciaMinima) {
            // se actualiza el valor del punto mas cercano
            masCercano = p;
            distanciaMinima = this.mapa.calcularDistancia(ruta.obtenerUltimo(), p);
         }
      }

      // devolver el punto mas cercano no visitado
      return masCercano;
   }

   /**
    * Método para obtener la ruta de menor coste
    * @param rutas ArrayList de rutas a comparar
    * @return ruta de menor coste
    */
   private Ruta obtenerRutaMenorCoste(ArrayList<Ruta> rutas) {
      Ruta rutaMenorCoste = rutas.size()>0? rutas.get(0) : null;

      for(Ruta ruta : rutas) {
         if (ruta.obtenerCoste() < rutaMenorCoste.obtenerCoste()) {
            rutaMenorCoste = ruta;
         }
      }

      return rutaMenorCoste;
   }
}
