package heuristicas;

import modelo.MapaTSP;
import modelo.Ruta;

import java.util.Random;

public class Heuristica2OPT implements HeuristicaTSP{

    /**
     * constante parametro K de la heuristica
     */
    private static int K;

    /**
     * referencia al problema a resolver
     */
    private final MapaTSP mapa;

    /**
     *
     */
    private final HeuristicaTSP heuristica;

    /**
     * constructor de la clase
     * @param mapa mapa del problema
     */
    public Heuristica2OPT(MapaTSP mapa, HeuristicaTSP heuristica){
        // se asigna el mapa
        this.mapa = mapa;

        // se asigna la heuristica
        this.heuristica = heuristica;

        // se calcula el parametro K en base a la dimension del problema
        K = new Random().nextInt(this.mapa.obtenerDimension()-1)+1;
    }


    @Override
    public Ruta resolver() {
        System.out.println("Interfaz heuristicas.Heuristica2OPT");
        System.out.println("resolucion mediante la heuristica 2opt");

        Ruta optima = heuristica.resolver();

        for(int i=0; i<=K; i++) {
            for(int j=i+1; j<=K; j++) {
                // Generar la nueva ruta
                Ruta nueva = this.swaopt( optima, i, j);

                // Comparar los costes
                if( nueva.obtenerCoste() < optima.obtenerCoste()){
                    optima = nueva;
                }
            }
        }

        System.out.println("RESULTADO 2OPT:");
        System.out.println(optima.toString());

        // Devolver la ruta optima
        return optima;
    }

    /**
     * metodo swaopt
     * @param ruta ruta actual
     * @param inicio indice de inicio
     * @param fin indice de fin
     * @return nueva ruta tras el swap
     */
    private Ruta swaopt(Ruta ruta, int inicio, int fin) {

        // Nueva ruta vacia
        Ruta nueva = new Ruta();
        double coste;

        // Copiar las posiciones [0, inicio) tal cual
        for(int i=0; i < inicio; i++) {
            // Evitar repeticiones por la primera y ultima ciudad
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0 ?
                        0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        // Copiar las posiciones [inicio,fin] en orden inverso
        for(int i=fin; i>=inicio; i--) {
            // Evitar repeticiones
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0 ?
                        0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        // Copiar los puntos (fin, n) tal cual
        for(int i=fin+1; i < ruta.calcularLongitud(); i++) {
            // Evitar repeticiones
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0 ?
                        0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        //Incuimos la ciudad inicial para cerrar la ruta
        coste = nueva.calcularLongitud() == 0 ?
                0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),nueva.obtenerPunto(0));
        nueva.agregar(nueva.obtenerPunto(0), coste);

        return nueva;
    }


}
