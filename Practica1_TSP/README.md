# Práctica 1: programación funcional en Java

## Entorno de desarrollo 🔧💡
Se ha usado IntelliJ IDEA como entorno de desarrollo y JDK version 16.0.1. Añadiendo en la configuración del proyecto las librerías para javaFX.

## Modificaciones 📝
1. Se ha estructurado el proyecto en tres paquetes:
   -  _**Modelo**_: Clases que representan los datos del problema: _Punto_, _Ruta_, _Mapa_…
   - _**Vista**_: Clases para la representación gráfica y en modo texto.
   - _**Heurísticas**_: Clases e interfaces para la implementación de las heurísticas.


2. Se ha eliminado de la clase `MapaTSP` el atributo de almacén de distancias: _distancias_, con el objetivo de ahorrar en gasto de memoria y de tiempo cuando el problema a resolver tiene un número alto de ciudades. En su lugar se calcula la distancia entre dos puntos cuando la heurística lo requiera y solo entre los puntos necesarios.


3. El número de iteraciones en la heurística MonteCarlo será directamente proporcional a la dimensión del problema. De manera que se ha incluido una constante _FACTOR_ a esa clase y el número de iteraciones se definirá, en el constructor de la clase una vez obtenido el mapa, como ``iteraciones = dimension_problema * FACTOR``. Este cambio se ha pensado, ya que, no tiene sentido utilizar el mismo número de iteraciones para un problema de 10 ciudades que para un problema de 500 ciudades. 

## Métodos agregados ➕

### Clase MapaTSP
- Agregado método `obtenerPuntos` : obtener la lista de puntos del mapa.
- En la versión funcional se ha agregado el método `procesarLineaPunto`: obtener el punto contenido en la línea de texto.

### Clase Ruta
- Se ha modificado el método `agregar`: añadiendo un nuevo parámetro `coste` y este valor se suma al coste actual de la ruta.
- Se ha agregado el método `obtenerUltimo`:  obtener el ultimo punto de la ruta.
- Se ha agregado el método `obtenerSecuencia`: obtener la secuencia de la ruta.
- Se ha agregado el método `compararCoste`: comparar el coste de dos rutas.

### Clase SolucionadorGenerico
- Se ha duplicado el método `factoría` con un número diferente de parámetros para crear un objeto para la solución _2OPT_ que necesita un parámetro más o las soluciones _MC_ o _VMC_ que necesitan los parámetros especificados en la versión inicial.


