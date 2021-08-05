package tareaDeMantenimiento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import conexionMySQL.Conexion;
import estacion.Estacion;

public class AdministradorDeTareas {
	
private Conexion con = new Conexion();

//Habria que recibir una tarea de mantenimiento directamente y ahi insertarla, no los atributos, ver eso
	public Integer createTareaDeMantenimiento(LocalDate fechaInicioTarea, LocalDate fechaFinTarea, String observaciones, Estacion estacion) throws SQLException, ClassNotFoundException{
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO mantenimiento (estacion, fechainicio, fechafin, observaciones) VALUES (?,?,?,?)");
		pstm.setInt(1,estacion.getId());
		pstm.setDate(2, Date.valueOf(fechaInicioTarea));
		pstm.setNull(3, Types.DATE);
		pstm.setString(4, observaciones);
		Integer c = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return c;

	}
	


	public void finalizarMantenimiento(Estacion nueva, LocalDate fin) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("UPDATE mantenimiento SET fechafin=? WHERE estacion=? AND fechafin IS NULL");
		pstm.setDate(1, Date.valueOf(fin));
		pstm.setInt(2, nueva.getId());
		pstm.close();
		conn.close();
	}
	
	public ArrayList<TareaDeMantenimiento>getMantenimientos(Estacion est) throws ClassNotFoundException, SQLException {
		LocalDate fechaFin;
		Connection conn = new Conexion().crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM mantenimiento WHERE estacion=? "); 
		ArrayList<TareaDeMantenimiento> retorno = new ArrayList<TareaDeMantenimiento>();
		ps.setInt(1, est.getId());
		ResultSet tareas = ps.executeQuery();
		while(tareas.next()){
			if(tareas.getDate(4) == null) {
				fechaFin = null;
			} else {
				fechaFin = tareas.getDate(4).toLocalDate();
			}
			retorno.add(new TareaDeMantenimiento(tareas.getInt(1), tareas.getDate(3).toLocalDate(), fechaFin, tareas.getString(5), new Estacion(tareas.getInt(2), null, null, null, null))); 
			  }
			tareas.close();
			ps.close();
			conn.close();
			return retorno;
	}
	
	public ArrayList<TareaDeMantenimiento> getTareasDeMatenimiento() throws ClassNotFoundException, SQLException {
		ArrayList<TareaDeMantenimiento> retorno = new ArrayList<TareaDeMantenimiento>();
		Connection conn = con.crearConexion();
		PreparedStatement ps;
		ResultSet tareas;

			ps = conn.prepareStatement("SELECT * FROM mantenimiento");    
			tareas = ps.executeQuery();
		
		while(tareas.next()){
		retorno.add(new TareaDeMantenimiento(tareas.getInt(1), tareas.getDate(3).toLocalDate(), tareas.getDate(4).toLocalDate(), tareas.getString(5), new Estacion(tareas.getInt(2), null, null, null, null)));
		  }
		tareas.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	
	
}
