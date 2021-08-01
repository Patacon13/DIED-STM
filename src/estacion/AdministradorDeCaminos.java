package estacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import ruta.*;

public class AdministradorDeCaminos {
	
	public HashMap<Estacion,HashMap<Estacion,Double>> grafo;
	public HashMap<Estacion,HashMap<Estacion,Double>> grafoDFS;
	public List<Integer> padre;
	public List<Double> costos;
	public List<Boolean> visitado;
	public int flujoEncontradoEnDFS = 100000;
	private HashMap<Estacion, Boolean> recorridosDFS;
	private List<Estacion> estacionesMaximoFlujo;
	private List<Estacion> estacionesMaximoFlujoAux;
	
	//el admin se debe reemplazar por la base de datos
		
	private void initMatriz(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Pedido datoQueRequiere) throws ClassNotFoundException, SQLException {
		grafo = new HashMap<>();
		AdministradorDeRutas admin = new AdministradorDeRutas();
		for(Estacion estacionA : estaciones) {
			grafo.put(estacionA, new HashMap<>());
			for(Estacion estacionB : estaciones){
				
				if(estacionA.equals(estacionB)) grafo.get(estacionA).put(estacionB, Double.valueOf(0));
				else {
					for(LineaDeTransporte linea : lineas){
						List<Ruta> rutas = admin.getRutas(linea);
						if(linea.contieneA(rutas, estacionA) && linea.llegaA(rutas, estacionA, estacionB) && linea.estaActiva()) { //Si contiene al origen, y llega a la estacionB como destino
							switch(datoQueRequiere) {
							case MASRAPIDO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(estacionA, estacionB).doubleValue());
								else if(linea.duracionAAdyacente(rutas, estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(rutas, estacionA, estacionB).doubleValue());
								break;
							case MENORDISTANCIA:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(rutas, estacionA, estacionB));
								else if(linea.distanciaAAdyacente(rutas,estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(rutas, estacionA, estacionB));
								break;
							case MASBARATO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(rutas, estacionA, estacionB));
								else if(linea.costoAAdyacente(rutas, estacionA, estacionB) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(rutas, estacionA, estacionB));
							default:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.pesoA(rutas, estacionA, estacionB).doubleValue());
								else if(linea.pesoA(rutas, estacionA, estacionB) > grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.pesoA(rutas, estacionA, estacionB).doubleValue());
							}
						}
					}
				}
				
			}
			
		}
	}
	
	
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
				if(grafo.get(cercana).get(adyacente) != null && costos.get(adj)>costos.get(cercanaId)+grafo.get(cercana).get(adyacente)) {
					costos.set(adj, costos.get(cercanaId)+grafo.get(cercana).get(adyacente));
					padre.set(adj, cercanaId);
				}
			}
		}
		
		
	}	
	
	private HashMap<Estacion, HashMap<Estacion, Double>> copyGrafo(List<Estacion> estaciones) throws ClassNotFoundException, SQLException {
		HashMap<Estacion,HashMap<Estacion,Double>> grafoRetorno = new HashMap<Estacion,HashMap<Estacion,Double>>(grafo);
		
		for(Estacion estacionA : estaciones){
			
			grafoRetorno.put(estacionA, new HashMap<Estacion, Double>(grafo.get(estacionA)));
		
			
		}
		return grafoRetorno;
	}
	
	private HashMap<Estacion,HashMap<Estacion,Double>> floydwarshall(HashMap<Estacion,HashMap<Estacion,Double>> grafoRetorno, List<Estacion> estaciones) throws ClassNotFoundException, SQLException {
		for(Estacion estacionA : estaciones){
			grafoRetorno.put(estacionA, new HashMap<Estacion, Double>(grafoRetorno.get(estacionA)));
			for(Estacion estacionB : estaciones) {
				for(Estacion estacionC : estaciones) {
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
	
	public List<Estacion> subGrafoAB(HashMap<Estacion,HashMap<Estacion,Double>> grafoFloyd, Estacion origen, Estacion destino, List<Estacion> estacionesIngreso) throws ClassNotFoundException, SQLException {
		List<Estacion> estaciones = new ArrayList<>();
		for(Estacion estacion : estacionesIngreso)
			if(grafoFloyd.get(origen).get(estacion) != null && grafoFloyd.get(estacion).get(destino) != null) estaciones.add(estacion);
		
		return estaciones;	
	}
	
	private int dfs(Estacion origen, Estacion destino, List<Estacion> estaciones, int flux) {
		if(!origen.equals(destino)) {
			
			recorridosDFS.put(origen, Boolean.TRUE);
			for(Estacion vecina : estaciones) {
				
				if(!recorridosDFS.containsKey(vecina) && grafoDFS.get(origen).get(vecina) != null && !origen.equals(vecina) && grafoDFS.get(origen).get(vecina).intValue() > 0) {
					
					int retornoDFS = dfs(vecina, destino, estaciones, Math.min(flux, grafoDFS.get(origen).get(vecina).intValue()));
					if(retornoDFS != -1) {
						estacionesMaximoFlujoAux.add(origen);
						grafoDFS.get(origen).put(vecina, grafoDFS.get(origen).get(vecina) - retornoDFS);
						flujoEncontradoEnDFS = Math.min(flujoEncontradoEnDFS, grafo.get(origen).get(vecina).intValue());
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
	
	public List<Estacion> caminoMasBarato(List<Estacion> estaciones, List<LineaDeTransporte> lineas, Estacion origenEntrada, Estacion destinoEntrada, Pedido datoQueRequiere) throws ClassNotFoundException, SQLException {
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
		HashMap<Estacion,HashMap<Estacion,Double>> grafoFloyd = floydwarshall(copyGrafo(estaciones), estaciones);
		List<Estacion> listaDeEstaciones = subGrafoAB(grafoFloyd, origen, destino, estaciones);
		return fordFulkerson(estaciones, lineas, listaDeEstaciones, origen, destino);
	}
	
	public List<Estacion> getEstacionesMaximoFlujo() {
		return estacionesMaximoFlujo;
	}
	
	
	public HashMap<Estacion,HashMap<Estacion,Double>> subGrafoConConexionesAB(List<Estacion> estaciones, Estacion origenEntrada, Estacion destinoEntrada) throws ClassNotFoundException, SQLException {
		HashMap<Estacion,HashMap<Estacion,Double>> grafoRetorno = new HashMap<>();
		Estacion origen = estaciones.stream()
									.filter(estacion -> estacion.id.equals(origenEntrada.id))
									.findFirst()
									.get();
		Estacion destino = estaciones.stream()
				 					 .filter(estacion -> estacion.id.equals(destinoEntrada.id))
				 					 .findFirst()
				 					 .get();
		
		HashMap<Estacion,HashMap<Estacion,Double>> grafoFloyd = floydwarshall(copyGrafo(estaciones), estaciones);
		List<Estacion> listaDeEstaciones = subGrafoAB(grafoFloyd, origen, destino, estaciones);
		
		for(Estacion estacionA : listaDeEstaciones) {
			grafoRetorno.put(estacionA, new HashMap<Estacion, Double>());
			for(Estacion estacionB : listaDeEstaciones) {
				if(!estacionA.equals(estacionB)) {
					if(grafo.get(estacionA).containsKey(estacionB)) grafoRetorno.get(estacionA).put(estacionB, Double.valueOf(1));
				}
			}
		}
		return grafoRetorno;
	}
}
