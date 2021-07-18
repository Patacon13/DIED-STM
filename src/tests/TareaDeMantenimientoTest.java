package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import estacion.*;
import tareaDeMantenimiento.AdministradorDeTareas;
import tareaDeMantenimiento.TareaDeMantenimiento;

class TareaDeMantenimientoTest {

	
	@Test
	void createTarea() {
		AdministradorDeTareas admin = new AdministradorDeTareas();
		AdministradorDeEstaciones adminEstaciones = new AdministradorDeEstaciones();
		Estacion e1 = adminEstaciones.createEstacion(Integer.valueOf(1), "Belgrano", LocalTime.of(07, 55), LocalTime.of(20, 55), EstadoEstacion.OPERATIVA);
		adminEstaciones.modifyState(e1, admin);
		
		assertEquals(admin.tareas.size(),1);
		
		adminEstaciones.modifyState(e1, admin);
		assertNotEquals(admin.buscarTareaDeEstacion(e1).getFechaFinTarea(), null);
		
	}
	
	
}
