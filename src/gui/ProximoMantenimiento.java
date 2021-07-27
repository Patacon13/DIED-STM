package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;

public class ProximoMantenimiento extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProximoMantenimiento() {
		//Monticulo m = new Monticulo(100);
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		try {
			ArrayList<Estacion> estaciones =  admin.getEstaciones("");
			//estaciones.stream().forEach(e->m.insertar(e));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
