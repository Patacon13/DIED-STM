---
title: Visualización de grafos
parent: Funcionamiento
has_children: false
---

# Visualización de grafos

Para visualizar el mapa con el grafo se utilizó la clase 'GraphStream' que permite realizar automáticamente el dibujo agregando nodos y aristas con su disposición en cada componente conexa automáticamente.

Seteo de propiedad:

```java
System.setProperty("org.graphstream.ui", "swing");
```

Disposición y muestra del grafo:

```java
graph.setAutoCreate(true);
graph.setStrict(true);
Viewer viewer = graph.display();
```

Permitir cerrar ventana sin cerrar aplicación:

```java
viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
```
