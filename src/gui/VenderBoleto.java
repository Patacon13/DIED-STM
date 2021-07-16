package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;

public class VenderBoleto extends JPanel {
	private JTextField clienteEmail;
	private JTextField clienteNombre;

	/**
	 * Create the panel.
	 */
	public VenderBoleto() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{19, 137, 140, 0, 0, 96, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("VENDER BOLETO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email Cliente:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		clienteEmail = new JTextField();
		GridBagConstraints gbc_clienteEmail = new GridBagConstraints();
		gbc_clienteEmail.gridwidth = 2;
		gbc_clienteEmail.anchor = GridBagConstraints.NORTH;
		gbc_clienteEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteEmail.insets = new Insets(0, 0, 5, 5);
		gbc_clienteEmail.gridx = 2;
		gbc_clienteEmail.gridy = 2;
		add(clienteEmail, gbc_clienteEmail);
		clienteEmail.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre Cliente:");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		clienteNombre = new JTextField();
		clienteNombre.setColumns(10);
		GridBagConstraints gbc_clienteNombre = new GridBagConstraints();
		gbc_clienteNombre.gridwidth = 2;
		gbc_clienteNombre.anchor = GridBagConstraints.NORTH;
		gbc_clienteNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteNombre.insets = new Insets(0, 0, 5, 5);
		gbc_clienteNombre.gridx = 2;
		gbc_clienteNombre.gridy = 3;
		add(clienteNombre, gbc_clienteNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Estacion Origen:");
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 1;
		gbc_lblNewLabel_1_1_1.gridy = 4;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JComboBox<String> estOrigen = new JComboBox<String>();
		GridBagConstraints gbc_estOrigen = new GridBagConstraints();
		gbc_estOrigen.gridwidth = 2;
		gbc_estOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_estOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_estOrigen.gridx = 2;
		gbc_estOrigen.gridy = 4;
		add(estOrigen, gbc_estOrigen);
		/*
		 Agregar las estaciones que se obtengan de la bd
		estDestino.addItem("Activa");
		estDestino.addItem("En mantenimiento");
		*/
		
		JLabel lblNewLabel_1_2 = new JLabel("Estacion Destino:");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 5;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JComboBox<String> estDestino = new JComboBox<String>();
		GridBagConstraints gbc_estDestino = new GridBagConstraints();
		gbc_estDestino.gridwidth = 2;
		gbc_estDestino.fill = GridBagConstraints.BOTH;
		gbc_estDestino.insets = new Insets(0, 0, 5, 5);
		gbc_estDestino.gridx = 2;
		gbc_estDestino.gridy = 5;
		add(estDestino, gbc_estDestino);
		/*
		 Agregar las estaciones que se obtengan de la bd
		estDestino.addItem("Activa");
		estDestino.addItem("En mantenimiento");
		*/
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Camino mas rapido");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 6;
		add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Camino de menor distancia");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 6;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Camino mas barato");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 3;
		gbc_rdbtnNewRadioButton_2.gridy = 6;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

		JButton btnNewButton = new JButton("Continuar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnNewRadioButton);
		grupo.add(rdbtnNewRadioButton_1);
		grupo.add(rdbtnNewRadioButton_2);
		
		
		
		btnNewButton.addActionListener(e->{
		String nombre = clienteNombre.getText().toString();
		String email = clienteEmail.getText().toString();
			 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
			 ventana.getContentPane().removeAll(); //Remover componentes
			 ventana.add(new VenderBoleto2(nombre, email), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
			 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
		});

	}
}
