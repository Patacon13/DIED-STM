package lineaDeTransporte;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionMySQL.Conexion;
import sources.Trayecto;

public class LineaDeTransporte {

	public LineaDeTransporte(Integer id, String nombre, ColorLineaDeTransporte color , EstadoLinea estado) {
		this.id=id;
		this.nombre=nombre; 
		this.color=color; //CAMBIAR COLOR A UN ENUM COLOR
		this.estado=estado;
	}
	protected Integer id;
	protected String nombre;
	protected ColorLineaDeTransporte color; //Se utilizo awt ya que no es para la parte grafica, es solo la definicion de un atributo de la linea.
	protected EstadoLinea estado;
	protected ArrayList<Trayecto> trayectos;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ColorLineaDeTransporte getColor() {
		return color;
	}
	public void setColor(ColorLineaDeTransporte color) {
		this.color = color;
	}
	public EstadoLinea getEstado() {
		return estado;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEstado(EstadoLinea estado) {
		this.estado = estado;
	}
	public ArrayList<Trayecto> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(ArrayList<Trayecto> trayectos) {
		this.trayectos = trayectos;
	}
	public ResultSet selectAllEstaciones() throws ClassNotFoundException, SQLException {
		Connection conn = new Conexion().crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM lineadetransporte");    
		ResultSet rs = ps.executeQuery();
		    return rs;
		 
	}
	
	
}
