package estacion;

import java.time.LocalTime;

public class Estacion {
	protected Integer id;
	protected String nombre;
	protected LocalTime horarioApertura;
	protected LocalTime horarioCierre;
	protected EstadoEstacion estado;
	
	protected Estacion(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoEstacion estado) {
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
}
