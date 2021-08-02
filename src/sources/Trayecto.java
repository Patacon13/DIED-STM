package sources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import estacion.Estacion;
import lineaDeTransporte.LineaDeTransporte;

public class Trayecto {
private ArrayList<Ruta> rutas; //tramos del trayecto
private LineaDeTransporte lineaDeTransporte; // a que linea de transporte pertenece el trayecto, aunque en lineaDeTransporte deberia estar
private SistemaDeTransporte sistema; //A que sistema pertenece

public Trayecto() {
	super();
	this.rutas = new ArrayList<Ruta>();
} 

public Trayecto(SistemaDeTransporte sistema) {
	super();
	this.rutas = new ArrayList<Ruta>();
	this.sistema = sistema;
}

public Trayecto(ArrayList<Ruta> rutas) {
	this.rutas = rutas;
}

public boolean agregarTramo(Ruta ruta) {
	return rutas.add(ruta);
	
}

public ArrayList<Ruta> getRutas() {
	return rutas;
}

public LineaDeTransporte getLineaDeTransporte() {
	return lineaDeTransporte;
}

public boolean contieneA(Estacion estacion) {
	return rutas.stream().anyMatch(ruta -> ruta.getOrigen().equals(estacion) && ruta.estaActiva());
}

public Boolean llegaA(Estacion estacion, Estacion destino) {
	for(Ruta r : rutas) {
		if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return true;
	}
	return false;
}

public Double costoAAdyacente(Estacion estacion, Estacion destino) {
	for(Ruta r : rutas) {
		if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCosto();
	}
	return null;
}

public Double distanciaAAdyacente(Estacion estacion, Estacion destino) {
	for(Ruta r : rutas) {
		if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getKilometros();
	}
	return null;
}


public Integer duracionAAdyacente(Estacion estacion, Estacion destino) {
	for(Ruta r : rutas) {
		if(r.getOrigen().equals(estacion) && r.estaActiva()) return r.getDuracion();
	}
	return null;
}

public Integer pesoA(Estacion estacion, Estacion destino) {
	for(Ruta r : rutas) {
		if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCantMax();
	}
	return null;
}

}


