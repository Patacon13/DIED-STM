package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import boleto.AdministradorDeBoletos;
import boleto.Boleto;

class BoletoTest {

	
	@Test
	void searchIdTest() {
		AdministradorDeBoletos admin = new AdministradorDeBoletos();
		Boleto enviado = admin.createBoleto(1, "tassenza@frsf.utn.edu.ar", "Tomas Assenza", LocalDate.of(2021, 07, 19), "Belgrano", "San Martin");
		
		assertEquals(admin.searchBoleto(1), enviado);
	}
	
	@Test
	void modifyTest() {
		AdministradorDeBoletos admin = new AdministradorDeBoletos();
		Boleto enviado = admin.createBoleto(1, "tassenza@frsf.utn.edu.ar", "Tomas Assenza", LocalDate.of(2021, 07, 19), "Belgrano", "San Martin");
		
		assertEquals("tassenza@frsf.utn.edu.ar", admin.getCorreoElectronico(enviado));
		
		admin.modifyBoleto(enviado, 1, "tassenza@frba.utn.edu.ar", "Tomas Assenza", LocalDate.of(2021, 07, 19), "Belgrano", "San Martin");
		
		
		assertEquals("tassenza@frba.utn.edu.ar", admin.getCorreoElectronico(enviado));
	}
	
	@Test
	void DeleteTest() {
		AdministradorDeBoletos admin = new AdministradorDeBoletos();
		Boleto enviado = admin.createBoleto(1, "tassenza@frsf.utn.edu.ar", "Tomas Assenza", LocalDate.of(2021, 07, 19), "Belgrano", "San Martin");
		
		assertEquals(admin.searchBoleto(1), enviado);
		
		admin.deleteBoleto(enviado);

		assertEquals(admin.searchBoleto(1), null);
	}
	
}
