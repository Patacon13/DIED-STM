package lineaDeTransporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionMySQL.Conexion;


public class AdministradorDeLineasDeTransporte {
	private Conexion con = new Conexion();

	
	public Integer addlinea(LineaDeTransporte linea) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO lineadetransporte (nombre, color, estado) VALUES (?,?,?)");
		pstm.setString(1, linea.getNombre());
		pstm.setString(2, linea.getColor().toString());
		pstm.setString(3, linea.getEstado().toString());
		Integer c = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return c;
	}
		
	public Integer modifyLineaDeTransporte(LineaDeTransporte vieja, LineaDeTransporte nueva) throws SQLException, ClassNotFoundException {
		//Obtener nombre de la direccion vieja y hacer el update en base a ese nombre
		PreparedStatement ps = null;
		Connection conn = con.crearConexion();
		ps = conn.prepareStatement("UPDATE lineadetransporte SET nombre=?, color=?, estado=? WHERE id=?");
		ps.setString(1, nueva.getNombre());
		ps.setString(2, nueva.getColor().toString());
		ps.setString(3, nueva.getEstado().toString());
		ps.setInt(4, vieja.getId());
		Integer c = ps.executeUpdate();
		ps.close();
		conn.close();
		return c;
	}
	
	public Integer deleteLineaDeTransporte(Integer id) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM lineadetransporte WHERE id=?");
		ps.setInt(1, id);
		Integer c = ps.executeUpdate();
		ps.close();
		conn.close();
		return c;
	}

	
	public ArrayList<LineaDeTransporte> getLineasDeTransporte(String sql) throws ClassNotFoundException, SQLException {
		ArrayList<LineaDeTransporte> retorno = new ArrayList<LineaDeTransporte>();
		Connection conn = con.crearConexion();
		PreparedStatement ps;
		ResultSet lineas;
		
		if(sql.length() == 0) {
			ps = conn.prepareStatement("SELECT * FROM lineadetransporte");    
			lineas = ps.executeQuery();
		} else {
			ps = conn.prepareStatement("SELECT * FROM lineadetransporte WHERE nombre LIKE ? OR color LIKE ? OR estado LIKE ? OR id LIKE?"); 
			ps.setString(1, '%' + sql + '%');
			ps.setString(2, '%' + sql + '%');
			ps.setString(3, '%' + sql + '%');
			ps.setString(4, '%' + sql + '%');
			lineas = ps.executeQuery();
		}
		while(lineas.next()){
		retorno.add(new LineaDeTransporte(lineas.getInt(1), lineas.getString(2), ColorLineaDeTransporte.valueOf(lineas.getString(3)), EstadoLinea.valueOf(lineas.getString(4))));
		  }
		lineas.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	/* crear search
	public List<LineaDeTransporte> searchLineaDeTransporte(EstadoTransporte estado) {
		return lineas.stream().
					  filter(linea -> linea.estado.compareTo(estado) == 0).
					  collect(Collectors.toList());
	}
	*/
	
	
	
}
