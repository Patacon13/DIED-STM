package gui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Ellipse2D;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;


public class VenderBoleto2 extends JPanel {
	private String nombreCliente;
	private String emailCliente;
	/**
	 * Create the panel.
	 */
	public VenderBoleto2(String nombre, String email) {
		this.nombreCliente = nombre;
		this.emailCliente = email;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{19, 137, 0, 140, 0, 0, 96, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 0, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("VENDER BOLETO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email Cliente:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		/*
		 Agregar las estaciones que se obtengan de la bd
		estDestino.addItem("Activa");
		estDestino.addItem("En mantenimiento");
		*/
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre Cliente:");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.gridwidth = 2;
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 2;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Estacion Origen:");
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.gridwidth = 2;
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1.gridy = 3;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		/*
		 Agregar las estaciones que se obtengan de la bd
		estDestino.addItem("Activa");
		estDestino.addItem("En mantenimiento");
		*/
		
		JLabel lblNewLabel_1_2 = new JLabel("Estacion Destino:");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.gridwidth = 2;
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 2;
		gbc_lblNewLabel_1_2.gridy = 4;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nro Boleto: ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 6;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.gridwidth = 3;
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 8;
		add(btnVolver, gbc_btnVolver);
		JButton btnNewButton = new JButton("Confirmar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 8;
		add(btnNewButton, gbc_btnNewButton);
		btnVolver.addActionListener(e->{
				 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
				 ventana.getContentPane().removeAll(); //Remover componentes
				 ventana.add(new VenderBoleto(), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
				 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
			});
		
		System.out.println(nombre + " " + email);
	}
	protected void paintComponent(Graphics g) {
		//Prueba de dibujar algo en la interfaz
		super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g.create();
		 Ellipse2D.Double c =new Ellipse2D.Double(10,10,30,30);
		 g2.fill(c);
	 }

}
