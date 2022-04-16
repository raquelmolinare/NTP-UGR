import modelo.*;

/**
 * Clase ProblemaPrueba para probar la funcionalidad de
 * creacion de objetos de la clase Problema mediante la
 * lectura de archivo de datos
 */
public class PruebaSistema {
   public static void main(String args[]){
      // nombre archivo con los datos del problema
      String nombreArchivo = "./data/small10.tsp";

      // Descomentar el modo que se quiera usar y comentar el resto

      //----------------------------------------------------------------------------------------
      //                    HEURISTICA MONTECARLO
      //----------------------------------------------------------------------------------------

      // se crea el objeto mediante el metodo factoria
//      MapaTSP objeto = SolucionadorGenerico.factoria(
//              nombreArchivo,
//              ModoVista.GRAFICO,
//              ModoDistancia.EUCLIDEA,
//              ModoHeuristica.MC);

      //----------------------------------------------------------------------------------------
      //                    HEURISTICA VECINO MAS CERCANO
      //---------------------------------------------------------------------------------------

      // se crea el objeto mediante el metodo factoria
//      MapaTSP objeto = SolucionadorGenerico.factoria(
//              nombreArchivo,
//              ModoVista.GRAFICO,
//              ModoDistancia.MANHATTAN,
//              ModoHeuristica.VMC);

      //----------------------------------------------------------------------------------------
      //                    HEURISTICA 2OPT
      //----------------------------------------------------------------------------------------

      // se crea el objeto mediante el metodo factoria
      // Cambiar el Modo2OTP a MC o VMC
      // según si se quiera generar la primera mejor ruta con MonteCarlo o con el Vecino Más Cercano
      MapaTSP objeto = SolucionadorGenerico.factoria(
              nombreArchivo,
              ModoVista.GRAFICO,
              ModoDistancia.EUCLIDEA,
              ModoHeuristica.OPT2,
              Modo2OPT.MC);



      //----------------------------------------------------------------------------------------
      // RESOLVER

      // se llama al metodo de resolucion
      objeto.resolver();

      // se llama al metodo mostrar, para ver la ruta optima
      // objeto.mostrarMapa();
      objeto.mostrarRuta();

   }
}
