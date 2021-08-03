package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;
import ruta.AdministradorDeRutas;
import ruta.Ruta;

import java.sql.SQLException;
import java.util.ArrayList;


public class RegistrarRuta extends JPanel {

	private JTextField rDistancia;
	private JTextField rDuracion;
	private JTextField rPasajeros;
	private JTextField rCosto;
	private JComboBox<Estacion> rOrigen;
	private JComboBox<Estacion> rDestino;
	private JComboBox<EstadoLinea> rEstado;
	private JButton rRegistrar;


	public RegistrarRuta(LineaDeTransporte linea) {
		construirInterfaz();
		try {
		    cargarEstaciones(rOrigen);
			cargarEstaciones(rDestino);
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar las estaciones.","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		rRegistrar.addActionListener(e-> {
			Integer  pasajeros = null, duracion = null;
			Double costo = null, distancia = null;
			if(rDistancia.getText().toString().length() == 0 || rPasajeros.getText().toString().length() == 0 || rDuracion.getText().toString().length() == 0 || rCosto.getText().toString().length() == 0) {
				 JOptionPane.showMessageDialog(this,"Dejaste algún campo vacio.","Error",JOptionPane.ERROR_MESSAGE);
			} else {
			try {
				distancia = Double.parseDouble(rDistancia.getText().toString());
				pasajeros = Integer.parseInt(rPasajeros.getText().toString());
				duracion = Integer.parseInt(rDuracion.getText().toString());
				costo = Double.parseDouble(rCosto.getText().toString());
				Estacion origen =  rOrigen.getItemAt(rOrigen.getSelectedIndex());
				Estacion destino = rOrigen.getItemAt(rDestino.getSelectedIndex());
				EstadoLinea estado = rEstado.getItemAt(rEstado.getSelectedIndex());

				Ruta nueva = new Ruta(null, origen, destino, distancia, duracion, pasajeros, estado, costo, linea.getId());
				AdministradorDeRutas admin = new AdministradorDeRutas();
				if(origen.equals(destino)) {
					 JOptionPane.showMessageDialog(this,"La estacion de origen no puede ser la misma que la de destino.","Error",JOptionPane.ERROR_MESSAGE);
				} else if(admin.getRutaCant(linea, origen, destino) > 0) {
					JOptionPane.showMessageDialog(this,"Ya existe una ruta entre las estaciones elegidas en esta linea.","Error",JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						admin.addRuta(nueva, linea);
						JOptionPane.showMessageDialog(this,"Se registró correctamente la ruta.","Info",JOptionPane.INFORMATION_MESSAGE);
						JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
						ventana.getContentPane().removeAll(); //Remover componentes
						ventana.getContentPane().add(new RegistrarTrayectoLinea(linea), BorderLayout.CENTER); 
						SwingUtilities.updateComponentTreeUI(ventana);
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(this,"Ocurrio un error. Intenta mas tarde.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			} catch (NumberFormatException f) {
				JOptionPane.showMessageDialog(this,"Algun campo completado no es numérico.","Error",JOptionPane.ERROR_MESSAGE);
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(this,"Error.","Error",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
			}
		});

	}
	
	private void  cargarEstaciones(JComboBox<Estacion> ests) throws ClassNotFoundException, SQLException {
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ArrayList<Estacion> estaciones = admin.getEstaciones("");
		estaciones.stream().forEach(e -> ests.addItem(e));

	}
	
	private void construirInterfaz() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("AGREGAR RUTA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estacion Origen");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estacion Destino");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 1;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
	    rOrigen= new JComboBox<Estacion>();
		GridBagConstraints gbc_rOrigen = new GridBagConstraints();
		gbc_rOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_rOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_rOrigen.gridx = 2;
		gbc_rOrigen.gridy = 2;
		add(rOrigen, gbc_rOrigen);

		
		JLabel lblNewLabel_8 = new JLabel("--->");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridx = 3;
		gbc_lblNewLabel_8.gridy = 2;
		add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		rDestino = new JComboBox<Estacion>();
		GridBagConstraints gbc_rDestino = new GridBagConstraints();
		gbc_rDestino.insets = new Insets(0, 0, 5, 5);
		gbc_rDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_rDestino.gridx = 4;
		gbc_rDestino.gridy = 2;
		add(rDestino, gbc_rDestino);
		
		JLabel lblNewLabel_3 = new JLabel("Distancia (Km): ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 5;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		rDistancia = new JTextField();
		GridBagConstraints gbc_rDistancia = new GridBagConstraints();
		gbc_rDistancia.insets = new Insets(0, 0, 5, 5);
		gbc_rDistancia.fill = GridBagConstraints.HORIZONTAL;
		gbc_rDistancia.gridx = 4;
		gbc_rDistancia.gridy = 5;
		add(rDistancia, gbc_rDistancia);
		rDistancia.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Duracion (Minutos): ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 6;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		rDuracion = new JTextField();
		GridBagConstraints gbc_rDuracion = new GridBagConstraints();
		gbc_rDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_rDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_rDuracion.gridx = 4;
		gbc_rDuracion.gridy = 6;
		add(rDuracion, gbc_rDuracion);
		rDuracion.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cant. Pasajeros: ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 7;
		add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		rPasajeros = new JTextField();
		GridBagConstraints gbc_rPasajeros = new GridBagConstraints();
		gbc_rPasajeros.insets = new Insets(0, 0, 5, 5);
		gbc_rPasajeros.fill = GridBagConstraints.HORIZONTAL;
		gbc_rPasajeros.gridx = 4;
		gbc_rPasajeros.gridy = 7;
		add(rPasajeros, gbc_rPasajeros);
		rPasajeros.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Estado: ");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 8;
		add(lblNewLabel_6, gbc_lblNewLabel_6);
		
	     rEstado = new JComboBox<EstadoLinea>();
		GridBagConstraints gbc_rEstado = new GridBagConstraints();
		gbc_rEstado.insets = new Insets(0, 0, 5, 5);
		gbc_rEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_rEstado.gridx = 4;
		gbc_rEstado.gridy = 8;
		rEstado.addItem(EstadoLinea.ACTIVO);
		rEstado.addItem(EstadoLinea.INACTIVO);
		add(rEstado, gbc_rEstado);
		
		JLabel lblNewLabel_7 = new JLabel("Costo: ");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 9;
		add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		rCosto = new JTextField();
		GridBagConstraints gbc_rCosto = new GridBagConstraints();
		gbc_rCosto.insets = new Insets(0, 0, 5, 5);
		gbc_rCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_rCosto.gridx = 4;
		gbc_rCosto.gridy = 9;
		add(rCosto, gbc_rCosto);
		rCosto.setColumns(10);
		
		rRegistrar = new JButton("Registrar ruta");
		GridBagConstraints gbc_rRegistrar = new GridBagConstraints();
		gbc_rRegistrar.gridwidth = 3;
		gbc_rRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_rRegistrar.gridx = 2;
		gbc_rRegistrar.gridy = 10;
		add(rRegistrar, gbc_rRegistrar);
		
	}
	

}
