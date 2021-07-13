package sources;

import java.util.ArrayList;

public class Trayecto {
private ArrayList<Ruta> rutas; //tramos del trayecto
private LineaDeTransporte lineaDeTransporte; // a que linea de transporte pertenece el trayecto, aunque en lineaDeTransporte deberia estar

public Trayecto() {
	super();
	this.rutas = new ArrayList<Ruta>();
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




}


