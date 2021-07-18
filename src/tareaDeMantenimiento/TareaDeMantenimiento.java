package tareaDeMantenimiento;

import java.time.LocalDate;

import estacion.Estacion;

public class TareaDeMantenimiento {
	protected LocalDate fechaInicioTarea;
	protected LocalDate fechaFinTarea;
	protected String observaciones;
	protected Estacion estacion;
	
	protected TareaDeMantenimiento(LocalDate fechaInicioTarea, LocalDate fechaFinTarea, String observaciones, Estacion estacion){
		this.fechaInicioTarea=fechaInicioTarea;
		this.fechaFinTarea=fechaFinTarea;
		this.observaciones=observaciones;
		this.estacion=estacion;
	}
	
	public LocalDate getFechaFinTarea() {
		return fechaFinTarea;
	}
	

}
