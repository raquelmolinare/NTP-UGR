package heuristicas;

import modelo.Ruta;

/**
 * interfaz para el metodo de resolucion del problema
 * mediante alguna heuristica
 */
public interface HeuristicaTSP {
   /**
    * metodo de resolucion
    * @return
    */
   public Ruta resolver();
}
