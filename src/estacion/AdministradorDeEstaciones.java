package estacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import conexionMySQL.Conexion2;


public class AdministradorDeEstaciones {
	private Conexion2 con = new Conexion2();
	
	public Integer addEstacion(Estacion e) throws ClassNotFoundException, SQLException{
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES (?,?,?,?)");
		pstm.setString(1, e.nombre);
		pstm.setTime(2,Time.valueOf(e.horarioApertura));
		pstm.setTime(3,Time.valueOf(e.horarioCierre));
		pstm.setString(4, e.estado.toString());
		Integer c = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return c;
	}
	
	public Integer modifyEstacion(Estacion vieja, Estacion nueva) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
		Connection conn = con.crearConexion();
		ps = conn.prepareStatement("UPDATE estacion SET nombre=?, horario_apertura=?, horario_cierre=?, estado=? WHERE id=?");
		ps.setString(1, nueva.getNombre());
		ps.setTime(2, Time.valueOf(nueva.getHorarioApertura()));
		ps.setTime(3, Time.valueOf(nueva.getHorarioCierre()));
		ps.setString(4, nueva.getEstado().toString());
		ps.setInt(5, vieja.getId());
		Integer c = ps.executeUpdate();
		ps.close();
		conn.close();
		return c;
	}
	

	public Integer deleteEstacion(Integer id) throws SQLException, ClassNotFoundException {
			Connection conn = con.crearConexion();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM estacion WHERE id=?");
			ps.setInt(1, id);
			Integer c = ps.executeUpdate();
			ps.close();
			conn.close();
			return c;
		}
	
	public Estacion getEstacion(Integer id) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM estacion WHERE id=?");
		ps.setInt(1, id);
		ResultSet estaciones = ps.executeQuery();
		Estacion retorno = null;
		while(estaciones.next()){
			 retorno = new Estacion(estaciones.getInt(1), estaciones.getString(2), estaciones.getTime(3).toLocalTime(), estaciones.getTime(4).toLocalTime(), EstadoEstacion.valueOf(estaciones.getString(5)));
		}
		estaciones.close();
		ps.close();
		conn.close();
		return retorno;
	}

	/*Si sql se deja vacio se devuelven todas las estaciones y si sql tiene algun valor se devuelven las estaciones que tengan
	en alguna columna ese valor
	*/
	public ArrayList<Estacion> getEstaciones(String sql) throws ClassNotFoundException, SQLException {
		ArrayList<Estacion> retorno = new ArrayList<Estacion>();
		Connection conn = new Conexion2().crearConexion();
		PreparedStatement ps;
		ResultSet estaciones;
		
		if(sql.length() == 0) {
			ps = conn.prepareStatement("SELECT * FROM estacion");    
			estaciones = ps.executeQuery();
		} else {
		    ps = conn.prepareStatement("SELECT * FROM estacion WHERE nombre=? OR horario_apertura=? OR horario_cierre=? OR estado=? OR id=? "); 
			ps.setString(1, sql);
			ps.setString(2, sql);
			ps.setString(3, sql);
			ps.setString(4, sql);
			ps.setString(5, sql);
			estaciones = ps.executeQuery();
		}
		while(estaciones.next()){
		retorno.add(new Estacion(estaciones.getInt(1), estaciones.getString(2), estaciones.getTime(3).toLocalTime(), estaciones.getTime(4).toLocalTime(), EstadoEstacion.valueOf(estaciones.getString(5))));
		  }
		estaciones.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	public ArrayList<Estacion> pageRank() throws SQLException, ClassNotFoundException{
		Connection conn = new Conexion2().crearConexion();
		PreparedStatement ps;
		ResultSet estaciones;
		ArrayList<Estacion> retorno = new ArrayList<Estacion>();
		ps = conn.prepareStatement("SELECT * FROM estacion AS est, (SELECT destino, count(*) AS cant FROM ruta GROUP BY destino) AS aux WHERE est.id = aux.destino ORDER BY aux.cant DESC;");
		
		estaciones = ps.executeQuery();
		while(estaciones.next()){
			retorno.add(new Estacion(estaciones.getInt(1), estaciones.getString(2), estaciones.getTime(3).toLocalTime(), estaciones.getTime(4).toLocalTime(), EstadoEstacion.valueOf(estaciones.getString(5))));
		}
		estaciones.close();
		ps.close();
		conn.close();
		return retorno;
	}
	

	
}
