package lineaDeTransporte;

import java.awt.Color;
import java.util.ArrayList;

import sources.Trayecto;

public class LineaDeTransporte {

	protected String nombre;
	protected Color color; //Se utilizo awt ya que no es para la parte grafica, es solo la definicion de un atributo de la linea.
	protected EstadoLinea estado;
	protected ArrayList<Trayecto> trayectos;
	
	
}
