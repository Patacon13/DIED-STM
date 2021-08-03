package ruta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionMySQL.Conexion2;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;

public class AdministradorDeRutas {
	private Conexion2 con = new Conexion2();

	public ArrayList<Ruta> getRutas(LineaDeTransporte l) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ruta WHERE linea=?");
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ps.setInt(1, l.getId());
		ResultSet ruta = ps.executeQuery();
		ArrayList<Ruta> retorno = new ArrayList<Ruta>();
		while(ruta.next()){
			retorno.add(new Ruta(ruta.getInt(1), admin.getEstacion(ruta.getInt(2)), admin.getEstacion(ruta.getInt(3)), ruta.getDouble(8), ruta.getInt(4), ruta.getInt(5), EstadoLinea.valueOf(ruta.getString(6)), ruta.getDouble(7), ruta.getInt(9)));
		}
		ruta.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	public ArrayList<Ruta> getRutas() throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ruta");
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ResultSet ruta = ps.executeQuery();
		ArrayList<Ruta> retorno = new ArrayList<Ruta>();
		while(ruta.next()){
			retorno.add(new Ruta(ruta.getInt(1), admin.getEstacion(ruta.getInt(2)), admin.getEstacion(ruta.getInt(3)), ruta.getDouble(8), ruta.getInt(4), ruta.getInt(5), EstadoLinea.valueOf(ruta.getString(6)), ruta.getDouble(7), ruta.getInt(9)));
		}
		ruta.close();
		ps.close();
		conn.close();
		return retorno;
	}

	//Agregar trayecto
	
	public Integer addRuta(Ruta ruta, LineaDeTransporte linea) throws SQLException, ClassNotFoundException {

		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (?,?,?,?,?,?,?,?)");
		pstm.setInt(1, ruta.getOrigen().getId());
		pstm.setInt(2, ruta.getDestino().getId());
		pstm.setDouble(3, ruta.getDuracion().doubleValue());
		pstm.setInt(4, ruta.getCantMax());
		pstm.setString(5, ruta.getEstado().toString());
		pstm.setDouble(6, ruta.getCosto());
		pstm.setDouble(7, ruta.getKilometros());
		pstm.setInt(8, linea.getId());
		Integer c = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return c;
	}

	public Ruta getRuta(Integer id) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ruta WHERE id=?");
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ps.setInt(1, id);
		ResultSet ruta = ps.executeQuery();
		Ruta retorno = null;
		while(ruta.next()){
			retorno = new Ruta(ruta.getInt(1), admin.getEstacion(ruta.getInt(2)), admin.getEstacion(ruta.getInt(3)), ruta.getDouble(8), ruta.getInt(4), ruta.getInt(5), EstadoLinea.valueOf(ruta.getString(6)), ruta.getDouble(7), ruta.getInt(9));
		}
		ruta.close();
		ps.close();
		conn.close();
		return retorno;
	}
	
	public Integer getRutaCant(LineaDeTransporte linea, Estacion origen, Estacion destino) throws SQLException, ClassNotFoundException {
		Connection conn = con.crearConexion();
		PreparedStatement ps = conn.prepareStatement("SELECT id FROM ruta WHERE origen=? AND destino=? AND linea=?");
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ps.setInt(1, origen.getId());
		ps.setInt(2, destino.getId());
		ps.setInt(3, linea.getId());
		ResultSet ruta = ps.executeQuery();
		Integer count = 0;
		while(ruta.next()){
			count++;
		}
		ruta.close();
		ps.close();
		conn.close();
		return count;
	}
	

}
