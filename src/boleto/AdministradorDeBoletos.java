package boleto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sources.Trayecto;


public class AdministradorDeBoletos {
	List<Boleto> boletos = new ArrayList<>();
	
	public Boleto createBoleto(Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
							   String nombreEstacionOrigen, String nombreEstacionDestino, Trayecto caminoASeguir) {
		Boleto b = new Boleto(numero, correoElectronico, nombre, fechaDeVenta, nombreEstacionOrigen, nombreEstacionDestino, caminoASeguir);
		boletos.add(b);
		return b;
	}
}
