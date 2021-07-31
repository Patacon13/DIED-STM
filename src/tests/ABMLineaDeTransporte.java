package tests;
/*

import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;

class ABMLineaDeTransporte {

	
	@Test
	void searchNombreTest() {
		/*
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		Trayecto t = new Trayecto();
		ArrayList<LineaDeTransporte> l=new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoTransporte.ACTIVO, t);
		l.add(l1);
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
		LineaDeTransporte l2 = adminLineas.crearLineaDeTransporte("Linea 1",Color.BLUE, EstadoTransporte.ACTIVO, t);
		LineaDeTransporte l3 = adminLineas.crearLineaDeTransporte("Linea 1",Color.GREEN, EstadoTransporte.INACTIVO, t);
		LineaDeTransporte l4 = adminLineas.crearLineaDeTransporte("Linea 1",Color.YELLOW, EstadoTransporte.INACTIVO, t);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
		
		
	}
	
	@Test
	void searchColorTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		Trayecto t = new Trayecto();
		ArrayList<LineaDeTransporte> l=new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoTransporte.ACTIVO, t);
		l.add(l1);
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
		LineaDeTransporte l2 = adminLineas.crearLineaDeTransporte("Linea 2",Color.RED, EstadoTransporte.ACTIVO, t);
		LineaDeTransporte l3 = adminLineas.crearLineaDeTransporte("Linea 3",Color.RED, EstadoTransporte.INACTIVO, t);
		LineaDeTransporte l4 = adminLineas.crearLineaDeTransporte("Linea 4",Color.RED, EstadoTransporte.INACTIVO, t);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		assert(l.equals(adminLineas.searchLineaDeTransporte(Color.RED)));
		
	}
	
	void modifyTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l = new ArrayList<LineaDeTransporte>();
		Trayecto t = new Trayecto();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoTransporte.ACTIVO, t);
		
		l.add(l1);
		
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
		adminLineas.modifyLineaDeTransporte(l1,"Linea 5", Color.MAGENTA, EstadoTransporte.ACTIVO);
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 5")));
	}
	
	@Test
	void deleteTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l = new ArrayList<LineaDeTransporte>();
		Trayecto t = new Trayecto();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoTransporte.ACTIVO, t);
		l.add(l1);
		
		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
		adminLineas.deleteLineaDeTransporte(l1);
		l.remove(l1);

		assert(l.equals(adminLineas.searchLineaDeTransporte("Linea 1")));
	}
}
}
*/