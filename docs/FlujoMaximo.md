---
title: Flujo Maximo
parent: Funcionamiento
has_children: false
---

# Cálculo del Flujo Máximo

Sabemos que el algoritmo de Ford-Fulkerson consigue (en un subgrafoAB) el flujo máximo requerido sobre un grafo. Este puede ser implementado sobre un DFS o BFS. Generalmente BFS puede tener una mejor complejidad total (aunque sea igual en Big O), pero no alteraba demasiado los tiempos por lo que no consideramos que sea una buena estrategia siendo que todo el trabajo práctico aprovechaba el algoritmo de búsqueda en profundidad.

El pseudo-código del algoritmo general sería:

```
1. Recorrer DFS actualizando valores (restando flujo).
2. Actualizar la lista si el flujo encontrado es mayor al anterior.
3. Revisar si existe un nuevo retorno de DFS (volver a paso 1, o finalizar).
```

