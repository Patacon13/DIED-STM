---
title: Camino Minimo
parent: Funcionamiento
has_children: false
---

# Cálculo del camino mínimo

El cálculo del camino mínimo es igual para los tres tipos de caminos, solo variando el dato que se analiza al inicializar la matriz de adyacencias del grafo.
Para comenzar, se realiza la matriz de adyacencia analizando el siguiente patrón:

```
Para todas las estacionesA -> Para todas las estacionesB -> Para todas las líneas de transporte -> Si existe conexión directa de estacionA a estacionB -> Actualizar valor en la matriz.
```

Luego, mediante el algoritmo de dijkstra se busca el mínimo camino según la matriz de adyacencia generada previamente. 

Sabemos que el algoritmo de dijkstra genera un vector con el padre de cada nodo en el recorrido mínimo generado. Entonces, finalmente lo que se hace, es generar una lista recorriendo de atrás para adelante los índices encontrados en este vector "padre"

```java
int ultimaIteracion = -1;
while(!iteracion.equals(origen.id)) {
	if(ultimaIteracion == iteracion) return null;
	int thisIteracion = iteracion;
	ultimaIteracion = thisIteracion;
	retorno.addFirst(estaciones.stream()
				   .filter(estacion -> estacion.id.equals(Integer.valueOf(thisIteracion)))
				   .findFirst()
				   .get());
	iteracion = padre.get(iteracion);
}
retorno.addFirst(origen);
return retorno.stream().collect(Collectors.toList());
```
