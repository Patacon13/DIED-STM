package conexionMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion2 {
	String URL = "jdbc:mysql://localhost:3306/tpdied";
	String USER = "usuario";
	String PASS = "contrasena";
	
	public Connection crearConexion() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USER, PASS);
		//System.out.println("Conexi�n establecida");
		return conn;
	}

}