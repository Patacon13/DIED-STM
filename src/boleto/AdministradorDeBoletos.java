package boleto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionMySQL.Conexion2;



public class AdministradorDeBoletos {
	public List<Boleto> boletos = new ArrayList<>();
	Conexion2 con = new Conexion2();


	public Integer addBoleto(Boleto b) throws ClassNotFoundException, SQLException{
		Connection conn = con.crearConexion();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO venta (correo_electronico_cli, nombre_cliente, fecha_venta, origen, destino, costo_boleto, caminoaseguir) VALUES (?,?,?,?,?,?,?)");
		pstm.setString(1, b.getCorreoElectronico());
		pstm.setString(2, b.getNombre());
		pstm.setDate(3, Date.valueOf(b.getFechaDeVenta()));
		pstm.setInt(4, b.getOrigen().getId());
		pstm.setInt(5, b.getDestino().getId());
		pstm.setDouble(6, b.getCosto());
		pstm.setString(7, b.getCaminoASeguir().toString());
		Integer c = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return c;
	}
	
	
}
