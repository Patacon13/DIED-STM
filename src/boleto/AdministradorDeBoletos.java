package boleto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import estacion.AdministradorDeCaminos;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;
import estacion.Pedido;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;


public class AdministradorDeBoletos {
	public List<Boleto> boletos = new ArrayList<>();
	
	public Boleto createBoleto(Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
							   String nombreEstacionOrigen, String nombreEstacionDestino, List<Estacion> caminoASeguir) {
		Boleto b = new Boleto(numero, correoElectronico, nombre, fechaDeVenta, nombreEstacionOrigen, nombreEstacionDestino, caminoASeguir);
		boletos.add(b);
		return b;
	}
	
	public Boleto createBoleto(Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
			String nombreEstacionOrigen, String nombreEstacionDestino) {
			Boleto b = new Boleto(numero, correoElectronico, nombre, fechaDeVenta, nombreEstacionOrigen, nombreEstacionDestino);
			boletos.add(b);
			return b;
	}
	
	public boolean addBoleto(Boleto e) {
		return boletos.add(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public boolean modifyBoleto(Boleto b, Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
			   String nombreEstacionOrigen, String nombreEstacionDestino, List<Estacion> caminoASeguir) {
		b.numero = numero;
		b.correoElectronico = correoElectronico;
		b.nombre = nombre;
		b.fechaDeVenta = fechaDeVenta;
		b.nombreEstacionOrigen = nombreEstacionOrigen;
		b.nombreEstacionDestino = nombreEstacionDestino;
		b.caminoASeguir = caminoASeguir;
		return true; //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public boolean modifyBoleto(Boleto b, Integer numero, String correoElectronico, String nombre, LocalDate fechaDeVenta,
			   String nombreEstacionOrigen, String nombreEstacionDestino) {
		b.numero = numero;
		b.correoElectronico = correoElectronico;
		b.nombre = nombre;
		b.fechaDeVenta = fechaDeVenta;
		b.nombreEstacionOrigen = nombreEstacionOrigen;
		b.nombreEstacionDestino = nombreEstacionDestino;
		return true; //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public boolean deleteBoleto(Boleto e) {
		return boletos.remove(e); //FIXME: debera revisar si pudo ser correctamente insertado en la base de datos
	}
	
	public Boleto searchBoleto(Integer numero) {
		for(Boleto b : boletos) if(b.numero.equals(numero)) return b;
		return null;
	}
	
	//Crear otros searchs en el de base de datos. No tiene mucho sentido hacerlo aca
	/*
	public void addTrayecto(AdministradorDeEstaciones adminEstaciones, AdministradorDeLineasDeTransporte adminLineas, AdministradorDeCaminos admin, Boleto boleto, Estacion origen, Estacion destino, Pedido pedido) {
		boleto.caminoASeguir = admin.caminoMasBarato(adminEstaciones, adminLineas, origen, destino, pedido);
	}*/
	
	public String getCorreoElectronico(Boleto b) {
		return b.correoElectronico;
	}
}
