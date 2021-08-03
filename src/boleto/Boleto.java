package boleto;

import java.time.LocalDate;
import java.util.List;

import estacion.Estacion;

public class Boleto {
	private Integer numero;
	private String correoElectronico;
	private String nombre;
	private LocalDate fechaDeVenta;
	private Estacion origen;
	private Estacion destino;
	private Double costo;
	private List<Estacion> caminoASeguir;


	public Boleto(Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
			Estacion origen, Estacion destino, Double costo, List<Estacion> caminoASeguir) {
		this.numero = numero;
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.fechaDeVenta = fechaDeVenta;
		this.origen = origen;
		this.destino = destino;
		this.caminoASeguir = caminoASeguir;
		this.costo = costo;
	}


	public Integer getNumero() {
		return numero;
	}
	
	public Double getCosto() {
		return costo;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public LocalDate getFechaDeVenta() {
		return fechaDeVenta;
	}


	public void setFechaDeVenta(LocalDate fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}


	public Estacion getOrigen() {
		return origen;
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


	public List<Estacion> getCaminoASeguir() {
		return caminoASeguir;
	}


	public void setCaminoASeguir(List<Estacion> caminoASeguir) {
		this.caminoASeguir = caminoASeguir;
	}

	
	
}
