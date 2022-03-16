import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * mediante simulacion Montecarlo
 */
public class HeuristicaMC implements HeuristicaTSP {
    /**
     * numero de iteraciones del problema
     */
    private int iteraciones;

    /**
     * constante factor de multiplicacion
     */
    private static final int FACTOR;

    /**
     * referencia al problema a resolver
     */
    private MapaTSP mapa;

    /**
     * array de indices de ciudades
      */
    private List<Integer> indices;

    /**
     * Constructor de las variables tipo static
     */
    static {
        FACTOR = 10000;
    }

    /**
     * constructor de la clase
     *
     * @param mapa
     */
    public HeuristicaMC(MapaTSP mapa) {
        // se asigna el dato miembro mapa
        this.mapa = mapa;

        // El numero de iteraciones sera directamente proporcional a la dimension del problema
        iteraciones = this.mapa.obtenerDimension() * FACTOR;

        // se inicializa la lista de indices vacia
        indices = new ArrayList<>();
    }

    /**
     * metodo de resolucion mediante heuristica montecarlo
     *
     * @return la ruta optima
     */
    public Ruta resolver() {
        System.out.println("Interfaz HeuristicaMC");
        System.out.println("resolucion mediante simulacion Montecarlo");

        // NOTA: por implementar. Pueden implementarse los
        // metodos auxiliares que se considere oportuno

        ArrayList<Ruta> rutas = new ArrayList<>();
        Ruta resultado;

        // Almacenar los indices de las ciudades
        for (int i=0; i < this.mapa.obtenerDimension(); i++) {
            this.indices.add(i);
        }

        // Obtener las rutas aleatorias
        for (int i = 0; i < this.iteraciones; i++) {
            Ruta r = this.rutaAleatoria();
            rutas.add(r);
        }

        // Obtener cual de las rutas tiene menor coste y devolver la ruta con menor coste
        resultado = this.obtenerRutaMenorCoste(rutas);

        System.out.println("RESULTADO MC:");
        System.out.println(resultado.toString());

        // se devuelve la ruta propuesta
        return resultado;
    }

    /**
     * Metodo para generar rutas aleatorias
     * @return una ruta aleatoria
     */
    private Ruta rutaAleatoria() {
        Ruta resultado = new Ruta();

        // Desordenar de forma aleatoria el array de indices
        Collections.shuffle(indices);

        // Completar la ruta aleatoria con las ciudades según la lista de indices barajados
        for(int i: this.indices) {
            // Añadir la ciudad a la ruta
            double coste = resultado.calcularLongitud() == 0 ?
                    0.0: this.mapa.calcularDistancia(resultado.obtenerUltimo(),this.mapa.obtenerPunto(i));
            resultado.agregar(this.mapa.obtenerPunto(i), coste);
        }

        // Añadir el punto de vuelta a la primera ciudad a la ruta
        double coste = resultado.calcularLongitud() == 0 ?
                0.0: this.mapa.calcularDistancia(resultado.obtenerUltimo(),resultado.obtenerPunto(0));
        resultado.agregar(resultado.obtenerPunto(0), coste);

        // Devolver la ruta aleatoria generada
        return resultado;
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
