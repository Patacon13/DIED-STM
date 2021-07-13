package estacion;

import java.time.LocalDate;

public class Estacion {
	protected Integer id;
	protected String nombre;
	protected LocalDate horarioApertura;
	protected LocalDate horarioCierre;
	protected EstadoEstacion estado;
	
	protected Estacion(Integer id, String nombre, LocalDate horarioApertura, LocalDate horarioCierre, EstadoEstacion estado) {
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}
}
