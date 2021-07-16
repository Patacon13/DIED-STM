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

public class RegistrarEstacion extends JPanel {
	private JTextField estNombre;
	private JTextField estApertura;
	private JTextField estCierre;

	/**
	 * Create the panel.
	 */
	public RegistrarEstacion() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{33, 136, 62, 189, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR ESTACION");
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
		
		estNombre = new JTextField();
		GridBagConstraints gbc_estNombre = new GridBagConstraints();
		gbc_estNombre.anchor = GridBagConstraints.NORTH;
		gbc_estNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_estNombre.insets = new Insets(0, 0, 5, 0);
		gbc_estNombre.gridx = 3;
		gbc_estNombre.gridy = 2;
		add(estNombre, gbc_estNombre);
		estNombre.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Horario apertura: ");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		estApertura = new JTextField();
		estApertura.setColumns(10);
		GridBagConstraints gbc_estApertura = new GridBagConstraints();
		gbc_estApertura.anchor = GridBagConstraints.NORTH;
		gbc_estApertura.fill = GridBagConstraints.HORIZONTAL;
		gbc_estApertura.insets = new Insets(0, 0, 5, 0);
		gbc_estApertura.gridx = 3;
		gbc_estApertura.gridy = 3;
		add(estApertura, gbc_estApertura);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Horario cierre:  ");
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 1;
		gbc_lblNewLabel_1_1_1.gridy = 4;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		estCierre = new JTextField();
		estCierre.setColumns(10);
		GridBagConstraints gbc_estCierre = new GridBagConstraints();
		gbc_estCierre.anchor = GridBagConstraints.NORTH;
		gbc_estCierre.fill = GridBagConstraints.HORIZONTAL;
		gbc_estCierre.insets = new Insets(0, 0, 5, 0);
		gbc_estCierre.gridx = 3;
		gbc_estCierre.gridy = 4;
		add(estCierre, gbc_estCierre);
		
		JLabel lblNewLabel_1_2 = new JLabel("Estado:  ");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 5;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JComboBox<String> estEstado = new JComboBox<String>();
		GridBagConstraints gbc_estEstado = new GridBagConstraints();
		gbc_estEstado.fill = GridBagConstraints.BOTH;
		gbc_estEstado.insets = new Insets(0, 0, 5, 0);
		gbc_estEstado.gridx = 3;
		gbc_estEstado.gridy = 5;
		add(estEstado, gbc_estEstado);
		estEstado.addItem("Activa");
		estEstado.addItem("En mantenimiento");
		
		JButton btnNewButton = new JButton("Agregar estacion");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(e->{
			//Accion cuando se presione el boton
		});

	}
}
