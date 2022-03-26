package modelo;

import heuristicas.*;
import vista.Visualizador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * clase para almacenar mapas que se resolveran mediante TSP
 */
public class MapaTSP {
   // nombre del archivo asociado
   private String nombre;

   // array de puntos
   private List<Punto> puntos;

   // almacen de distancias
   private double distancias[][];

   // el comportamiento para mostrar se delega en
   // la visualizador correspondiente
   protected Visualizador visualizador;

   // comportamiento para calculo de distancia
   protected Distancia calculadorDistancia;

   // comportamiento para resolver el problema
   protected HeuristicaTSP heuristica;

   // dato miembro para almacenar la solucion del problema
   protected Ruta solucion;

   /**
    * constructor de la clase
    * @param nombreArchivo
    */
   MapaTSP(String nombreArchivo){
      // se asigna el nombre
      this.nombre = nombreArchivo;

      // se crea la coleccion de puntos
      puntos = new ArrayList<>();

      // lectura del archivo
      leerArchivo();
   }

   /**
    * metodo de acceso al nombre
    * @return
    */
   public String obtenerNombre(){
      return nombre;
   }

   /**
    * devuelve la dimension del problema
    * @return
    */
   public int obtenerDimension(){
      return puntos.size();
   }

   /**
    * devuelve el punto de una determinada posicion
    * @param indice
    * @return
    */
   public Punto obtenerPunto(int indice){
      return puntos.get(indice);
   }

   /**
    * Metodo para obtener los puntos del mapa
    * @return puntos
    */
   public  List<Punto> obtenerPuntos() {
      return this.puntos;
   }

   /**
    * metodo mostrar delegando en el visualizador correspondiente
    */
   public void mostrarMapa(){
      // se delega en el visualizador
      visualizador.mostrar(this);
   }

   /**
    * metodo para mostrar la ruta
    */
   public void mostrarRuta(){
      visualizador.mostrar(nombre, solucion);
      System.out.println("Coste: " + solucion.obtenerCoste());
   }

   /**
    * metodo de calculo de distancia
    * @param origen
    * @param destino
    * @return
    */
   public double calcularDistancia(Punto origen, Punto destino){
      return calculadorDistancia.calcular(origen, destino);
   }

   /**
    * metodo de obtencion de la ruta
    * @return
    */
   public Ruta resolver(){
      // asignar la solucion
      solucion = heuristica.resolver();
      return solucion;
   }

   /**
    * metodo privado para leer datos del archivo
    */
   private void leerArchivo(){
      // se procesa el contenido del archivo
      try{
         // se crean objetos necesarios para la lectura
         FileReader lector = new FileReader(nombre);
         BufferedReader lectorb = new BufferedReader(lector);

         // lectura de la primera linea: se eliminan los+
         // blancos iniciales
         String linea = lectorb.readLine().stripLeading();

         // extraigo las palabras de la linea
         String [] datos = linea.split("\\s+");

         // se comprueba que hay dos datos en la linea
         if(datos.length != 2){
            System.out.println("error en linea de cabecera");
            System.exit(-1);
         }

         // se obtiene la dimension
         int dimension = Integer.parseInt(datos[1]);

         // bucle de lectura de puntos
         for(int i=0; i < dimension; i++){
            // lectura de linea
            linea = lectorb.readLine().stripLeading();

            // se extraen las palabras
            datos = linea.split("\\s+");

            // se comprueba el formato
            if(datos.length != 3){
               System.out.println("error en linea de datos");
               System.out.println(linea);
               System.exit(-1);
            }

            // se crea el punto con los datos leidos
            Punto punto = new Punto(datos[0],
                    Double.parseDouble(datos[1]),
                    Double.parseDouble(datos[2]));

            // se agrega a la coleccion de puntos
            puntos.add(punto);
         }
      }catch(Exception e){
         System.out.println("Error en apertura de archivo");
         System.exit(-1);
      }
   }

   /**
    * metodo de calculo de distancias
    */
   protected void calcularDistancias(){
      int dimension = puntos.size();

      // se reserva espacio para el array de distancias
      distancias = new double[dimension][dimension];

      // bucle de calculo de distancias
      for(int i=0; i < dimension; i++){
         for(int j=i+1; j < dimension; j++){
            distancias[i][j] = calcularDistancia(puntos.get(i),
                    puntos.get(j));
            distancias[j][i] = distancias[i][j];
         }
      }
   }
}
