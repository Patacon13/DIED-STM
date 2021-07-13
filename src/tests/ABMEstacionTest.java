package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import estacion.*;

class ABMEstacionTest {

	
	@Test
	void SearchIdtest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e2 = adminEstaciones.createEstacion(Integer.valueOf(2), "San Martin", LocalTime.of(12, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e3 = adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		
		assertEquals(adminEstaciones.searchEstacion(1), e1);
		assertEquals(adminEstaciones.searchEstacion(2), e2);
		assertEquals(adminEstaciones.searchEstacion(3), e3);
		assertEquals(adminEstaciones.searchEstacion(4), e4);
		
	}
	
	@Test
	void SearchNombretest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		Estacion e2 = adminEstaciones.createEstacion(Integer.valueOf(2), "San Martin", LocalTime.of(12, 55), LocalTime.of(21, 55), EstadoEstacion.OPERATIVA);
		Estacion e3 = adminEstaciones.createEstacion(Integer.valueOf(3), "Bujias Hescher", LocalTime.of(07, 45), LocalTime.of(22, 55), EstadoEstacion.OPERATIVA);
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 52), EstadoEstacion.OPERATIVA);
				
		assertEquals(adminEstaciones.searchEstacion("Belgrano"), e1);
		assertEquals(adminEstaciones.searchEstacion("San Martin"), e2);
		assertEquals(adminEstaciones.searchEstacion("Bujias Hescher"), e3);
		assertEquals(adminEstaciones.searchEstacion("Rosas"), e4);
		
	}
	
	void modifyTest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
				
		assertEquals(adminEstaciones.searchEstacion(4), e4);
		
		adminEstaciones.modifyEstacion(e4, 4, "Alberdi", LocalTime.of(04, 56), LocalTime.of(22, 15), EstadoEstacion.EN_MANTENIMIENTO);

		assertEquals(adminEstaciones.searchEstacion(4), adminEstaciones.searchEstacion("Alberdi"));
	}
	
	@Test
	void Deletetest() {
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e4 = adminEstaciones.createEstacion(Integer.valueOf(4), "Rosas", LocalTime.of(06, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
				
		assertEquals(adminEstaciones.searchEstacion(4), e4);
		
		adminEstaciones.deleteEstacion(e4);

		assertEquals(adminEstaciones.searchEstacion(4), null);
	}
	
}
