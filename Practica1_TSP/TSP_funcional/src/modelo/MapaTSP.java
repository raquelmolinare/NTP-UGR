package modelo;

import heuristicas.*;
import vista.Visualizador;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * clase para almacenar mapas que se resolveran mediante TSP
 */
public class MapaTSP {
   // nombre del archivo asociado
   private final String nombre;

   // array de puntos
   private List<Punto> puntos;

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
         // Leer las lineas del archivo
         Stream<String> lineas = Files.lines(Paths.get(this.nombre));

         // Para cada linea se genera un objeto de la clase Punto y se almacena toda la coleccion de puntos
         // Evitando la cabecera del archivo que contienene la dimension del problema
         puntos = lineas.filter(linea -> !linea.contains("DIMENSION")).map(this::procesarLineaPunto).
                 collect(Collectors.toList());

      }catch(Exception e){
         // Mostrar el mensaje de la excepción
         System.out.println(e);
         System.out.println("Error en apertura de archivo");
         System.exit(-1);
      }
   }


   private Punto procesarLineaPunto(String linea) {
      // Patron para extraer las palabras de la línea --> etiqueta x y
      Pattern pattern = Pattern.compile("\\s+");

      // Se procesa la linea
      List<String> datos = pattern.splitAsStream(linea).collect(Collectors.toList());

      // Se crea el punto con los datos leidos
      Punto punto = new Punto(datos.get(0),
              Double.parseDouble(datos.get(1)),
              Double.parseDouble(datos.get(2)));

      // se devuelve el punto creado a partir de la linea
      return punto;
   }

}
