package tests;


import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;

class ABMLineaDeTransporte {

	
	@Test
	void SearchNombreTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l=new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoLinea.ACTIVO);
		l.add(l1);
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
		LineaDeTransporte l2 = adminLineas.crearLineaDeTransporte("Linea 1",Color.BLUE, EstadoLinea.ACTIVO);
		LineaDeTransporte l3 = adminLineas.crearLineaDeTransporte("Linea 1",Color.GREEN, EstadoLinea.INACTIVO);
		LineaDeTransporte l4 = adminLineas.crearLineaDeTransporte("Linea 1",Color.YELLOW, EstadoLinea.INACTIVO);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
		
		
	}
	
	@Test
	void SearchColorTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l=new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoLinea.ACTIVO);
		l.add(l1);
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
		LineaDeTransporte l2 = adminLineas.crearLineaDeTransporte("Linea 2",Color.RED, EstadoLinea.ACTIVO);
		LineaDeTransporte l3 = adminLineas.crearLineaDeTransporte("Linea 3",Color.RED, EstadoLinea.INACTIVO);
		LineaDeTransporte l4 = adminLineas.crearLineaDeTransporte("Linea 4",Color.RED, EstadoLinea.INACTIVO);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		assert(l.equals(adminLineas.buscarLineaDeTransporte(Color.RED)));
		
	}
	
	void modifyTest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l = new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoLinea.ACTIVO);
		
		l.add(l1);
		
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
		adminLineas.modificarLineaDeTransporte(l1,"Linea 5", Color.MAGENTA, EstadoLinea.ACTIVO);
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 5")));
	}
	
	@Test
	void Deletetest() {
		AdministradorDeLineasDeTransporte adminLineas = new AdministradorDeLineasDeTransporte();
		ArrayList<LineaDeTransporte> l = new ArrayList<LineaDeTransporte>();
		LineaDeTransporte l1 = adminLineas.crearLineaDeTransporte("Linea 1",Color.RED, EstadoLinea.ACTIVO);
		l.add(l1);
		
		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
		adminLineas.borrarLineaDeTransporte(l1);
		l.remove(l1);

		assert(l.equals(adminLineas.buscarLineaDeTransporte("Linea 1")));
	}
	
}
