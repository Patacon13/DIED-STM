package lineaDeTransporte;

import java.awt.Color;
import estacion.Estacion;
import sources.Trayecto;

public class LineaDeTransporte {

	public LineaDeTransporte(String nombre, Color color, EstadoTransporte estado, Trayecto trayecto) {
		this.nombre=nombre;
		this.color=color;
		this.estado=estado;
		this.trayecto = trayecto;
	}
	protected String nombre;
	protected Color color; //Se utilizo awt ya que no es para la parte grafica, es solo la definicion de un atributo de la linea.
	protected EstadoTransporte estado;
	protected Trayecto trayecto;
	
	public boolean contieneA(Estacion estacion) {
		return trayecto.contieneA(estacion);
	}
	
	public Estacion llegaA(Estacion origen) {
		return trayecto.llegaA(origen);
	}
	
	public Double costoAAdyacente(Estacion origen) {
		return trayecto.costoAAdyacente(origen);
	}
	
	public Double distanciaAAdyacente(Estacion origen) {
		return trayecto.distanciaAAdyacente(origen);
	}
	
	public Integer duracionAAdyacente(Estacion origen) {
		return trayecto.duracionAAdyacente(origen);
	}
	
	public Trayecto getTrayecto() {
		return trayecto;
	}
	
	public boolean estaActiva() {
		return estado == EstadoTransporte.ACTIVO;
	}
}
