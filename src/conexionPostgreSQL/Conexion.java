package conexionPostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static final String URL = "jdbc:postgresql://localhost:puerto/baseDeDatos";
	static final String USER = "nombreUsuario";
	static final String PASS = "passwordUsuario";
	
	public Connection crearConexion() throws SQLException, ClassNotFoundException {
	Connection conn = null;
	try {
	Class.forName("org.postgresql.Driver");
	conn = DriverManager.getConnection(URL, USER, PASS);
	System.out.println("Conexi�n establecida");
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace(); //se captura cuando en el path no se encuentra la clase del driver
		}
	catch(SQLException e) {
		e.printStackTrace(); //se captura cuando la ip, pass, user est�n mal o alg�n problema de la BD
	}
	return conn;
	}
}
