package lineaDeTransporte;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sources.Trayecto;


public class AdministradorDeLineasDeTransporte {
	public ArrayList<LineaDeTransporte> lineas = new ArrayList<>(); 
	
	public LineaDeTransporte crearLineaDeTransporte(String nombre, Color color, EstadoTransporte estado, Trayecto trayecto) {
		LineaDeTransporte linea = new LineaDeTransporte(nombre,color,estado, trayecto);
		addlinea(linea);
		return linea;
	}
		
	public boolean addlinea(LineaDeTransporte linea) {
		return lineas.add(linea);  
	}
	
	public boolean modifyLineaDeTransporte(LineaDeTransporte linea,String nombre, Color color, EstadoTransporte estado) {
		linea.nombre= nombre;
		linea.color= color;
		linea.estado=estado;
		return true; 
	}
	
	public boolean deleteLineaDeTransporte(LineaDeTransporte linea) {
		return lineas.remove(linea); 
	}
	
	public List<LineaDeTransporte> searchLineaDeTransporte(String nombre) {
		return lineas.stream().
					  filter(linea -> linea.nombre.compareTo(nombre) == 0).
					  collect(Collectors.toList());
	}
	public List<LineaDeTransporte> searchLineaDeTransporte(Color color) {
		return lineas.stream().
					  filter(linea -> linea.color.equals(color)).
					  collect(Collectors.toList());
	}
	
	public List<LineaDeTransporte> searchLineaDeTransporte(EstadoTransporte estado) {
		return lineas.stream().
					  filter(linea -> linea.estado.compareTo(estado) == 0).
					  collect(Collectors.toList());
	}
	
	
	
}
