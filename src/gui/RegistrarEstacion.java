package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.awt.Color;

public class RegistrarEstacion extends JPanel {
	private JTextField estNombre;
	private JTextField estApertura;
	private JTextField estCierre;
	private AdministradorDeEstaciones admin;

	/**
	 * Create the panel.
	 */
	public RegistrarEstacion() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		admin = new AdministradorDeEstaciones();
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
		
		JComboBox<EstadoEstacion> estEstado = new JComboBox<EstadoEstacion>();
		GridBagConstraints gbc_estEstado = new GridBagConstraints();
		gbc_estEstado.fill = GridBagConstraints.BOTH;
		gbc_estEstado.insets = new Insets(0, 0, 5, 0);
		gbc_estEstado.gridx = 3;
		gbc_estEstado.gridy = 5;
		add(estEstado, gbc_estEstado);
		estEstado.addItem(EstadoEstacion.OPERATIVA);
		estEstado.addItem(EstadoEstacion.EN_MANTENIMIENTO);
		
		JLabel labelErrores = new JLabel("");
		labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelErrores.setForeground(Color.RED);
		GridBagConstraints gbc_labelErrores = new GridBagConstraints();
		gbc_labelErrores.gridwidth = 3;
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 1;
		gbc_labelErrores.gridy = 6;
		add(labelErrores, gbc_labelErrores);
		
		JButton boton = new JButton("Agregar estacion");
		GridBagConstraints gbc_boton = new GridBagConstraints();
		gbc_boton.anchor = GridBagConstraints.NORTH;
		gbc_boton.fill = GridBagConstraints.HORIZONTAL;
		gbc_boton.gridwidth = 3;
		gbc_boton.gridx = 1;
		gbc_boton.gridy = 7;
		add(boton, gbc_boton);
		
		boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelErrores.setForeground(Color.RED);
				if(estNombre.getText().length() == 0 || estApertura.getText().length() == 0 || estCierre.getText().length() == 0)  {
					labelErrores.setText("Has dejado algún campo sin completar, revisalo.");
				} else {
					LocalTime apertura = null;
					LocalTime cierre = null;
					try {
						apertura = LocalTime.parse(estApertura.getText().toString());
						cierre = LocalTime.parse(estCierre.getText().toString());
					}catch(DateTimeParseException f) {
						labelErrores.setText("La fecha ingresada es incorrecta, verifica que el formato sea hh:mm");
					}
					EstadoEstacion estado = estEstado.getItemAt(estEstado.getSelectedIndex());
					Estacion nueva = new Estacion(null, estNombre.getText().toString(), apertura, cierre, estado);
					try {
						admin.addEstacion(nueva);
						labelErrores.setForeground(Color.GREEN);
						labelErrores.setText("Se registro la estacion correctamente");
						estNombre.setText("");
						estApertura.setText("");
						estCierre.setText("");
					} catch (ClassNotFoundException | SQLException e1) {
						labelErrores.setText(e1.getMessage());
					}
				}
			}
		});
	}
}
