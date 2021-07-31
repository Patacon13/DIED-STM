package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import estacion.Estacion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Ellipse2D;
import java.util.Enumeration;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;


public class VenderBoleto2 extends JPanel {


	public VenderBoleto2(Estacion origen, Estacion destino, String nombre, String email) {

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 145, 0, 0, 0, 140, 0, 0, 96, 0, 0};
		gridBagLayout.rowHeights = new int[]{19, 25, 25, 25, 0, 25, 0, 0, 0, 0, 0, 0, 25, 17, 24, 23, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("VENDER BOLETO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 9;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel mapa = new JPanel();
		GridBagConstraints gbc_mapa = new GridBagConstraints();
		gbc_mapa.gridheight = 6;
		gbc_mapa.gridwidth = 3;
		gbc_mapa.insets = new Insets(0, 0, 5, 5);
		gbc_mapa.fill = GridBagConstraints.BOTH;
		gbc_mapa.gridx = 4;
		gbc_mapa.gridy = 1;
		add(mapa, gbc_mapa);
		
		ButtonGroup grupo = new ButtonGroup();
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 7;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		
		add(separator, gbc_separator);

		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Camino mas rapido");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 4;
		gbc_rdbtnNewRadioButton.gridy = 8;
		add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		grupo.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Camino de menor distancia");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 5;
		gbc_rdbtnNewRadioButton_1.gridy = 8;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		grupo.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Camino mas barato");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 6;
		gbc_rdbtnNewRadioButton_2.gridy = 8;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		grupo.add(rdbtnNewRadioButton_2);
		
		JButton elegirCamino = new JButton("Elegir camino");

		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_elegirCamino = new GridBagConstraints();
		gbc_elegirCamino.insets = new Insets(0, 0, 5, 5);
		gbc_elegirCamino.gridx = 5;
		gbc_elegirCamino.gridy = 9;
		add(elegirCamino, gbc_elegirCamino);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 10;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		add(separator_1, gbc_separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Email Cliente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 11;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel emailValue = new JLabel("");
		GridBagConstraints gbc_emailValue = new GridBagConstraints();
		gbc_emailValue.insets = new Insets(0, 0, 5, 5);
		gbc_emailValue.gridx = 5;
		gbc_emailValue.gridy = 11;
		add(emailValue, gbc_emailValue);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre Cliente:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 4;
		gbc_lblNewLabel_1_1.gridy = 12;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel clienteValue = new JLabel("");
		GridBagConstraints gbc_clienteValue = new GridBagConstraints();
		gbc_clienteValue.insets = new Insets(0, 0, 5, 5);
		gbc_clienteValue.gridx = 5;
		gbc_clienteValue.gridy = 12;
		add(clienteValue, gbc_clienteValue);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Estacion Origen:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 4;
		gbc_lblNewLabel_1_1_1.gridy = 13;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JLabel origenValue = new JLabel("");
		GridBagConstraints gbc_origenValue = new GridBagConstraints();
		gbc_origenValue.insets = new Insets(0, 0, 5, 5);
		gbc_origenValue.gridx = 5;
		gbc_origenValue.gridy = 13;
		add(origenValue, gbc_origenValue);
		
		JLabel lblNewLabel_1_2 = new JLabel("Estacion Destino:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 4;
		gbc_lblNewLabel_1_2.gridy = 14;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel destinoValue = new JLabel("");
		GridBagConstraints gbc_destinoValue = new GridBagConstraints();
		gbc_destinoValue.insets = new Insets(0, 0, 5, 5);
		gbc_destinoValue.gridx = 5;
		gbc_destinoValue.gridy = 14;
		add(destinoValue, gbc_destinoValue);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 15;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel precioValue = new JLabel("");
		GridBagConstraints gbc_precioValue = new GridBagConstraints();
		gbc_precioValue.insets = new Insets(0, 0, 5, 5);
		gbc_precioValue.gridx = 5;
		gbc_precioValue.gridy = 15;
		add(precioValue, gbc_precioValue);
		
		JLabel lblNewLabel_3 = new JLabel("Nro Boleto: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 16;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel boletoValue = new JLabel("");
		GridBagConstraints gbc_boletoValue = new GridBagConstraints();
		gbc_boletoValue.insets = new Insets(0, 0, 5, 5);
		gbc_boletoValue.gridx = 5;
		gbc_boletoValue.gridy = 16;
		add(boletoValue, gbc_boletoValue);
		
		JButton btnVolver = new JButton("Volver atras");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 4;
		gbc_btnVolver.gridy = 17;
		add(btnVolver, gbc_btnVolver);
		JButton confirmarBoleto = new JButton("Confirmar");
		GridBagConstraints gbc_confirmarBoleto = new GridBagConstraints();
		gbc_confirmarBoleto.insets = new Insets(0, 0, 5, 5);
		gbc_confirmarBoleto.anchor = GridBagConstraints.NORTH;
		gbc_confirmarBoleto.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmarBoleto.gridx = 6;
		gbc_confirmarBoleto.gridy = 17;
		add(confirmarBoleto, gbc_confirmarBoleto);
		emailValue.setText(email);
		clienteValue.setText(nombre);
		origenValue.setText(origen.toString());
		destinoValue.setText(destino.toString());
		
		btnVolver.addActionListener(e->{
				 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
				 ventana.getContentPane().removeAll(); //Remover componentes
				 ventana.add(new VenderBoleto(), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
				 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
			});
		
		elegirCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Llamar a funcion que devuelva la lista de rutas y estaciones
			//Hay que setear el precio en base a la lista de rutas
			}
		});
		
		
		
		}
	
		
	
	
	
	
	
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
