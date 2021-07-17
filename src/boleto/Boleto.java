package boleto;

import java.time.LocalDate;
import java.util.List;

import sources.*;

public class Boleto {
	protected Integer numero;
	protected String correoElectronico;
	protected String nombre;
	protected LocalDate fechaDeVenta;
	protected String nombreEstacionOrigen;
	protected String nombreEstacionDestino;
	protected Trayecto caminoASeguir;
	protected Boleto(Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
			String nombreEstacionOrigen, String nombreEstacionDestino, Trayecto caminoASeguir) {
		this.numero = numero;
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.fechaDeVenta = fechaDeVenta;
		this.nombreEstacionOrigen = nombreEstacionOrigen;
		this.nombreEstacionDestino = nombreEstacionDestino;
		this.caminoASeguir = caminoASeguir;
	}
	
	
	
}
