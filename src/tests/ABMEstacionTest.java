package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import estacion.*;

class ABMEstacionTest {

	
	/*
	void searchIdTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Estacion> estacionesTest = new ArrayList<>();
		estacionesTest.add(e1);
		
		assertEquals(adminEstaciones.searchEstacion(1), estacionesTest);
		
		adminEstaciones.createEstacion(Integer.valueOf(2), "San Martin", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
		
		assertEquals(adminEstaciones.searchEstacion(1), estacionesTest);
		
	}
	
	@Test
	void searchMultipleIdTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Estacion> estacionesTest = new ArrayList<>();
		estacionesTest.add(e1);
		
		assertEquals(adminEstaciones.searchEstacion(1), estacionesTest);
		
		Estacion e2 = adminEstaciones.createEstacion(Integer.valueOf(1), "San Martin", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(1), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
		
		estacionesTest.add(e2);
		estacionesTest.add(e4);
		
		assertEquals(adminEstaciones.searchEstacion(1), estacionesTest);
		
	}
	
	@Test
	void searchNombreTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Estacion> estacionesTest = new ArrayList<>();
		estacionesTest.add(e1);
		
		assertEquals(adminEstaciones.searchEstacion("Belgrano"), estacionesTest);
		
		adminEstaciones.createEstacion(Integer.valueOf(2), "San Martin", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
		
		assertEquals(adminEstaciones.searchEstacion("Belgrano"), estacionesTest);
		
	}
	
	@Test
	void searchMultipleNombreTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Estacion> estacionesTest = new ArrayList<>();
		estacionesTest.add(e1);
		
		assertEquals(adminEstaciones.searchEstacion(1), estacionesTest);
		
		Estacion e2 = adminEstaciones.createEstacion(Integer.valueOf(2), "Belgrano", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Belgrano", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
		
		estacionesTest.add(e2);
		estacionesTest.add(e4);
		
		assertEquals(adminEstaciones.searchEstacion("Belgrano"), estacionesTest);
		
	}
	
	@Test
	void modifyTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		assertEquals(adminEstaciones.searchEstacion(4), adminEstaciones.searchEstacion("Rosas"));
		
		//adminEstaciones.modifyEstacion(e4, 4, "Alberdi", LocalTime.of(04, 56), LocalTime.of(22, 15), EstadoEstacion.EN_MANTENIMIENTO);

		assertEquals(adminEstaciones.searchEstacion(4), adminEstaciones.searchEstacion("Alberdi"));
		assertNotEquals(adminEstaciones.searchEstacion(4), adminEstaciones.searchEstacion("Rosas"));
	}
	
	@Test
	void DeleteTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		ArrayList<Estacion> estacionesTest = new ArrayList<>();
		estacionesTest.add(e4);
		
		assertEquals(adminEstaciones.searchEstacion(4), estacionesTest);
		
		//adminEstaciones.deleteEstacion("e");

		assertEquals(adminEstaciones.searchEstacion(4), new ArrayList<>());
	}
	*/
	
}
