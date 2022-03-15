import java.util.ArrayList;
import java.util.Random;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * del vecino mas cercano
 */
public class HeuristicaVMC implements HeuristicaTSP{
   /**
    * referencia al problema a resolver
    */
   private MapaTSP mapa;

   /**
    * dato miembro para almacenar la ruta optima
    */
   private Ruta optima;

   /**
    * constructor de la clase
    * @param mapa
    */
   public HeuristicaVMC(MapaTSP mapa){
      // se asigna el mapa
      this.mapa = mapa;

      // se crea inicializa la ruta optima
      this.optima = new Ruta();
   }

   /**
    * metodo de resolucion con heuristica del vecino
    * mas cercano
    * @return
    */
   public Ruta resolver(){
      System.out.println("Interfaz HeuristicaVMC");
      System.out.println("resolucion mediante vecino mas cercano");

      // NOTA: por implementar. Pueden implementarse los
      // metodos auxiliares que se considere oportuno


      // Para cada ciudad i
      for( int i=0; i < mapa.obtenerDimension(); i++) {

         Ruta resultado = new Ruta();

         // La primera ciudad de la ruta será vi
         Punto vi = mapa.obtenerPunto(i);

         // añadir la ciudad al recorrido
         double coste = resultado.calcularLongitud() == 0? 0.0: this.mapa.calcularDistancia(resultado.obtenerPunto(resultado.calcularLongitud()-1),vi);
         resultado.agregar(vi, coste);

         // Completar la ruta
         this.completarRuta(resultado, i);

         // Volvemos a añadir la primera ciudad
         coste = resultado.calcularLongitud() == 0? 0.0: this.mapa.calcularDistancia(resultado.obtenerPunto(resultado.calcularLongitud()-1),vi);
         resultado.agregar(vi, coste);


         //Actualizar la ruta optima si es necesario
         if( this.optima.obtenerCoste() == 0.0 || (this.optima.obtenerCoste() > resultado.obtenerCoste() )) {
            this.optima = new Ruta( resultado.obtenerSecuencia(), resultado.obtenerCoste());
         }

      }

      System.out.println("RESULTADO VMC");
      System.out.println(this.optima.toString());

      // se devuelve la ruta de minimo coste
      return this.optima;
   }

   private void completarRuta(Ruta ruta, int indicePrimeraCiudad){

      // Mientras queden ciudades por recorrer
      for(int j=0; j < this.mapa.obtenerDimension(); j++) {
         if( j != indicePrimeraCiudad ){

            // Obtener la más cercana a la anterior (que no haya sido visitada)
            Punto masCercana = mapa.obtenerPunto(this.indiceMenorDistancia(indicePrimeraCiudad, ruta));

            // Añadirla al recorrido
            double coste = ruta.calcularLongitud() == 0? 0.0: this.mapa.calcularDistancia(ruta.obtenerPunto(ruta.calcularLongitud()-1),masCercana);
            ruta.agregar(masCercana, coste);

         }
      }

      // Re
      if(ruta.calcularLongitud() < this.mapa.obtenerDimension()) {
         this.completarRuta(ruta, indicePrimeraCiudad);
      }
   }

   // TODO: Refactorizar este método en Mapa
   private int indiceMenorDistancia(int inicio, Ruta rutaActual) {
      int indiceMenor = -1;
      double distanciaMenor = -1.0;


      for(int i= 0; i < this.mapa.obtenerDimension(); i++){
         if( i != inicio && !rutaActual.contiene(mapa.obtenerPunto(i)) ) {
            if( indiceMenor == -1 ) {
               indiceMenor = i;
               distanciaMenor = this.mapa.calcularDistancia(this.mapa.obtenerPunto(inicio), this.mapa.obtenerPunto(indiceMenor));
            }
            else {
               double d = this.mapa.calcularDistancia(this.mapa.obtenerPunto(inicio), this.mapa.obtenerPunto(i));
               if( d < distanciaMenor ) {
                  indiceMenor = i;
                  distanciaMenor = d;
               }
            }
         }
      }

      return indiceMenor;
   }
}
