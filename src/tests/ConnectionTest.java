package tests;

import conexionMySQL.Conexion;
import static org.junit.Assert.fail;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class ConnectionTest {

	@Test
	
	void getConnection() {
	Conexion con=new Conexion();
	try {
	con.crearConexion();
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
		fail();
	}
	catch(SQLException e) {
		e.printStackTrace();
		fail();
	}
	}
	

}
