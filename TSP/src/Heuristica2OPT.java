import java.util.Random;

public class Heuristica2OPT implements HeuristicaTSP{
    /**
     * referencia al problema a resolver
     */
    private MapaTSP mapa;

    /**
     *
     */
    private HeuristicaTSP heuristica;

    /**
     * dato miembro para almacenar la ruta optima
     */
    private Ruta optima;

    /**
     * constructor de la clase
     * @param mapa
     */
    public Heuristica2OPT(MapaTSP mapa, HeuristicaTSP heuristica){
        // se asigna el mapa
        this.mapa = mapa;

        //
        this.heuristica = heuristica;

        // se crea inicializa la ruta optima
        this.optima = new Ruta();
    }


    @Override
    public Ruta resolver() {
        System.out.println("Interfaz Heuristica2OPT");
        System.out.println("resolucion mediante la heuristica 2opt");

        this.optima = heuristica.resolver();

        int k = new Random().nextInt(mapa.obtenerDimension()-1)+1;

        for(int i=0; i<=k; i++) {
            for(int j=i+1; j<=k; j++) {
                // Generar la nueva ruta
                Ruta nueva = this.swaopt( this.optima, i, j);

                // Comparar los costes
                if( nueva.obtenerCoste() < this.optima.obtenerCoste()){
                    this.optima = nueva;
                }
            }
        }

        System.out.println("RESULTADO 2OPT:");
        System.out.println(this.optima);
        return this.optima;
    }

    private Ruta swaopt(Ruta ruta, int inicio, int fin) {

        // Nueva ruta vacia
        Ruta nueva = new Ruta();
        double coste;

        // Copiar las posiciones [0, inicio) tal cual
        for(int i=0; i < inicio; i++) {
            // Evitar repeticiones por la primera y ultima ciudad
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0? 0.0: this.mapa.calcularDistancia(nueva.obtenerPunto(nueva.calcularLongitud()-1), ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        // Copiar las posiciones [inicio,fin] en orden inverso
        for(int i=fin; i>=inicio; i--) {
            // Evitar repeticiones
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0 ? 0.0 : this.mapa.calcularDistancia(nueva.obtenerPunto(nueva.calcularLongitud() - 1), ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        // Copiar los puntos (fin, n) tal cual
        for(int i=fin+1; i < ruta.calcularLongitud(); i++) {
            // Evitar repeticiones
            if( !nueva.contiene(ruta.obtenerPunto(i)) ) {
                coste = nueva.calcularLongitud() == 0 ? 0.0 : this.mapa.calcularDistancia(nueva.obtenerPunto(nueva.calcularLongitud() - 1), ruta.obtenerPunto(i));
                nueva.agregar(ruta.obtenerPunto(i), coste);
            }
        }

        //Incuimos la ciudad inicial
        coste = this.mapa.calcularDistancia(nueva.obtenerPunto(nueva.calcularLongitud()-1), nueva.obtenerPunto(0));
        nueva.agregar(nueva.obtenerPunto(0), coste);

        return nueva;
    }


}
