package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RegistrarLineaDeTransporte extends JPanel {
	private JTextField linNombre;
	private JTextField linColor;

	/**
	 * Create the panel.
	 */
	public RegistrarLineaDeTransporte() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{33, 136, 62, 189, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR LINEA DE TRANSPORTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
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
		gbc_estNombre.insets = new Insets(0, 0, 5, 0);
		gbc_estNombre.gridx = 3;
		gbc_estNombre.gridy = 2;
		add(linNombre, gbc_estNombre);
		linNombre.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Color: ");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		linColor = new JTextField();
		linColor.setColumns(10);
		GridBagConstraints gbc_estApertura = new GridBagConstraints();
		gbc_estApertura.anchor = GridBagConstraints.NORTH;
		gbc_estApertura.fill = GridBagConstraints.HORIZONTAL;
		gbc_estApertura.insets = new Insets(0, 0, 5, 0);
		gbc_estApertura.gridx = 3;
		gbc_estApertura.gridy = 3;
		add(linColor, gbc_estApertura);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Estado:  ");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 4;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JComboBox<String> linEstado = new JComboBox<String>();
		GridBagConstraints gbc_estEstado = new GridBagConstraints();
		gbc_estEstado.fill = GridBagConstraints.BOTH;
		gbc_estEstado.insets = new Insets(0, 0, 5, 0);
		gbc_estEstado.gridx = 3;
		gbc_estEstado.gridy = 4;
		add(linEstado, gbc_estEstado);
		linEstado.addItem("Activa");
		linEstado.addItem("No Activa");
		
		JButton btnNewButton = new JButton("Agregar Linea de Transporte");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(e->{
				//Accion cuando se presione el boton
		});

	}
}
