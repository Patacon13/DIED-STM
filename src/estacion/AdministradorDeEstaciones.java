package estacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tareaDeMantenimiento.AdministradorDeTareas;



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
	
	public Estacion searchEstacion(Integer id) {
		for(Estacion e : estaciones) if(e.id.equals(id)) return e;
		return null;
	}
	public List<Estacion> searchEstacion(String nombre) {
		return estaciones.stream().
				  filter(estacion -> estacion.nombre.compareTo(nombre) == 0).
				  collect(Collectors.toList());
	}
	
	public void modifyState(Estacion e, AdministradorDeTareas a) {
		if(e.estado==EstadoEstacion.OPERATIVA) {
			a.createTareaDeMantenimiento(LocalDate.now(),null,"",e);
			e.estado = EstadoEstacion.EN_MANTENIMIENTO;
		}
		else {
			if(e.estado==EstadoEstacion.EN_MANTENIMIENTO) {
				a.registrarFin(e);
				e.estado = EstadoEstacion.OPERATIVA;
			}
			
		}
	}
}
