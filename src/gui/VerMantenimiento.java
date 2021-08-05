package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import estacion.Estacion;
import tareaDeMantenimiento.AdministradorDeTareas;
import tareaDeMantenimiento.TareaDeMantenimiento;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class VerMantenimiento extends JPanel {
	private JTable table;
	private AdministradorDeTareas admin = new AdministradorDeTareas();
	private JLabel labelErrores;
	private DefaultTableModel modelo = null;
	private JPanel panel;
	private JLabel lblNewLabel;
	
	public VerMantenimiento(Estacion est) {
		
		construirInterfaz(est);
		
		//Construir tabla
		try {
			modelo = construirTabla(est);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error al obtener los mantenimientos de la estacion.","Error",JOptionPane.ERROR_MESSAGE);
		}
		table = new JTable (modelo);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
		table.setFillsViewportHeight(true);
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
		JScrollPane js=new JScrollPane(table);
		js.setVisible(true);
		panel.add(js);
	   }
	

	public void construirInterfaz(Estacion est) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 360, 40, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 248, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("Visualizar mantenimientos de " + est.getNombre());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		labelErrores = new JLabel("");
		labelErrores.setForeground(Color.RED);
		labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_labelErrores = new GridBagConstraints();
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 2;
		gbc_labelErrores.gridy = 3;
		add(labelErrores, gbc_labelErrores);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
	}


	
	private  DefaultTableModel construirTabla(Estacion est) throws ClassNotFoundException, SQLException{
		
		//Setear columnas
		String[] columnas = {"ID", "Fecha inicio ",
	            "Fecha fin",
	            "Observaciones"};
		
		//Construir modelo de la tabla vacia
		DefaultTableModel modelo = new DefaultTableModel(null,columnas){
		    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		
		ArrayList<TareaDeMantenimiento> tareas = admin.getMantenimientos(est);
		for(int i=0; i<tareas.size(); i++) {
			   int ID = tareas.get(i).getId();
			   LocalDate fechainicio = tareas.get(i).getFechaInicioTarea();
			   String fechaFin;
			   if(tareas.get(i).getFechaFinTarea() == null) {
				   fechaFin = "";
			   } else {
				   fechaFin = tareas.get(i).getFechaFinTarea().toString();
			   }
			   String observaciones = tareas.get(i).getObservaciones();
			   Object[] mantenimiento = {ID, fechainicio, fechaFin, observaciones};
			   modelo.addRow(mantenimiento);
			   }		
		return modelo;
	
	}
}
