package estacion;

import java.time.LocalDate;
import java.util.ArrayList;


public class AdministradorDeEstaciones {
	ArrayList<Estacion> estaciones = new ArrayList<>(); //FIXME: cuando se pase a bases de datos esto se debe eliminar
	
	boolean createEstacion(Integer id, String nombre, LocalDate horarioApertura, LocalDate horarioCierre, EstadoEstacion estado) {
		return addEstacion(new Estacion(id, nombre, horarioApertura, horarioCierre, estado));
	}
	
	boolean addEstacion(Estacion e) {
		return estaciones.add(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	boolean modifyEstacion(Estacion e, Integer id, String nombre, LocalDate horarioApertura, LocalDate horarioCierre, EstadoEstacion estado) {
		e.id = id;
		e.nombre = nombre;
		e.horarioApertura = horarioApertura;
		e.horarioCierre = horarioCierre;
		e.estado = estado;
		return true; //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	boolean deleteEstacion(Estacion e) {
		return estaciones.remove(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	Estacion searchEstacion(Integer id) {
		for(Estacion e : estaciones) 
			if(e.id.compareTo(id) == 0) return e;
		return null; //FIXME: Se podria aplicar optional. Pero como es un boceto sin bases de datos lo hice con null
	}
}
