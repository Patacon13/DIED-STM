---
title: PageRank
parent: Funcionamiento
has_children: false
---

# Cálculo del PageRank

El cálculo del PageRank es obtenido mediante una consulta de SQL que encuentra todas las estaciones con rutas que la contengan como destino (con grado negativo mayor a cero) y se ordenan las mismas según la cantidad de rutas que llegan a ella de manera descendente (es decir que la estación que se encuentra en puesto 1 es a la que llega mayor cantidad de rutas). 

```sql
SELECT * FROM estacion AS est, (SELECT destino, count(*) AS cant FROM ruta GROUP BY destino) AS aux WHERE est.id = aux.destino ORDER BY aux.cant DESC;
```
