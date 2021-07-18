package tareaDeMantenimiento;

import java.time.LocalDate;
import java.util.ArrayList;

import estacion.Estacion;

public class AdministradorDeTareas {
	
public ArrayList<TareaDeMantenimiento> tareas = new ArrayList<>(); //FIXME: cuando se pase a bases de datos esto se debe eliminar
	
	//FIXME: puede que se pueda armar todo como static cuando tengamos la BD

	public TareaDeMantenimiento createTareaDeMantenimiento(LocalDate fechaInicioTarea, LocalDate fechaFinTarea, String observaciones, Estacion estacion){
		TareaDeMantenimiento t= new TareaDeMantenimiento(fechaInicioTarea,fechaFinTarea,observaciones, estacion);
		tareas.add(t);
		return t;
	}
	
	public TareaDeMantenimiento buscarTareaDeEstacion(Estacion e) {
		for(TareaDeMantenimiento tarea : tareas) 
			if(tarea.estacion.equals(e)) return tarea;
		return null;
		
	}
	public void registrarFin(Estacion e) {
		TareaDeMantenimiento t = buscarTareaDeEstacion(e);
		int indice = tareas.indexOf(t);
		t.fechaFinTarea = LocalDate.now();
		tareas.set(indice, t);
	}
	
	
	
}
