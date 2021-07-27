package ruta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionMySQL.Conexion;
import estacion.AdministradorDeEstaciones;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;

public class AdministradorDeRutas {
	private Conexion con = new Conexion();

public ArrayList<Ruta> getRutas(LineaDeTransporte l) throws ClassNotFoundException, SQLException{
		ArrayList<Ruta> retorno = new ArrayList<Ruta>();
		Connection conn = con.crearConexion();
		PreparedStatement ps;
		ResultSet rutas;
			ps = conn.prepareStatement("SELECT ruta FROM listaestacioneslinea WHERE linea=?");   
			ps.setInt(1, l.getId());
			rutas = ps.executeQuery();
		while(rutas.next()){
		retorno.add(this.getRuta(rutas.getInt(1)));
		  }
		ps.close();
		conn.close();
		return retorno;
}


public Integer addRuta(Ruta ruta, LineaDeTransporte linea) throws SQLException, ClassNotFoundException {

	Connection conn = con.crearConexion();
	PreparedStatement pstm = conn.prepareStatement("INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros) VALUES (?,?,?,?,?,?,?)");
	pstm.setInt(1, ruta.getOrigen().getId());
	pstm.setInt(2, ruta.getDestino().getId());
	pstm.setDouble(3, ruta.getDuracion().doubleValue());
	pstm.setInt(4, ruta.getCantMax());
	pstm.setString(5, ruta.getEstado().toString());
	pstm.setDouble(6, ruta.getCosto());
	pstm.setDouble(7, ruta.getKilometros());
	Integer c = pstm.executeUpdate();
	pstm.close();
	Integer idRuta = this.maxId();
	PreparedStatement pstm2 = conn.prepareStatement("INSERT INTO listaestacioneslinea (linea, ruta) VALUES(?, ?)");
	pstm2.setInt(1, linea.getId());
	pstm2.setInt(2, idRuta);
	Integer d = pstm2.executeUpdate();
	pstm2.close();
	conn.close();
	return c+d;
}

public Ruta getRuta(Integer id) throws SQLException, ClassNotFoundException {
	Connection conn = con.crearConexion();
	PreparedStatement ps = conn.prepareStatement("SELECT * FROM ruta WHERE id=?");
	AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
	ps.setInt(1, id);
	ResultSet ruta = ps.executeQuery();
	Ruta retorno = null;
	while(ruta.next()){
		retorno = new Ruta(ruta.getInt(1), admin.getEstacion(ruta.getInt(2)), admin.getEstacion(ruta.getInt(3)), ruta.getDouble(8), ruta.getInt(4), ruta.getInt(5), EstadoLinea.valueOf(ruta.getString(6)), ruta.getDouble(7));
	}
	ps.close();
	conn.close();
	return retorno;
}

private Integer maxId() throws SQLException, ClassNotFoundException {
	Connection conn = con.crearConexion();
	PreparedStatement stat;
	ResultSet rs;
	String sql = "SELECT MAX(id) AS max_id FROM ruta";
	stat = conn.prepareStatement(sql);
	rs = stat.executeQuery();
	Integer retorno = 1;
	if (rs.next()) {
	    retorno = rs.getInt("max_id");
	}
	return retorno;
	
}







	
}
