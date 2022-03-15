import java.util.ArrayList;
import java.util.Random;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * mediante simulacion Montecarlo
 */
public class HeuristicaMC implements HeuristicaTSP {
    // constante para el numero de iteraciones
    final static int iteraciones = 100000;

    /**
     * referencia al problema a resolver
     */
    private MapaTSP mapa;

    /**
     * dato miembro para almacenar las rutas aleatorias
     */
    private ArrayList<Ruta> rutas;


    /**
     * constructor de la clase
     *
     * @param mapa
     */
    public HeuristicaMC(MapaTSP mapa) {
        // se asigna el dato miembro mapa
        this.mapa = mapa;

        // se incializan los array vacios
        rutas = new ArrayList<>();
    }

    /**
     * metodo de resolucion mediante heuristica montecarlo
     *
     * @return
     */
    public Ruta resolver() {
        System.out.println("Interfaz HeuristicaMC");
        System.out.println("resolucion mediante simulacion Montecarlo");

        // NOTA: por implementar. Pueden implementarse los
        // metodos auxiliares que se considere oportuno

        Ruta resultado;

        // 1. Obtener las rutas aleatorias y sus costes asociados
        for (int i = 0; i < HeuristicaMC.iteraciones; i++) {
            Ruta r = this.rutaAleatoria();
            this.rutas.add(r);
        }

        // 2. Obtener cual de las rutas tiene menor coste y devolver la ruta con menor coste
        resultado = this.obtenerRutaMenorCoste();

        System.out.println("RESULTADO MC:");
        System.out.println(resultado.toString());

        // se devuelve la ruta propuesta
        return resultado;
    }

    private Ruta rutaAleatoria() {
        Ruta resultado = new Ruta();

        this.completarRuta(resultado);

        // Añadir el punto de vuelta a la primera ciudad a la ruta
        double coste = resultado.calcularLongitud() == 0? 0.0: this.mapa.calcularDistancia(resultado.obtenerPunto(resultado.calcularLongitud()-1),resultado.obtenerPunto(0));
        resultado.agregar(resultado.obtenerPunto(0), coste);

        return resultado;
    }


    private void completarRuta(Ruta ruta){

        int siguienteIndice =  new Random().nextInt(this.mapa.obtenerDimension());
        Punto siguientePunto = this.mapa.obtenerPunto(siguienteIndice);
        if(!ruta.contiene(siguientePunto)) {
            // Añadir el punto a la ruta
            double coste = ruta.calcularLongitud() == 0 ? 0.0: this.mapa.calcularDistancia(ruta.obtenerPunto(ruta.calcularLongitud()-1),siguientePunto);
            ruta.agregar(siguientePunto, coste);
        }

        // Re
        if(ruta.calcularLongitud() < this.mapa.obtenerDimension()) {
           this.completarRuta(ruta);
        }
    }

    private Ruta obtenerRutaMenorCoste() {
        Ruta rutaMenorCoste = rutas.get(0);

        for(Ruta ruta : this.rutas) {
            if (ruta.obtenerCoste() < rutaMenorCoste.obtenerCoste()) {
                rutaMenorCoste = ruta;
            }
        }

        return rutaMenorCoste;
    }

}
