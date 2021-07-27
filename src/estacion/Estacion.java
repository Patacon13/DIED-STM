package estacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

import conexionMySQL.Conexion;
import tareaDeMantenimiento.TareaDeMantenimiento;

public class Estacion {
	private Conexion con = new Conexion();
	protected Integer id;
	protected String nombre;
	protected LocalTime horarioApertura;
	protected LocalTime horarioCierre;
	protected EstadoEstacion estado;
	
	public Estacion(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoEstacion estado) {
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	public EstadoEstacion getEstado() {
		return estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	public TareaDeMantenimiento obtenerUltimoMantenimiento() throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("SELECT * FROM mantenimiento WHERE estacion=?");
		pstm.setInt(1, this.getId());
		ResultSet rs = pstm.executeQuery();
		TareaDeMantenimiento ret = new TareaDeMantenimiento();
	    while(rs.next()) {
	    	ret.setId(rs.getInt(1));
	    	ret.setEstacion(this);
	    	ret.setFechaInicioTarea(rs.getDate(3).toLocalDate());
	    }
		pstm.close();
		conn.close();
	return ret;
	}
	
	
}
