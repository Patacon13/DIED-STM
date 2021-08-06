package gui;

import java.sql.SQLException;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;
import tareaDeMantenimiento.AdministradorDeTareas;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;

public class ProximoMantenimiento extends JPanel {

	JPanel panel;
	JButton boton;
	public ProximoMantenimiento() {
		construirInterfaz();
		
		AdministradorDeEstaciones admin2 = new AdministradorDeEstaciones();
			 Queue<Estacion> estaciones = new PriorityQueue<Estacion>();
			 try {
				estaciones.addAll(admin2.getEstaciones(""));
			    	Estacion aMantener = estaciones.poll();
			    	panel.setLayout(new BorderLayout());
					
					String[] columnas = {"ID", "Nombre", "Apertura", "Cierre", "Estado"};
					
					//Construir modelo de la tabla vacia
					DefaultTableModel modelo = new DefaultTableModel(null,columnas){
					    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
					};
						
						if(aMantener == null || aMantener.getEstado() == EstadoEstacion.EN_MANTENIMIENTO) {
							Object[] estacion = {"-", "-", "-", "-", "-"};
							   modelo.addRow(estacion);
						} else {
							Object[] estacion = {aMantener.getId(), aMantener.getNombre(), aMantener.getHorarioApertura(), aMantener.getHorarioCierre(), aMantener.getEstado().toString()};
							   modelo.addRow(estacion);
						}

				
					JTable table = new JTable (modelo);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
					table.setFillsViewportHeight(true);
					panel.setLayout(new BorderLayout());
					panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
					panel.add(table, BorderLayout.CENTER);
					
					boton.addActionListener(e->{
						if(aMantener != null) {
					   	 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
						 ventana.getContentPane().removeAll(); //Remover componentes
						 ventana.add(new ModificarEstacion(aMantener), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
						 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
						}
					});
					
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(this, "Ocurrio un error al obtener el proximo mantenimiento.","Error",JOptionPane.ERROR_MESSAGE);
			}

		
	}
	
	private void construirInterfaz() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{59, 61, 61, 0, 0, 65, 53, 0};
		gridBagLayout.rowHeights = new int[]{33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Proximo Mantenimiento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		panel = new JPanel();
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 5;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		
		boton = new JButton("Realizar mantenimiento");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 9;
		add(boton, gbc_btnNewButton_1);
	}
	
	
	
}
