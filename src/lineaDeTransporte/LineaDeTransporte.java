package lineaDeTransporte;

import java.sql.SQLException;
import java.util.List;

import estacion.Estacion;
import ruta.AdministradorDeRutas;
import ruta.Ruta;

public class LineaDeTransporte {


	public LineaDeTransporte(Integer id, String nombre, ColorLineaDeTransporte color , EstadoLinea estado) {
		this.id=id;
		this.nombre=nombre; 
		this.color=color; //CAMBIAR COLOR A UN ENUM COLOR
		this.estado = estado;
	}
	
	protected Integer id;
	protected String nombre;
	protected ColorLineaDeTransporte color; //Se utilizo awt ya que no es para la parte grafica, es solo la definicion de un atributo de la linea.
	protected EstadoLinea estado;
	
	private AdministradorDeRutas administradorDeRutas = new AdministradorDeRutas();
	
	//protected Trayecto trayecto;
	//protected ArrayList<Trayecto> trayectos;
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
	/*public ArrayList<Trayecto> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(ArrayList<Trayecto> trayectos) {
		this.trayectos = trayectos;
	}*/
	
	public boolean contieneA(Estacion estacion) throws ClassNotFoundException, SQLException {
		return administradorDeRutas.getRutas(this).stream().anyMatch(ruta -> ruta.getOrigen().equals(estacion) && ruta.estaActiva());
	}

	public Boolean llegaA(Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : administradorDeRutas.getRutas(this)) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return true;
		}
		return false;
	}

	public Double costoAAdyacente(Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : administradorDeRutas.getRutas(this)) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCosto();
		}
		return null;
	}

	public Double distanciaAAdyacente(Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : administradorDeRutas.getRutas(this)) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getKilometros();
		}
		return null;
	}


	public Integer duracionAAdyacente(Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : administradorDeRutas.getRutas(this)) {
			if(r.getOrigen().equals(estacion) && r.estaActiva()) return r.getDuracion();
		}
		return null;
	}

	public Integer pesoA(Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : administradorDeRutas.getRutas(this)) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCantMax();
		}
		return null;
	}
	
	//Las mismas funciones pero con listas para reducir los tiempos
	
	public boolean contieneA(List<Ruta> rutas, Estacion estacion) {
		return rutas.stream().anyMatch(ruta -> ruta.getOrigen().equals(estacion) && ruta.estaActiva());
	}

	public Boolean llegaA(List<Ruta> rutas, Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : rutas) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return true;
		}
		return false;
	}

	public Double costoAAdyacente(List<Ruta> rutas, Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : rutas) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCosto();
		}
		return null;
	}

	public Double distanciaAAdyacente(List<Ruta> rutas, Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : rutas) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getKilometros();
		}
		return null;
	}


	public Integer duracionAAdyacente(List<Ruta> rutas, Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : rutas) {
			if(r.getOrigen().equals(estacion) && r.estaActiva()) return r.getDuracion();
		}
		return null;
	}

	public Integer pesoA(List<Ruta> rutas, Estacion estacion, Estacion destino) throws ClassNotFoundException, SQLException {
		for(Ruta r : rutas) {
			if(r.getOrigen().equals(estacion) && r.estaActiva() && r.getDestino().equals(destino)) return r.getCantMax();
		}
		return null;
	}
	
		
	public boolean estaActiva() {
		return estado == EstadoLinea.ACTIVO;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
