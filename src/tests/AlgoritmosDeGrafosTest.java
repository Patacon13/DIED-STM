package tests;


import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import estacion.AdministradorDeCaminos;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;
import estacion.Pedido;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.EstadoTransporte;
import sources.Ruta;
import sources.Trayecto;

class AlgoritmosDeGrafosTest {

	
	@Test
	void maximoFlujoTest() {
		//quiero ir de la 1 a la 2
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		AdministradorDeLineasDeTransporte adminLineasDeTransporte = new AdministradorDeLineasDeTransporte();
		AdministradorDeCaminos adminCaminos = new AdministradorDeCaminos();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(0), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e2 = adminEstaciones.createEstacion(Integer.valueOf(1), "San Martin", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		Estacion e3 = adminEstaciones.createEstacion(Integer.valueOf(2), "Alberdi", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(3), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
		Estacion e5 = adminEstaciones.createEstacion(Integer.valueOf(4), "Roca", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e6 = adminEstaciones.createEstacion(Integer.valueOf(5), "Sarmiento", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		Estacion e7 = adminEstaciones.createEstacion(Integer.valueOf(6), "Illia", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Ruta> rutasT1 = new ArrayList<Ruta>();
		ArrayList<Ruta> rutasT2 = new ArrayList<Ruta>();
		
		Ruta e1Ae3 = new Ruta(e1, e3, Double.valueOf(3), Integer.valueOf(6), Integer.valueOf(5), EstadoTransporte.ACTIVO, Double.valueOf(10));
		Ruta e3Ae6 = new Ruta(e3, e6, Double.valueOf(3), Integer.valueOf(6), Integer.valueOf(5), EstadoTransporte.ACTIVO, Double.valueOf(10));
		Ruta e6Ae7 = new Ruta(e6, e7, Double.valueOf(3), Integer.valueOf(6), Integer.valueOf(5), EstadoTransporte.ACTIVO, Double.valueOf(10));
		Ruta e7Ae2 = new Ruta(e7, e2, Double.valueOf(3), Integer.valueOf(6), Integer.valueOf(5), EstadoTransporte.ACTIVO, Double.valueOf(10));
		rutasT1.add(e1Ae3);
		rutasT1.add(e3Ae6);
		rutasT1.add(e6Ae7);
		rutasT1.add(e7Ae2);
		
		Ruta e1Ae4 = new Ruta(e1, e4, Double.valueOf(20), Integer.valueOf(6), Integer.valueOf(3), EstadoTransporte.ACTIVO, Double.valueOf(10));
		Ruta e4Ae5 = new Ruta(e4, e5, Double.valueOf(20), Integer.valueOf(6), Integer.valueOf(3), EstadoTransporte.ACTIVO, Double.valueOf(5));
		Ruta e5Ae2 = new Ruta(e5, e2, Double.valueOf(20), Integer.valueOf(6), Integer.valueOf(3), EstadoTransporte.ACTIVO, Double.valueOf(5));
		rutasT2.add(e1Ae4);
		rutasT2.add(e4Ae5);
		rutasT2.add(e5Ae2);
		
		Trayecto t1 = new Trayecto(rutasT1);
		Trayecto t2 = new Trayecto(rutasT2);
		
		adminLineasDeTransporte.crearLineaDeTransporte("Sol", null, EstadoTransporte.ACTIVO, t1);
		adminLineasDeTransporte.crearLineaDeTransporte("Tierra", null, EstadoTransporte.ACTIVO, t2);
		
		ArrayList<Estacion> listaUno = new ArrayList<>();
		listaUno.add(e1);
		listaUno.add(e3);
		listaUno.add(e6);
		listaUno.add(e7);
		listaUno.add(e2);
		
		List<Estacion> listaDos = new ArrayList<>();
		listaDos.add(e1);
		listaDos.add(e4);
		listaDos.add(e5);
		listaDos.add(e2);
		
		assertEquals(adminCaminos.mayorPesoDeAaB(adminEstaciones, adminLineasDeTransporte, e1, e2), 5);
	}
	
}
