package ruta;

import estacion.Estacion;
import lineaDeTransporte.EstadoLinea;

public class Ruta {
	private Estacion origen;
	private Estacion destino;
	private Double kilometros;
	private Integer duracion;
	private Integer cantMax;
	private EstadoLinea estado;
	private Double costo;
	private Integer id;
	private Integer idLinea;
	
	
	public Ruta(Integer id, Estacion origen, Estacion destino, Double kilometros, Integer duracion, Integer cantMax, EstadoLinea estado,
			Double costo, Integer idlinea) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.kilometros = kilometros;
		this.duracion = duracion;
		this.cantMax = cantMax;
		this.estado = estado;
		this.costo = costo;
		this.idLinea = idLinea;
	}


	public Estacion getOrigen() {
		return origen;
	}
	public Integer idLinea() {
		return idLinea;
	}
	


	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}


	public Estacion getDestino() {
		return destino;
	}


	public void setDestino(Estacion destino) {
		this.destino = destino;
	}


	public Double getKilometros() {
		return kilometros;
	}


	public void setKilometros(Double kilometros) {
		this.kilometros = kilometros;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}


	public Integer getCantMax() {
		return cantMax;
	}


	public void setCantMax(Integer cantMax) {
		this.cantMax = cantMax;
	}


	public EstadoLinea getEstado() {
		return estado;
	}


	public void setEstado(EstadoLinea estado) {
		this.estado = estado;
	}


	public Double getCosto() {
		return costo;
	}


	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public boolean estaActiva() {
		return estado == EstadoLinea.ACTIVO;
	}
	
	
	
}
