package sources;

import estacion.Estacion;
import lineaDeTransporte.EstadoTransporte;

public class Ruta {
	private Estacion origen;
	private Estacion destino;
	private Double kilometros;
	private Integer duracion;
	private Integer cantMax;
	private EstadoTransporte estado;
	private Double costo;
	
	
	public Ruta(Estacion origen, Estacion destino, Double kilometros, Integer duracion, Integer cantMax, EstadoTransporte estado,
			Double costo) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.kilometros = kilometros;
		this.duracion = duracion;
		this.cantMax = cantMax;
		this.estado = estado;
		this.costo = costo;
	}


	public Estacion getOrigen() {
		return origen;
	}

	public Double getKilometros() {
		return kilometros;
	}
	
	public Estacion getDestino() {
		return destino;
	}

	public Integer getDuracion() {
		return duracion;
	}


	public Integer getCantMax() {
		return cantMax;
	}


	public EstadoTransporte getEstado() {
		return estado;
	}
	
	public boolean estaActiva() {
		return estado == EstadoTransporte.ACTIVO;
	}


	public Double getCosto() {
		return costo;
	}
	
	
	
}
