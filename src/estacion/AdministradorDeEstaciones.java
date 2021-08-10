package estacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import conexionMySQL.Conexion;
import pair.Pair;
import tareaDeMantenimiento.AdministradorDeTareas;


public class AdministradorDeEstaciones {
	private Map<Integer, Double> pageRank = new HashMap<Integer, Double>();
	private Map<Integer, Double> pageRankIt = new HashMap<Integer, Double>();
	private Conexion con = new Conexion();
	
	public Integer addEstacion(Estacion e) throws ClassNotFoundException, SQLException{
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, e.nombre);
		pstm.setTime(2,Time.valueOf(e.horarioApertura));
		pstm.setTime(3,Time.valueOf(e.horarioCierre));
		pstm.setString(4, e.estado.toString());
		Integer c = pstm.executeUpdate();

		if(e.getEstado() == EstadoEstacion.EN_MANTENIMIENTO) {
			ResultSet rs= pstm.getGeneratedKeys();
			Integer uid = 0;
			if(rs.next()){
				uid =rs.getInt(1);
			}
			e.setId(uid);
			AdministradorDeTareas admin = new AdministradorDeTareas();
			admin.createTareaDeMantenimiento(LocalDate.now(), null, "La estacion inició en mantenimiento", e);
			rs.close();
		}
		pstm.close();
		conn.close();
		return c;
	}
	
	public Map<Integer, Double> getPageRank() {
		return this.pageRank;
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
		Connection conn = new Conexion().crearConexion();
		PreparedStatement ps;
		ResultSet estaciones;
		
		if(sql.length() == 0) {
			ps = conn.prepareStatement("SELECT * FROM estacion");    
			estaciones = ps.executeQuery();
		} else {
		    ps = conn.prepareStatement("SELECT * FROM estacion WHERE nombre LIKE ? OR horario_apertura LIKE ? OR horario_cierre LIKE ? OR estado LIKE ? OR id LIKE ? "); 
			ps.setString(1, '%' + sql + '%');
			ps.setString(2, '%' + sql + '%');
			ps.setString(3, '%' + sql + '%');
			ps.setString(4, '%' + sql + '%');
			ps.setString(5, '%' + sql + '%');
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
	
	
	private ArrayList<Integer> idEstacionesQueLlegan(Integer idEstacion) throws SQLException, ClassNotFoundException{
			Connection conn = con.crearConexion();
			PreparedStatement ps = conn.prepareStatement("SELECT origen FROM ruta WHERE destino=?");
			ps.setInt(1, idEstacion);
			ResultSet estaciones = ps.executeQuery();
			ArrayList<Integer> retorno = new ArrayList<Integer>();
			while(estaciones.next()){
				retorno.add(estaciones.getInt(1));
			}
			estaciones.close();
			ps.close();
			conn.close();
			return retorno;
	}
	
	public void pageRank() throws SQLException, ClassNotFoundException{
		Connection conn = new Conexion().crearConexion();
		PreparedStatement ps;
		ResultSet estaciones;
		ArrayList<Estacion> lista = this.getEstaciones("");
		for(Estacion est : lista) {
			pageRank.put(est.getId(), 1.0);
		}
		for(Estacion est : lista) {
			pageRankIt.put(est.getId(), 0.0);
		}
		
		for(int i=0; i<pageRank.size(); i++) {
			for(Estacion est: lista) {
				pageRankInt(est.getId());
			}
			pageRankIt.forEach((k,v) -> {
				pageRank.put(k, v);
			});
		}
	}
	
	private void pageRankInt(Integer id) throws ClassNotFoundException, SQLException {
		Double d = 0.5;
		Double sumaPageRank = 0.0;
		for(Integer idEst : this.idEstacionesQueLlegan(id)) {
			sumaPageRank+= pageRank.get(idEst) / Double.valueOf(cantQueSalen(idEst));
		}
		pageRankIt.put(id, d + d*sumaPageRank);
	}

	private Integer cantQueSalen(Integer idEst) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM ruta WHERE origen=? GROUP BY origen");
		ps.setInt(1, idEst);
		ResultSet estaciones = ps.executeQuery();
		Integer retorno = 0;
		while(estaciones.next()){
			retorno = estaciones.getInt(1);
		}
		estaciones.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	

	
}
