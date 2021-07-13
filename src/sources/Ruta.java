package sources;

public class Ruta {
	private Estacion origen;
	private Estacion destino;
	private Double kilometros;
	private Integer duracion;
	private Integer cantMax;
	private EstadoLinea estado;
	private Double costo;
	
	
	public Ruta(Estacion origen, Estacion destino, Double kilometros, Integer duracion, Integer cantMax, EstadoLinea estado,
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


	public Estacion getDestino() {
		return destino;
	}


	public Double getKilometros() {
		return kilometros;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public Integer getCantMax() {
		return cantMax;
	}


	public EstadoLinea getEstado() {
		return estado;
	}


	public Double getCosto() {
		return costo;
	}
	
	
	
}
