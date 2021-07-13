package lineaDeTransporte;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdministradorDeLineasDeTransporte {
	public ArrayList<LineaDeTransporte> lineas = new ArrayList<>(); 
	
	public LineaDeTransporte crearLineaDeTransporte(String nombre, Color color, EstadoLinea estado) {
		LineaDeTransporte linea = new LineaDeTransporte(nombre,color,estado);
		addlinea(linea);
		return linea;
	}
	
	public boolean addlinea(LineaDeTransporte linea) {
		return lineas.add(linea);  
	}
	
	public boolean modificarLineaDeTransporte(LineaDeTransporte linea,String nombre, Color color, EstadoLinea estado) {
		linea.nombre= nombre;
		linea.color= color;
		linea.estado=estado;
		return true; 
	}
	
	public boolean borrarLineaDeTransporte(LineaDeTransporte linea) {
		return lineas.remove(linea); 
	}
	
	public List<LineaDeTransporte> buscarLineaDeTransporte(String nombre) {
		return lineas.stream().
					  filter(linea -> linea.nombre.compareTo(nombre) == 0).
					  collect(Collectors.toList());
	}
	public List<LineaDeTransporte> buscarLineaDeTransporte(Color color) {
		return lineas.stream().
					  filter(linea -> linea.color.equals(color)).
					  collect(Collectors.toList());
	}
	
	public List<LineaDeTransporte> buscarLineaDeTransporte(EstadoLinea estado) {
		return lineas.stream().
					  filter(linea -> linea.estado.compareTo(estado) == 0).
					  collect(Collectors.toList());
	}
	
	
	
}
