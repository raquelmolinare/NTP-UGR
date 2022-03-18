package modelo;

import heuristicas.*;
import vista.VisualizadorGrafico;
import vista.VisualizadorTexto;

/**
 * No es preciso crear una clase especifica para cada combinacion
 * de visualizacion - distancia - heuristica. Basta con disponer
 * de un mecanismo claro de construccion de objetos. En este caso
 * se usa el patron metodo-factoria, que recibe como argumentos el
 * nombre del fichero y enumerados que indican la forma en que
 * se quiere hacer cada funcion
 */
public class SolucionadorGenerico extends MapaTSP{

   /**
    * Creacion de objeto a partir de nombre de archivo
    *
    * @param nombreArchivo
    */
   public SolucionadorGenerico(String nombreArchivo) {
      super(nombreArchivo);
   }

   /**
    * metodo que implementa el patron factoria para crear los
    * objetos con las caracteristicas adecuadas
    * @param nombreArchivo
    * @param modoVista
    * @param modoDistancia
    * @param modoHeuristica
    * @return
    */
   public static MapaTSP factoria(String nombreArchivo, ModoVista modoVista, ModoDistancia modoDistancia,
                                  ModoHeuristica modoHeuristica, Modo2OPT modoHeuristica2opt ){
      MapaTSP objeto = new SolucionadorGenerico(nombreArchivo);
      HeuristicaTSP inicioOPT = null;

      // se procesa el modo de visualizacion deseado
      switch (modoVista){
         case TEXTO -> objeto.visualizador = new VisualizadorTexto();
         case GRAFICO -> objeto.visualizador = new VisualizadorGrafico();
      }

      // se considera la forma de calculo de distancia
      switch (modoDistancia){
         case EUCLIDEA -> objeto.calculadorDistancia = new DistanciaEuclidea();
         case MANHATTAN -> objeto.calculadorDistancia = new DistanciaManhattan();
      }

      // En el caso en el que la heuristica a procesar sea 2opt lleva asociada esta otra heuristica
      if(modoHeuristica2opt !=  null) {
         switch (modoHeuristica2opt) {
            case MC -> inicioOPT = new HeuristicaMC(objeto);
            case VMC -> inicioOPT = new HeuristicaVMC(objeto);
         }
      }

      // se procesa la heuristica a considerar
      switch (modoHeuristica) {
         case MC -> objeto.heuristica = new HeuristicaMC(objeto);
         case VMC -> objeto.heuristica = new HeuristicaVMC(objeto);
         case OPT2 -> objeto.heuristica = new Heuristica2OPT(objeto, inicioOPT);
      }

      // se calculan las distancias ahora que el objeto esta
      // creado de forma completa
      objeto.calcularDistancias();

      // se devuelve el objeto una vez configurado
      return objeto;
   }

   public static MapaTSP factoria(String nombreArchivo, ModoVista modoVista, ModoDistancia modoDistancia,
                                  ModoHeuristica modoHeuristica){
      return SolucionadorGenerico.factoria(nombreArchivo, modoVista, modoDistancia, modoHeuristica, null);
   }

}
