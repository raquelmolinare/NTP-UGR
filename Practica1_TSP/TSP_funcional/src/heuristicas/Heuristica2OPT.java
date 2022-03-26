package heuristicas;

import modelo.MapaTSP;
import modelo.Ruta;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

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

        AtomicReference<Ruta> optima = new AtomicReference<>(heuristica.resolver());

        IntStream.range(0, K+1).forEach(i -> IntStream.range(i+1, K+1).forEach(j -> {
            // Generar la nueva ruta
            Ruta nueva = this.swaopt(optima.get(), i, j);
            // Comparar los costes
            if( nueva.obtenerCoste() < optima.get().obtenerCoste()){
                optima.set(nueva);
            }
        }));

        System.out.println("RESULTADO 2OPT:");
        System.out.println(optima.get().toString());

        // Devolver la ruta optima
        return optima.get();
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

        // Copiar las posiciones [0, inicio) tal cual
        IntStream.range(0, inicio).filter( i-> !nueva.contiene(ruta.obtenerPunto(i))).forEach( i ->{
                double coste = nueva.calcularLongitud() == 0 ?
                        0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
        });


        // Copiar las posiciones [inicio,fin] en orden inverso
        IntStream.range(inicio, fin+1).boxed().sorted(Collections.reverseOrder()).filter( i-> !nueva.contiene(ruta.obtenerPunto(i))).forEach( i ->{
            double coste = nueva.calcularLongitud() == 0 ?
                    0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
            nueva.agregar(ruta.obtenerPunto(i), coste);
        });

        // Copiar los puntos (fin, n) tal cual
        IntStream.range(fin+1, ruta.calcularLongitud()).filter( i-> !nueva.contiene(ruta.obtenerPunto(i))).forEach( i ->{
            double coste = nueva.calcularLongitud() == 0 ?
                    0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),ruta.obtenerPunto(i));
            nueva.agregar(ruta.obtenerPunto(i), coste);
        });

        //Incuimos la ciudad inicial para cerrar la ruta
        double coste = nueva.calcularLongitud() == 0 ?
                0.0 : this.mapa.calcularDistancia(nueva.obtenerUltimo(),nueva.obtenerPunto(0));
        nueva.agregar(nueva.obtenerPunto(0), coste);

        return nueva;
    }


}
