package estacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import conexionMySQL.Conexion2;
import tareaDeMantenimiento.TareaDeMantenimiento;

public class Estacion implements Comparable<Estacion>{
	private Conexion2 con = new Conexion2();
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
		return this.nombre + " " + this.id;
	}

	public TareaDeMantenimiento obtenerUltimoMantenimiento() throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("SELECT * FROM mantenimiento WHERE estacion=? AND id=(SELECT MAX(id) FROM mantenimiento WHERE estacion=?)");
		pstm.setInt(1, this.getId());
		pstm.setInt(2, this.getId());
		ResultSet rs = pstm.executeQuery();
		TareaDeMantenimiento ret = new TareaDeMantenimiento(null, LocalDate.of(1969, 1, 1), LocalDate.of(1969, 1, 1), null, null);
	    while(rs.next()) {
	    	ret.setId(rs.getInt(1));
	    	ret.setEstacion(this);
	    	ret.setFechaInicioTarea(rs.getDate(3).toLocalDate());
	    	if(rs.getDate(4) == null) {
	    		ret.setFechaFinTarea(LocalDate.of(2100, 1, 1));
	    	} else {
	    		ret.setFechaFinTarea(rs.getDate(4).toLocalDate());
	    	}
	    }
		pstm.close();
		conn.close();
	return ret;
	}
	

	@Override
	public int compareTo(Estacion o) {
		TareaDeMantenimiento ultimoMantenimientoThis;
		TareaDeMantenimiento ultimoMantenimientoO;
		try {
			ultimoMantenimientoThis = this.obtenerUltimoMantenimiento();
			ultimoMantenimientoO = o.obtenerUltimoMantenimiento();
			if(ultimoMantenimientoThis.getFechaFinTarea().isAfter(ultimoMantenimientoO.getFechaFinTarea())){
				//el mio termino despues
				return 1;
			} else if(ultimoMantenimientoThis.getFechaFinTarea().isBefore(ultimoMantenimientoO.getFechaFinTarea())) {
				//el del otro termino despues
				return -1;
			} else {
				//son iguales, o sea los dos nunca tuvieron mantenimiento o los dos iniciaron y no terminaron
				return 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
			
	}
	
	@Override
	public boolean equals(Object est) {
		Estacion e= (Estacion) est; 
		return this.id.equals(e.getId());

	}
}
