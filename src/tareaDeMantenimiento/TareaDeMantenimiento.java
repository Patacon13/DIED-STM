package tareaDeMantenimiento;

import java.time.LocalDate;

import estacion.Estacion;

public class TareaDeMantenimiento {
	protected LocalDate fechaInicioTarea;
	protected LocalDate fechaFinTarea;
	protected String observaciones;
	protected Estacion estacion;
	protected Integer id;
	
	public TareaDeMantenimiento(Integer id, LocalDate fechaInicioTarea, LocalDate fechaFinTarea, String observaciones, Estacion estacion){
		this.fechaInicioTarea=fechaInicioTarea;
		this.fechaFinTarea=fechaFinTarea;
		this.observaciones=observaciones;
		this.estacion=estacion;
		this.id= id;
	}
	
	public TareaDeMantenimiento() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getFechaInicioTarea() {
		return fechaInicioTarea;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFechaInicioTarea(LocalDate fechaInicioTarea) {
		this.fechaInicioTarea = fechaInicioTarea;
	}

	public void setFechaFinTarea(LocalDate fechaFinTarea) {
		this.fechaFinTarea = fechaFinTarea;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public LocalDate getFechaFinTarea() {
		return fechaFinTarea;
	}
	

}
