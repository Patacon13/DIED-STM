package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.ColorLineaDeTransporte;
import lineaDeTransporte.EstadoLinea;
import lineaDeTransporte.LineaDeTransporte;
import tareaDeMantenimiento.AdministradorDeTareas;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.BorderLayout;
import java.awt.Color;

public class ModificarLineaDeTransporte extends JPanel {
	private JTextField linNombre;
	private JComboBox<ColorLineaDeTransporte> linColor;
	private JComboBox<EstadoLinea> lEstado;
	private AdministradorDeLineasDeTransporte admin = new AdministradorDeLineasDeTransporte();
	private JButton boton;
	private JLabel labelErrores;


	public ModificarLineaDeTransporte(LineaDeTransporte l) {

		construirInterfaz(l); //Agregar componentes a la interfaz
		
		boton.addActionListener(g-> {
				labelErrores.setForeground(Color.RED);
				if(linNombre.getText().length() == 0)  {
					labelErrores.setText("Has dejado algún campo sin completar, revisalo.");
				} else {
					EstadoLinea estado = lEstado.getItemAt(lEstado.getSelectedIndex());
					ColorLineaDeTransporte color = linColor.getItemAt(linColor.getSelectedIndex());
					LineaDeTransporte nueva = new LineaDeTransporte(null, linNombre.getText().toString(), color, estado);				
					try {	
						admin.modifyLineaDeTransporte(l, nueva);
						JOptionPane.showMessageDialog(this,"Se modificó la linea correctamente.","Info",JOptionPane.INFORMATION_MESSAGE);
						cambiarJFrame();
					} catch (ClassNotFoundException | SQLException e1) {
						labelErrores.setText("Ha ocurrido un error al modificar la estacion, intente mas tarde");
					}	
		}
		});
	}
	
	private void cambiarJFrame() {
		 
		JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.getContentPane().add(new BMELineaDeTransporte(), BorderLayout.CENTER); 
		 SwingUtilities.updateComponentTreeUI(ventana);
		
		
	}
	
	
	private void construirInterfaz(LineaDeTransporte l) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{33, 136, 62, 189, 36, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR LINEA DE TRANSPORTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		linNombre = new JTextField();
		GridBagConstraints gbc_estNombre = new GridBagConstraints();
		gbc_estNombre.anchor = GridBagConstraints.NORTH;
		gbc_estNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_estNombre.insets = new Insets(0, 0, 5, 5);
		gbc_estNombre.gridx = 3;
		gbc_estNombre.gridy = 2;
		add(linNombre, gbc_estNombre);
		linNombre.setColumns(10);
		linNombre.setText(l.getNombre());
		
		JLabel lblNewLabel_1_1 = new JLabel("Color: ");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		linColor = new JComboBox<ColorLineaDeTransporte>();
		GridBagConstraints gbc_linColor = new GridBagConstraints();
		gbc_linColor.insets = new Insets(0, 0, 5, 5);
		gbc_linColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_linColor.gridx = 3;
		gbc_linColor.gridy = 3;
		linColor.addItem(ColorLineaDeTransporte.AMARILLO);
		linColor.addItem(ColorLineaDeTransporte.AZUL);
		linColor.addItem(ColorLineaDeTransporte.BLANCO);
		linColor.addItem(ColorLineaDeTransporte.CYAN);
		linColor.addItem(ColorLineaDeTransporte.GRIS);
		linColor.addItem(ColorLineaDeTransporte.GRIS_CLARO);
		linColor.addItem(ColorLineaDeTransporte.GRIS_CLARO);
		linColor.addItem(ColorLineaDeTransporte.MAGENTA);
		linColor.addItem(ColorLineaDeTransporte.NARANJA);
		linColor.addItem(ColorLineaDeTransporte.NEGRO);
		linColor.addItem(ColorLineaDeTransporte.ROJO);
		linColor.addItem(ColorLineaDeTransporte.ROSADO);
		linColor.addItem(ColorLineaDeTransporte.VERDE);
		add(linColor, gbc_linColor);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Estado:  ");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 4;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		lEstado = new JComboBox<EstadoLinea>();
		GridBagConstraints gbc_estEstado = new GridBagConstraints();
		gbc_estEstado.fill = GridBagConstraints.BOTH;
		gbc_estEstado.insets = new Insets(0, 0, 5, 5);
		gbc_estEstado.gridx = 3;
		gbc_estEstado.gridy = 4;
		add(lEstado, gbc_estEstado);
		lEstado.addItem(EstadoLinea.ACTIVO);
		lEstado.addItem(EstadoLinea.INACTIVO);
		lEstado.setSelectedItem(l.getEstado());
		
		labelErrores = new JLabel("");
		labelErrores.setForeground(Color.RED);
		labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_labelErrores = new GridBagConstraints();
		gbc_labelErrores.gridwidth = 3;
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 1;
		gbc_labelErrores.gridy = 6;
		add(labelErrores, gbc_labelErrores);
		
		boton = new JButton("Modificar linea de transporte");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		add(boton, gbc_btnNewButton);
	}
}
