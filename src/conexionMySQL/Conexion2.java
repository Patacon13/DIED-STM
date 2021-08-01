package conexionMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion2 {
	String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10428757";
	String USER = "sql10428757";
	String PASS = "wNW12qcwqW";
	
	public Connection crearConexion() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USER, PASS);
		//System.out.println("Conexi�n establecida");
		return conn;
	}

}