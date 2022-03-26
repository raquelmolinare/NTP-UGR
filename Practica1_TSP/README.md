# Práctica 1: programación funcional en Java

## Entorno de desarrollo 🔧💡
Se ha usado IntelliJ IDEA como entorno de desarrollo y JDK version 16.0.1. Añadiendo en la configuración del proyecto las librerías para javaFX.

## Modificaciones 📝
1. Se ha estructurado el proyecto en tres paquetes:
   -  _**Modelo**_: Clases que representan los datos del problema: _Punto_, _Ruta_, _Mapa_…
   - _**Vista**_: Clases para la representación gráfica y en modo texto.
   - _**Heurísticas**_: Clases e interfaces para la implementación de las heurísticas.


2. Se ha eliminado de la clase _MapaTSP_ el atributo de almacén de distancias: _distacias_, con el objetivo de ahorrar en gasto de memoria y de tiempo cuando el problema a resolver tiene un número alto de ciudades. En su lugar se calcula la distancia entre dos puntos cuando la heurística lo requiera y sólo entre los puntos necesarios.


3. El número de iteraciones en la heurística MonteCarlo será directamente proporcional a la dimensión del problema. De manera que se ha incluido una constante _FACTOR_ a esa clase y el número de iteraciones se definirá, en el constructor de la clase una vez obtenido el mapa, como ``iteraciones = dimension_problema * FACTOR``. Este cambio se ha pensado en base a que no tiene sentido utilizar el mismo número de iteraciones para un problema de 10 ciudades que para un problema de 500 ciudades. 

## Métodos agregados ➕

