package estacion;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdministradorDeEstaciones {
	public ArrayList<Estacion> estaciones = new ArrayList<>(); //FIXME: cuando se pase a bases de datos esto se debe eliminar
	
	//FIXME: puede que se pueda armar todo como static cuando tengamos la BD
	
	public Estacion createEstacion(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoEstacion estado) {
		Estacion e = new Estacion(id, nombre, horarioApertura, horarioCierre, estado);
		addEstacion(e);
		return e;
	}
	
	public boolean addEstacion(Estacion e) {
		return estaciones.add(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public boolean modifyEstacion(Estacion e, Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoEstacion estado) {
		e.id = id;
		e.nombre = nombre;
		e.horarioApertura = horarioApertura;
		e.horarioCierre = horarioCierre;
		e.estado = estado;
		return true; //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public boolean deleteEstacion(Estacion e) {
		return estaciones.remove(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public List<Estacion> searchEstacion(Integer id) {
		return estaciones.stream().
				  filter(estacion -> estacion.id.compareTo(id) == 0).
				  collect(Collectors.toList());
	}
	public List<Estacion> searchEstacion(String nombre) {
		return estaciones.stream().
				  filter(estacion -> estacion.nombre.compareTo(nombre) == 0).
				  collect(Collectors.toList());
	}
}
