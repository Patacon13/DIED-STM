package estacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import sources.Trayecto;

public class AdministradorDeCaminos {
	
	public HashMap<Estacion,HashMap<Estacion,Double>> grafo;
	public HashMap<Estacion,HashMap<Estacion,Double>> grafoDFS;
	public List<Integer> padre;
	public List<Double> costos;
	public List<Boolean> visitado;
	public int flujoEncontradoEnDFS = 100000;
	HashMap<Estacion, Boolean> recorridosDFS;
	List<Estacion> estacionesMaximoFlujo;
	List<Estacion> estacionesMaximoFlujoAux;
	
	//el admin se debe reemplazar por la base de datos
		
	private void initMatriz(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, Pedido datoQueRequiere) {
		grafo = new HashMap<>();
		for(Estacion estacionA : adminEstaciones.estaciones) {
			grafo.put(estacionA, new HashMap<>());
			for(Estacion estacionB : adminEstaciones.estaciones) {
				
				if(estacionA.equals(estacionB)) grafo.get(estacionA).put(estacionB, Double.valueOf(0));
				else {
					for(LineaDeTransporte linea : adminLineas.lineas) {
						System.out.println("Con linea " + linea + " iterando " + estacionA + " " + estacionB + "contiene? " + linea.contieneA(estacionA));
						if(linea.contieneA(estacionA) && linea.llegaA(estacionA, estacionB) && linea.estaActiva()) { //Si contiene al origen, y llega a la estacionB como destino
							System.out.println("Y llega");
							switch(datoQueRequiere) {
							case MASRAPIDO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(estacionA, estacionB).doubleValue());
								else if(linea.duracionAAdyacente(estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(estacionA, estacionB).doubleValue());
								break;
							case MENORDISTANCIA:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(estacionA, estacionB));
								else if(linea.distanciaAAdyacente(estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(estacionA, estacionB));
								break;
							case MASBARATO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(estacionA, estacionB));
								else if(linea.costoAAdyacente(estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(estacionA, estacionB));
							default:
								System.out.println("iterando " + estacionA + " " + estacionB);
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.pesoA(estacionA, estacionB).doubleValue());
								else if(linea.pesoA(estacionA, estacionB) > grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.pesoA(estacionA, estacionB).doubleValue());
							}
						}
					}
				}
				
			}
			
		}
	}
	
	
	private void init(AdministradorDeEstaciones adminEstaciones, Estacion origen) {
		padre = new ArrayList<>();
		costos = new ArrayList<>();
		visitado = new ArrayList<>();
		for(int i = 0; i<adminEstaciones.estaciones.size(); i++) {
			visitado.add(Boolean.FALSE);
			costos.add(Double.valueOf(Double.MAX_VALUE));
			padre.add(i);
		}
		costos.set(origen.id, Double.valueOf(0));
	}
	
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
	
	private void dijkstra(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, Estacion origen, Estacion destino) {
		
		for(Double costo : costos) {
			int cercanaId = getCercano(costos);
			Estacion cercana = adminEstaciones.searchEstacion(Integer.valueOf(cercanaId));
			visitado.set(cercanaId, true);
			for(int adj = 0; adj < costos.size(); adj++) {
				Estacion adyacente = adminEstaciones.searchEstacion(Integer.valueOf(adj));
				if(adyacente != null && cercana != null) {
					if(grafo.get(cercana).get(adyacente) != null && costos.get(adj)>costos.get(cercanaId)+grafo.get(cercana).get(adyacente)) {
						costos.set(adj, costos.get(cercanaId)+grafo.get(cercana).get(adyacente));
						padre.set(adj, cercanaId);
					}
				}
			}
		}
		
		
	}	
	
	private HashMap<Estacion, HashMap<Estacion, Double>> copyGrafo(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas) {
		HashMap<Estacion,HashMap<Estacion,Double>> grafoRetorno = new HashMap<Estacion,HashMap<Estacion,Double>>(grafo);
		for(Estacion estacionA : adminEstaciones.estaciones)
			grafoRetorno.put(estacionA, new HashMap<Estacion, Double>(grafoRetorno.get(estacionA)));
		return grafoRetorno;
	}
	
	private HashMap<Estacion,HashMap<Estacion,Double>> floydwarshall(HashMap<Estacion,HashMap<Estacion,Double>> grafoRetorno, AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas) {
		for(Estacion estacionA : adminEstaciones.estaciones) {
			grafoRetorno.put(estacionA, new HashMap<Estacion, Double>(grafoRetorno.get(estacionA)));
			for(Estacion estacionB : adminEstaciones.estaciones) {
				for(Estacion estacionC : adminEstaciones.estaciones) {
					if(grafoRetorno.get(estacionB) != null && grafoRetorno.get(estacionA) != null)
						if(grafoRetorno.get(estacionB).get(estacionA) != null && grafoRetorno.get(estacionA).get(estacionC) != null) {
							if(grafoRetorno.get(estacionB).get(estacionC) != null) grafoRetorno.get(estacionB).put(estacionC, Math.min(grafoRetorno.get(estacionB).get(estacionC),grafoRetorno.get(estacionB).get(estacionA) + grafoRetorno.get(estacionA).get(estacionC)));
							else grafoRetorno.get(estacionB).put(estacionC, grafoRetorno.get(estacionB).get(estacionA) + grafoRetorno.get(estacionA).get(estacionC));
						}
				}
			}
		}
		return grafoRetorno;
	}
	
	private List<Estacion> subGrafoAB(HashMap<Estacion,HashMap<Estacion,Double>> grafoFloyd, Estacion origen, Estacion destino, AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas) {
		List<Estacion> estaciones = new ArrayList<>();
		
		for(Estacion estacion : adminEstaciones.estaciones) 
			if(grafoFloyd.get(origen).get(estacion) != null && grafoFloyd.get(estacion).get(destino) != null) estaciones.add(estacion);
		
		return estaciones;	
	}
	
	private int dfs(Estacion origen, Estacion destino, List<Estacion> estaciones, int flux) {
		if(!origen.equals(destino)) {
			
			recorridosDFS.put(origen, Boolean.TRUE);
			for(Estacion vecina : estaciones) {
				
				if(!recorridosDFS.containsKey(vecina) && grafoDFS.get(origen).get(vecina) != null && !origen.equals(vecina) && grafoDFS.get(origen).get(vecina).intValue() > 0) {
					
					if(grafoDFS.get(origen).get(vecina).intValue() == 0) System.out.println("Vamos a pasar 0 en " + origen + " destino " + vecina);
					int retornoDFS = dfs(vecina, destino, estaciones, Math.min(flux, grafoDFS.get(origen).get(vecina).intValue()));
					if(retornoDFS != -1) {
						estacionesMaximoFlujoAux.add(origen);
						System.out.println("a " + origen + " restando " + retornoDFS);
						grafoDFS.get(origen).put(vecina, grafoDFS.get(origen).get(vecina) - retornoDFS);
						flujoEncontradoEnDFS = Math.min(flujoEncontradoEnDFS, grafo.get(origen).get(vecina).intValue());
						return retornoDFS;
					}
				
				}
				
			}
			
		}
		else {
			estacionesMaximoFlujoAux.add(origen);
			System.out.println("primer retorno: " + flux);
			return flux;
		}
		return -1;
	}
	
	private int fordFulkerson(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, List<Estacion> estaciones, Estacion origen, Estacion destino) {
		recorridosDFS = new HashMap<>();
		estacionesMaximoFlujoAux = new ArrayList<>();
		grafoDFS = copyGrafo(adminEstaciones, adminLineas);
		int retornoDFS = dfs(origen, destino, estaciones, 100000);
		int flujoMaximo = 0;
		while(retornoDFS != -1) {
			recorridosDFS.clear();
			recorridosDFS = new HashMap<>();
			if(flujoMaximo < flujoEncontradoEnDFS) {
				flujoMaximo = flujoEncontradoEnDFS;
				estacionesMaximoFlujo = estacionesMaximoFlujoAux;
			}
			flujoEncontradoEnDFS = 1000000;
			System.out.println("retorno del dfs " + retornoDFS + " con estaciones " + estacionesMaximoFlujoAux);
			estacionesMaximoFlujoAux = new ArrayList<>();
			grafo.forEach((i,j) -> System.out.println("key: " + i + "value " + j));
			retornoDFS = dfs(origen, destino, estaciones, 100000);
		}
		Collections.reverse(estacionesMaximoFlujo);
		System.out.println(estacionesMaximoFlujo);
		return flujoMaximo;
	}
	
	public List<Estacion> caminoMasBarato(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, Estacion origen, Estacion destino, Pedido datoQueRequiere) {
		Deque<Estacion> retorno = new LinkedList<>();
		
		init(adminEstaciones, origen);
		initMatriz(adminEstaciones, adminLineas, datoQueRequiere);
		dijkstra(adminEstaciones, adminLineas, origen, destino);
		
		Integer iteracion = padre.get(destino.id);
		retorno.addFirst(destino);
		while(!iteracion.equals(origen.id)) {
			retorno.addFirst(adminEstaciones.searchEstacion(iteracion));
			iteracion = padre.get(iteracion);
		}
		retorno.addFirst(origen);
		return retorno.stream().collect(Collectors.toList());
	}
	
	public int mayorPesoDeAaB(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, Estacion origen, Estacion destino) {
		initMatriz(adminEstaciones, adminLineas, Pedido.MAXIMOPESO);
		grafo.forEach((i,j) -> System.out.println("key: " + i + "value " + j));
		HashMap<Estacion,HashMap<Estacion,Double>> grafoFloyd = floydwarshall(copyGrafo(adminEstaciones, adminLineas), adminEstaciones, adminLineas);
		List<Estacion> listaDeEstaciones = subGrafoAB(grafoFloyd, origen, destino, adminEstaciones, adminLineas);
		return fordFulkerson(adminEstaciones, adminLineas, listaDeEstaciones, origen, destino);
	}
	
	
	
	
}
