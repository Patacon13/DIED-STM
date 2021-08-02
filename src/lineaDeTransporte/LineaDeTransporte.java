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
	
	public Boolean llegaA(Estacion origen, Estacion destino) {
		return trayecto.llegaA(origen, destino);
	}
	
	public Double costoAAdyacente(Estacion origen, Estacion destino) {
		return trayecto.costoAAdyacente(origen, destino);
	}
	
	public Double distanciaAAdyacente(Estacion origen, Estacion destino) {
		return trayecto.distanciaAAdyacente(origen, destino);
	}
	
	public Integer duracionAAdyacente(Estacion origen, Estacion destino) {
		return trayecto.duracionAAdyacente(origen, destino);
	}
	
	public Integer pesoA(Estacion origen, Estacion destino) {
		return trayecto.pesoA(origen, destino);
	}
	
	public Trayecto getTrayecto() {
		return trayecto;
	}
	
	public boolean estaActiva() {
		return estado == EstadoTransporte.ACTIVO;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
