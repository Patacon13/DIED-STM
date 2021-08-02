package estacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lineaDeTransporte.ColorLineaDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import ruta.*;

/**
 * @author tomas
 *
 */
public class AdministradorDeCaminos {
	
	private HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafo;
	private HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoDFS;
	private List<Integer> padre;
	private List<Double> costos;
	private List<Boolean> visitado;
	private int flujoEncontradoEnDFS = 100000;
	private HashMap<Estacion, Boolean> recorridosDFS;
	private List<Estacion> estacionesMaximoFlujo;
	private List<Estacion> estacionesMaximoFlujoAux;
	private List<Deque<Pair<Estacion, ColorLineaDeTransporte>>> estacionesDeAaB;
	
		
	/**
	 * Inicializa el grafo (matriz con dato X de cada par de nodos).
	 * <p>
	 * Segun el dato que se ingrese la matriz se cargara con distintos numeros. Las opciones son las disponibles en el inciso 5 y 6,
	 * las cuales son camino mas rapido (MASRAPIDO), con menor distancia (MENORDISTANCIA), mas barato (MASBARATO) y maximo peso (incluido como default). 
	 * Requiere que se ejecute privamente init
	 *  
	 * @param estaciones
	 * @param lineas
	 * @param datoQueRequiere
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void initMatriz(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Pedido datoQueRequiere) throws ClassNotFoundException, SQLException {
		grafo = new HashMap<>();
		AdministradorDeRutas admin = new AdministradorDeRutas();
		for(Estacion estacionA : estaciones) {
			grafo.put(estacionA, new HashMap<>());
			for(Estacion estacionB : estaciones){
				
				if(estacionA.equals(estacionB)) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(Double.valueOf(0),null));
				else {
					for(LineaDeTransporte linea : lineas){
						List<Ruta> rutas = admin.getRutas(linea);
						if(linea.contieneA(rutas, estacionA) && linea.llegaA(rutas, estacionA, estacionB) && linea.estaActiva()) { //Si contiene al origen, y llega a la estacionB como destino
							switch(datoQueRequiere) {
							case MASRAPIDO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.duracionAAdyacente(estacionA, estacionB).doubleValue(), linea.getColor()));
								else if(linea.duracionAAdyacente(rutas, estacionA, estacionB) < grafo.get(estacionA).get(estacionB).first) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.duracionAAdyacente(rutas, estacionA, estacionB).doubleValue(), linea.getColor()));
								break;
							case MENORDISTANCIA:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, new Pair<Double,ColorLineaDeTransporte>(linea.distanciaAAdyacente(rutas, estacionA, estacionB), linea.getColor()));
								else if(linea.distanciaAAdyacente(rutas,estacionA, estacionB) < grafo.get(estacionA).get(estacionB).first) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.distanciaAAdyacente(rutas, estacionA, estacionB), linea.getColor()));
								break;
							case MASBARATO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.costoAAdyacente(rutas, estacionA, estacionB), linea.getColor()));
								else if(linea.costoAAdyacente(rutas, estacionA, estacionB) < grafo.get(estacionA).get(estacionB).first) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.costoAAdyacente(rutas, estacionA, estacionB), linea.getColor()));
							default:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.pesoA(rutas, estacionA, estacionB).doubleValue(), linea.getColor()));
								else if(linea.pesoA(rutas, estacionA, estacionB) > grafo.get(estacionA).get(estacionB).first) grafo.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(linea.pesoA(rutas, estacionA, estacionB).doubleValue(), linea.getColor()));
							}
						}
					}
				}
				
			}
			
		}
	}
	
	
	/**
	 * Inicializa padre (de que nodo viene el nodo actual), costos (desde el nodo inicial, cuanto cuesta llegar al destino) y visitado (indica si se recorrio el nodo).
	 * 
	 * @param estaciones
	 * @param origen
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void init(List<Estacion> estaciones, Estacion origen) throws ClassNotFoundException, SQLException {
		padre = new ArrayList<>();
		costos = new ArrayList<>();
		visitado = new ArrayList<>();
		for(int i = 0; i<estaciones.size(); i++) {
			visitado.add(Boolean.FALSE);
			costos.add(Double.valueOf(Double.MAX_VALUE));
			padre.add(i);
		}
		costos.set(origen.id, Double.valueOf(0));
	}
	
	/**
	 * Obtiene el nodo mas cercano al nodo actual. Requiere que anteriormente se ejecute init e initMatriz.
	 * 
	 * @param costos
	 * @return
	 */
	private int getCercano(List<Double> costos) {
		Double valorMinimo = Double.MAX_VALUE;
		int nodoMinimo = 0;
		for(int i = 0; i<costos.size(); i++) {
			if(!visitado.get(i) && costos.get(i) < valorMinimo) {
				valorMinimo = costos.get(i);
				nodoMinimo = i;
			}
		}
		return nodoMinimo;
	}
	
	/**
	 * Realiza el algoritmo de Dijkstra.
	 * <p>
	 * El algoritmo consiste en buscar las distancias de un nodo a todos los otros. Tomamos como "distancia" al dato que ingreso como Pedido en
	 * la funcion initMatriz. Requiere que anteriormente se ejecute initMatriz.
	 * 
	 * @param estaciones
	 * @param lineas
	 * @param origen
	 * @param destino
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void dijkstra(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Estacion origen, Estacion destino) throws ClassNotFoundException, SQLException {
		
		int cercanaId = getCercano(costos);
		Estacion cercana = estaciones.stream()
									 .filter(estacion -> estacion.id.equals(Integer.valueOf(cercanaId)))
									 .findFirst()
									 .get();
		visitado.set(cercanaId, true);
		for(int adj = 0; adj < costos.size(); adj++) {
			int thisIteracion = adj;
			Estacion adyacente = estaciones.stream()
										   .filter(estacion -> estacion.id.equals(Integer.valueOf(thisIteracion)))
										   .findFirst()
										   .get();
			if(adyacente != null && cercana != null) {
				if(grafo.get(cercana).get(adyacente) != null && costos.get(adj)>costos.get(cercanaId)+grafo.get(cercana).get(adyacente).first) {
					costos.set(adj, costos.get(cercanaId)+grafo.get(cercana).get(adyacente).first);
					padre.set(adj, cercanaId);
				}
			}
		}
		
		
	}	
	
	/**
	 * Copia el grafo generado en initMatriz por uno nuevo. Requiere que anteriormente se ejecute initMatriz
	 * 
	 * @param estaciones
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> copyGrafo(List<Estacion> estaciones) throws ClassNotFoundException, SQLException {
		HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoRetorno = new HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>>(grafo);
		
		for(Estacion estacionA : estaciones){
			
			grafoRetorno.put(estacionA, new HashMap<Estacion, Pair<Double,ColorLineaDeTransporte>>(grafo.get(estacionA)));
		
			
		}
		return grafoRetorno;
	}
	
	
	/**
	 * Realiza el algoritmo de floydWarshall
	 * <p>
	 * El algoritmo consiste en buscar la distancia entre CADA PAR DE NODOS. Se toma como distancia a lo pedido en initMatriz.
	 * Requiere que se ejecute previamente initMatriz. grafoRetorno tiene que ser una copia del grafo original.
	 * 
	 * @param grafoRetorno
	 * @param estaciones
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private HashMap<Estacion, HashMap<Estacion, Pair<Double, ColorLineaDeTransporte>>> floydwarshall(HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoRetorno, List<Estacion> estaciones) throws ClassNotFoundException, SQLException {
		for(Estacion estacionA : estaciones){
			grafoRetorno.put(estacionA, new HashMap<Estacion, Pair<Double, ColorLineaDeTransporte>>(grafoRetorno.get(estacionA)));
			for(Estacion estacionB : estaciones) {
				for(Estacion estacionC : estaciones) {
					if(grafoRetorno.get(estacionB) != null && grafoRetorno.get(estacionA) != null)
						if(grafoRetorno.get(estacionB).get(estacionA) != null && grafoRetorno.get(estacionA).get(estacionC) != null) {
							if(grafoRetorno.get(estacionB).get(estacionC) != null) grafoRetorno.get(estacionB).put(estacionC, new Pair<Double, ColorLineaDeTransporte>(Math.min(grafoRetorno.get(estacionB).get(estacionC).first,grafoRetorno.get(estacionB).get(estacionA).first + grafoRetorno.get(estacionA).get(estacionC).first),null));
							else grafoRetorno.get(estacionB).put(estacionC, new Pair<Double, ColorLineaDeTransporte>(grafoRetorno.get(estacionB).get(estacionA).first + grafoRetorno.get(estacionA).get(estacionC).first, null));
						}
				}
			}
		}
		return grafoRetorno;
	}
	
	
	/**
	 * Encuentra todas las estaciones de A a B.
	 * <p>
	 * Esta funcion no genera un grafo de A a B, solamente encuentra las estaciones involucradas.
	 * Requiere un grafo pasado por la funcion floydWarshall.
	 * 
	 * @param grafoFloyd
	 * @param origen
	 * @param destino
	 * @param estacionesIngreso
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Estacion> estacionesAB(HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoFloyd, Estacion origen, Estacion destino, List<Estacion> estacionesIngreso) throws ClassNotFoundException, SQLException {
		List<Estacion> estaciones = new ArrayList<>();
		for(Estacion estacion : estacionesIngreso)
			if(grafoFloyd.get(origen).get(estacion) != null && grafoFloyd.get(estacion).get(destino) != null) estaciones.add(estacion);
		
		return estaciones;	
	}
	
	/**
	 * Algoritmo de DFS (Busqueda en Profundidad).
	 * <p>
	 * Este algoritmo busca en profundidad dentro de un grafo dado. Esta reimplementado para poder ayudar a encontrar el
	 * flujo maximo en un subgrafo dado. Requiere que se inicialice recorridosDFS y grafoDFS.
	 * 
	 * @param origen
	 * @param destino
	 * @param estaciones
	 * @param flux
	 * @return
	 */
	private int dfs(Estacion origen, Estacion destino, List<Estacion> estaciones, int flux) {
		if(!origen.equals(destino)) {
			
			recorridosDFS.put(origen, Boolean.TRUE);
			for(Estacion vecina : estaciones) {
				
				if(!recorridosDFS.containsKey(vecina) && grafoDFS.get(origen).get(vecina) != null && !origen.equals(vecina) && grafoDFS.get(origen).get(vecina).first.intValue() > 0) {
					
					int retornoDFS = dfs(vecina, destino, estaciones, Math.min(flux, grafoDFS.get(origen).get(vecina).first.intValue()));
					if(retornoDFS != -1) {
						estacionesMaximoFlujoAux.add(origen);
						grafoDFS.get(origen).put(vecina, new Pair<Double, ColorLineaDeTransporte>((grafoDFS.get(origen).get(vecina).first - retornoDFS), grafoDFS.get(origen).get(vecina).second));
						flujoEncontradoEnDFS = Math.min(flujoEncontradoEnDFS, grafo.get(origen).get(vecina).first.intValue());
						return retornoDFS;
					}
				
				}
				
			}
			
		}
		else {
			estacionesMaximoFlujoAux.add(origen);
			return flux;
		}
		return -1;
	}
	
	/**
	 * Algoritmo de DFS (Busqueda en profundidad)
	 * <p>
	 * Este algoritmo  busca en profundidad dentro de un grafo dado. Esta reimplementado para poder encontrar caminos de A a B y generar
	 * un grafo nuevo (un subgrafo de las estaciones que llegan de A a B). Requiere que se ejecute previamente "initDFSAaB".
	 * @param origen
	 * @param destino
	 * @param estaciones
	 * @param estacionesEnIteracionActual
	 */
	private void dfs(Estacion origen, Estacion destino, List<Estacion> estaciones, Deque<Pair<Estacion, ColorLineaDeTransporte>> estacionesEnIteracionActual) {
		//System.out.println("Origen: " + origen + " Destino: " + destino);
		//System.out.println("Y la lista en este momento es de " + estacionesEnIteracionActual);
		if(!origen.equals(destino)) {
			
			recorridosDFS.put(origen, Boolean.TRUE);
			for(Estacion vecina : estaciones) {
				System.out.println("trabajando de origen " + origen + " a vecina " + vecina);
				if(!recorridosDFS.containsKey(vecina) && grafo.get(origen).get(vecina) != null && !origen.equals(vecina) && grafo.get(origen).get(vecina).first.intValue() > 0) {
					System.out.println("Entrando desde " + origen + " a " + vecina);
					estacionesEnIteracionActual.addLast(new Pair<Estacion, ColorLineaDeTransporte>(vecina, grafo.get(origen).get(vecina).second));
					dfs(vecina, destino, estaciones, estacionesEnIteracionActual);
					estacionesEnIteracionActual.pop();
				}
			}
			
		}
		else {
			//System.out.println("Anadiendo + " + estacionesEnIteracionActual);
			estacionesDeAaB.add(new LinkedList(estacionesEnIteracionActual));
			//System.out.println("Estaciones de a b en este momento: " + estacionesDeAaB);
		}
		System.out.println("Saliendo de " + origen);
	}
	
	/**
	 * Inicializa recorridosDFS y estacionesDeAaB para la funcion dfs (solo la que tiene cuarto parametro Deque<Pair<Estacion, ColorLineaDeTransporte>)
	 */
	private void initDFSAaB() {
		recorridosDFS = new HashMap<Estacion, Boolean>();
		estacionesDeAaB = new ArrayList<>();
	}
	
	/**
	 * Ejecuta el algoritmo de Ford Fulkerson.
	 * <p>
	 * El algoritmo se encarga de encontrar el maximo flujo en un grafo dado. Este grafo tiene que ser un subgrafo con las estaciones que llegan de A a B.
	 * Para esta implementacion se utilizo  una lista de estaciones que reemplaza al subgrafo combinandola con el grafo original del grafo completo.
	 * <p>
	 * La implementacion de Ford Fulkerson se puede realizar con un algoritmo de DFS o uno BFS (busqueda en profundidad). Se decidio usar DFS porque es el que
	 * se utilizo durante el resto del trabajo practico.
	 *  
	 * @param estacionesIngreso
	 * @param lineas
	 * @param estaciones
	 * @param origen
	 * @param destino
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int fordFulkerson(List<Estacion> estacionesIngreso, List<LineaDeTransporte> lineas, List<Estacion> estaciones, Estacion origen, Estacion destino) throws ClassNotFoundException, SQLException {
		recorridosDFS = new HashMap<>();
		estacionesMaximoFlujoAux = new ArrayList<>();
		grafoDFS = copyGrafo(estacionesIngreso);
		int retornoDFS = dfs(origen, destino, estaciones, 100000);
		int flujoMaximo = 0;
		estacionesMaximoFlujo = estacionesMaximoFlujoAux;
		while(retornoDFS != -1) {
			recorridosDFS.clear();
			recorridosDFS = new HashMap<>();
			if(flujoMaximo < flujoEncontradoEnDFS) {
				flujoMaximo = flujoEncontradoEnDFS;
				estacionesMaximoFlujo = estacionesMaximoFlujoAux;
			}
			flujoEncontradoEnDFS = 1000000;
			estacionesMaximoFlujoAux = new ArrayList<>();
			retornoDFS = dfs(origen, destino, estaciones, 100000);
		}
		Collections.reverse(estacionesMaximoFlujo);
		return flujoMaximo;
	}
	
	/**
	 * Busca el camino mas barato.
	 * <p>
	 * Esta funcion se encarga de resolver el inciso 5. Segun el dato que se ingrese en datoQueRequiere,
	 * pasara a la funcion initMatriz. lo que corresponda, y luego retornara una lista con el camino acorde al pedido. 
	 * 
	 * @param estaciones
	 * @param lineas
	 * @param origenEntrada
	 * @param destinoEntrada
	 * @param datoQueRequiere
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Estacion> caminoPedido(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Estacion origenEntrada, Estacion destinoEntrada, Pedido datoQueRequiere) throws ClassNotFoundException, SQLException {
		Deque<Estacion> retorno = new LinkedList<>();
		
		Estacion origen = estaciones.stream()
									.filter(estacion -> estacion.id.equals(origenEntrada.id))
									.findFirst()
									.get();
		Estacion destino = estaciones.stream()
									 .filter(estacion -> estacion.id.equals(destinoEntrada.id))
									 .findFirst()
									 .get();
		
		init(estaciones, origen);
		initMatriz(estaciones, lineas, datoQueRequiere);
		dijkstra(estaciones, lineas, origen, destino);
		
		Integer iteracion = padre.get(destino.id);
		retorno.addFirst(destino);
		while(!iteracion.equals(origen.id)) {
			int thisIteracion = iteracion;
			retorno.addFirst(estaciones.stream()
									   .filter(estacion -> estacion.id.equals(Integer.valueOf(thisIteracion)))
									   .findFirst()
									   .get());
			iteracion = padre.get(iteracion);
		}
		retorno.addFirst(origen);
		return retorno.stream().collect(Collectors.toList());
	}
	
	/**
	 * Busca el maximo flujo (con peso) de A a B.
	 * <p>
	 * Utiliza una implementacion de ford fulkerson (llamado a funcion) obteniendo previamente la lista de estaciones que debe usar con el
	 * algoritmo de floyd, y la funcion que revisa cuales se corresponden al/los camino/s de A a B.
	 * 
	 * @param estaciones
	 * @param lineas
	 * @param origenEntrada
	 * @param destinoEntrada
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int mayorPesoDeAaB(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Estacion origenEntrada, Estacion destinoEntrada) throws ClassNotFoundException, SQLException {
		Estacion origen = estaciones.stream()
									.filter(estacion -> estacion.id.equals(origenEntrada.id))
									.findFirst()
									.get();
		Estacion destino = estaciones.stream()
				 					 .filter(estacion -> estacion.id.equals(destinoEntrada.id))
				 					 .findFirst()
				 					 .get();
		initMatriz(estaciones, lineas, Pedido.MAXIMOPESO);
		System.out.println(grafo);
		System.out.println("TODOS LOS CAMINOS: " + getCaminos(estaciones, origenEntrada, destinoEntrada));
		HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoFloyd = floydwarshall(copyGrafo(estaciones), estaciones);
		List<Estacion> listaDeEstaciones = estacionesAB(grafoFloyd, origen, destino, estaciones);
		return fordFulkerson(estaciones, lineas, listaDeEstaciones, origen, destino);
	}
	
	/**
	 * Devuelve las estaciones con maximo flujo. Se debe ejecutar previamente mayorPesoDeAaB.
	 * @return
	 */
	public List<Estacion> getEstacionesMaximoFlujo() {
		return estacionesMaximoFlujo;
	}
	
	
	/**
	 * Devuelve el grafo. Se requiere ejecutar previamente initMatriz.
	 * @return
	 */
	public HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> getGrafo() {
		return grafo;
	}
	
	
	/**
	 * Retorna un subgrafo de A a B.
	 * <p>
	 * Utilizando DFS, revisa que estaciones forman parte del grafo que va de A a B, y lo devuelve con una distancia de 1 para cada par de nodos al cual se pueda llegar.
	 * @param estaciones
	 * @param origenEntrada
	 * @param destinoEntrada
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> subGrafoConConexionesAB(List<Estacion> estaciones, Estacion origenEntrada, Estacion destinoEntrada) throws ClassNotFoundException, SQLException {
		HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoRetorno = new HashMap<>();
		Estacion origen = estaciones.stream()
									.filter(estacion -> estacion.id.equals(origenEntrada.id))
									.findFirst()
									.get();
		Estacion destino = estaciones.stream()
				 					 .filter(estacion -> estacion.id.equals(destinoEntrada.id))
				 					 .findFirst()
				 					 .get();
		
		HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoFloyd = floydwarshall(copyGrafo(estaciones), estaciones);
		List<Estacion> listaDeEstaciones = estacionesAB(grafoFloyd, origen, destino, estaciones);
		
		for(Estacion estacionA : listaDeEstaciones) {
			grafoRetorno.put(estacionA, new HashMap<Estacion, Pair<Double,ColorLineaDeTransporte>>());
			for(Estacion estacionB : listaDeEstaciones) {
				if(!estacionA.equals(estacionB)) {
					if(grafo.get(estacionA).containsKey(estacionB)) grafoRetorno.get(estacionA).put(estacionB, new Pair<Double, ColorLineaDeTransporte>(Double.valueOf(1),grafo.get(estacionA).get(estacionB).second));
				}
			}
		}
		return grafoRetorno;
	}
	
	/**
	 * Retorna los caminos de A a B.
	 * <p>
	 * Utilizando la lsita de estaciones y el grafo, encuentra el subgrafo que tiene las conexiones de A a B y lo retorna en forma de listas de listas (lista de Deque). 
	 * @param estaciones
	 * @param origenEntrada
	 * @param destinoEntrada
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Deque<Pair<Estacion, ColorLineaDeTransporte>>> getCaminos(List<Estacion> estaciones, Estacion origenEntrada, Estacion destinoEntrada) throws ClassNotFoundException, SQLException {
		Estacion origen = estaciones.stream()
				.filter(estacion -> estacion.id.equals(origenEntrada.id))
				.findFirst()
				.get();
		Estacion destino = estaciones.stream()
							 .filter(estacion -> estacion.id.equals(destinoEntrada.id))
							 .findFirst()
							 .get();
		HashMap<Estacion,HashMap<Estacion,Pair<Double,ColorLineaDeTransporte>>> grafoFloyd = floydwarshall(copyGrafo(estaciones), estaciones);
		List<Estacion> listaDeEstaciones = estacionesAB(grafoFloyd, origen, destino, estaciones);
		
		initDFSAaB();
		System.out.println("Estaciones: " + estaciones);
		System.out.println("Grafo: " + grafo);
		
		dfs(origen, destino, estaciones, new LinkedList<>());
		estacionesDeAaB.stream().forEach(d -> d.addFirst(new Pair<Estacion, ColorLineaDeTransporte>(origen, null)));
		return estacionesDeAaB;
	}

}
