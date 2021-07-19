package estacion;

import java.util.ArrayList;
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
	
	public Map<Estacion,Map<Estacion,Double>> grafo;
	public List<Integer> padre;
	public List<Double> costos;
	public List<Boolean> visitado;
	//el admin se debe reemplazar por la base de datos
	public List<Estacion> getCaminoMasRapido(AdministradorDeLineasDeTransporte admin, Estacion origen, Estacion destino) {
		Queue<Estacion> aProcesar = new LinkedList<>();
		Map<Estacion, List<Estacion>> recorridos = new HashMap<>();
		aProcesar.add(origen);
		
		List<Estacion> lista = new ArrayList<>();
		lista.add(origen);
		recorridos.put(origen, lista);
		
		while(!aProcesar.isEmpty()) {
			Estacion estacion = aProcesar.poll();
			List<Estacion> estacionesLlega = admin.lineas.stream().filter(linea -> (linea.contieneA(estacion) && linea.estaActiva()))
								 .map(linea -> linea.getTrayecto().llegaA(estacion))
								 .collect(Collectors.toList());
			for(Estacion estacionALaQueLlega : estacionesLlega) {
				if(!recorridos.containsKey(estacionALaQueLlega)) {
					List<Estacion> recorridoHastaEstaEstacion = recorridos.get(estacion).stream().collect(Collectors.toList());
					if(!estacion.equals(origen)) recorridoHastaEstaEstacion.add(estacion);
					recorridos.put(estacionALaQueLlega, recorridoHastaEstaEstacion);
					aProcesar.add(estacionALaQueLlega);
				}
			}
		}
		return recorridos.get(destino);
	}
		
	
	private void initMatriz(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, Pedido datoQueRequiere) {
		for(Estacion estacionA : adminEstaciones.estaciones) {
			grafo.put(estacionA, new HashMap<>());
			for(Estacion estacionB : adminEstaciones.estaciones) {
				
				if(estacionA.equals(estacionB)) grafo.get(estacionA).put(estacionB, Double.valueOf(0));
				else {
					for(LineaDeTransporte linea : adminLineas.lineas) {
						if(linea.contieneA(estacionA) && linea.llegaA(estacionA).equals(estacionB) && linea.estaActiva()) { //Si contiene al origen, y llega a la estacionB como destino
							switch(datoQueRequiere) {
							case MASRAPIDO:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(estacionA).doubleValue());
								else if(linea.costoAAdyacente(estacionA) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.duracionAAdyacente(estacionA).doubleValue());
								break;
							case MENORDISTANCIA:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(estacionA));
								else if(linea.costoAAdyacente(estacionA) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.distanciaAAdyacente(estacionA));
								break;
							default:
								if(grafo.get(estacionA).get(estacionB) == null) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(estacionA));
								else if(linea.costoAAdyacente(estacionA) < grafo.get(estacionA).get(estacionB)) grafo.get(estacionA).put(estacionB, linea.costoAAdyacente(estacionA));
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
		grafo = new HashMap<>();
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
	
}
