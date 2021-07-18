package conexionMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	 String URL = "jdbc:mysql://db4free.net:3306/sistdetransporte";
	 String USER = "tpdied";
	 String PASS = "tpdied1234";
	
	public Connection crearConexion() throws SQLException, ClassNotFoundException {
	Connection conn = null;
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(URL, USER, PASS);
	System.out.println("Conexión establecida");
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace(); //se captura cuando en el path no se encuentra la clase del driver
		System.out.println("Error de ClassNotFoundException");
		}
	catch(SQLException e) {
		e.printStackTrace(); //se captura cuando la ip, pass, user están mal o algún problema de la BD
		System.out.println("Error de SQL");
	}
	return conn;
	
	}

}
