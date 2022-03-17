import modelo.*;

/**
 * Clase ProblemaPrueba para probar la funcionalidad de
 * creacion de objetos de la clase Problema mediante la
 * lectura de archivo de datos
 */
public class PruebaSistema {
   public static void main(String args[]){
      // se prueba con un archivo pequeño, de 10 ciudades
      String nombreArchivo = "./data/small10.tsp";

      // 2OPT
      // se crea el objeto mediante el metodo factoria
      MapaTSP objeto = SolucionadorGenerico.factoria(nombreArchivo,
                 ModoVista.GRAFICO, ModoDistancia.EUCLIDEA,
                 ModoHeuristica.MC);

      // se llama al metodo de resolucion
      objeto.resolver();

      // se llama al metodo mostrar, para ver la ruta
      // optima
//      objeto.mostrarMapa();
      objeto.mostrarRuta();

   }
}
