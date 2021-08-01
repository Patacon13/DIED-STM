package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class BMEEstacionPadre extends JPanel {
	private JTextField buscarField;
	private String selectedData = null;
	private JButton eliminar;
	private JButton modificar;
	private JButton fmaximo;
	private JTable table;
	private JButton buscar;
	private AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
	private JLabel labelErrores;
	private DefaultTableModel modelo = null;
	private JPanel panel_1 = new JPanel();
	private JPanel panel;
	private JButton verMantenimientos;
	
	public BMEEstacionPadre() {
		
		construirInterfaz();
		
		//Construir tabla
		try {
			modelo = construirTabla("");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Ocurrio un error al obtener los datos.","Error",JOptionPane.ERROR_MESSAGE);
		}
		table = new JTable (modelo);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
		table.setFillsViewportHeight(true);
		if(table.getRowCount() > 0) { //Si hay al menos una fila entonces puede seleccionar alguna y mostrar las opciones
			panel_1.setVisible(true);
			table.setRowSelectionInterval(0, 0); //Seleccionar la primera fila automaticamente
		}
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
		JScrollPane js=new JScrollPane(table);
		js.setVisible(true);
		panel.add(js);
		
		
	    //Accion si se elimina una fila
	    eliminar.addActionListener(f->{
			labelErrores.setText("");
		    int selectedRow = table.getSelectedRow();
		    Integer idsec;
	        idsec = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
	        Object[] opciones = {"De acuerdo!", "No"};
	        Object defaultOp = opciones[0];
	        int elegido = JOptionPane.showOptionDialog(this, "Se eliminará " + table.getValueAt(selectedRow, 1).toString() + " ¿Está seguro?", "Eliminar estacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, defaultOp);
	        if(elegido == JOptionPane.YES_OPTION) {
	        	try {
					admin.deleteEstacion(idsec);
					modelo.removeRow(selectedRow); //Mostrar en la interfaz que se elimina
					if(table.getRowCount() < 1) { //Si hay al menos una fila entonces puede seleccionar alguna y mostrar las opciones
						panel_1.setVisible(false);
					}
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla.","Error",JOptionPane.ERROR_MESSAGE);
				} 
	        }
	    });
	    
	    //Accion al modificar una fila
	    modificar.addActionListener(f->{
		    int selectedRow = table.getSelectedRow();
	        selectedData = (String) table.getValueAt(selectedRow, 1);
	        Estacion est = new Estacion((Integer) table.getValueAt(selectedRow, 0), (String) table.getValueAt(selectedRow, 1), LocalTime.parse(table.getValueAt(selectedRow, 2).toString()), LocalTime.parse(table.getValueAt(selectedRow, 3).toString()), EstadoEstacion.valueOf(table.getValueAt(selectedRow, 4).toString()));
	   	 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.add(new ModificarEstacion(est), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
		 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
	    });
	    
	    fmaximo.addActionListener(f->{
		    int selectedRow = table.getSelectedRow();
	        selectedData = (String) table.getValueAt(selectedRow, 1);
	        Estacion est = new Estacion((Integer) table.getValueAt(selectedRow, 0), (String) table.getValueAt(selectedRow, 1), LocalTime.parse(table.getValueAt(selectedRow, 2).toString()), LocalTime.parse(table.getValueAt(selectedRow, 3).toString()), EstadoEstacion.valueOf(table.getValueAt(selectedRow, 4).toString()));
	   	 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.add(new FlujoMaximo(est), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
		 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
	    });
	    
	    //Accion al buscar
	    buscar.addActionListener(f->{
	    try {
			modelo = this.construirTabla(buscarField.getText().toString());
			table.setModel(modelo);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
			table.setFillsViewportHeight(true);
			if(table.getRowCount() > 0) { //Si hay al menos una fila entonces puede seleccionar alguna y mostrar las opciones
				panel_1.setVisible(true);
				table.setRowSelectionInterval(0, 0); //Seleccionar la primera fila automaticamente
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error al realizar la busqueda","Error",JOptionPane.ERROR_MESSAGE);
		}	
	    });
	    
	    verMantenimientos.addActionListener(f->{
		    int selectedRow = table.getSelectedRow();
	        //selectedData = (String) table.getValueAt(selectedRow, 0);
	        Estacion est = new Estacion((Integer) table.getValueAt(selectedRow, 0), (String) table.getValueAt(selectedRow, 1), LocalTime.parse(table.getValueAt(selectedRow, 2).toString()), LocalTime.parse(table.getValueAt(selectedRow, 3).toString()), EstadoEstacion.valueOf(table.getValueAt(selectedRow, 4).toString()));
	   	 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.add(new VerMantenimiento(est), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
		 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
	    });
	    
	   }
	

	public void construirInterfaz() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 360, 40, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 248, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		buscarField = new JTextField();
		GridBagConstraints gbc_buscarField = new GridBagConstraints();
		gbc_buscarField.gridwidth = 2;
		gbc_buscarField.insets = new Insets(0, 0, 5, 5);
		gbc_buscarField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscarField.gridx = 2;
		gbc_buscarField.gridy = 0;
		add(buscarField, gbc_buscarField);
		buscarField.setColumns(10);
		
		panel = new JPanel();
		labelErrores = new JLabel("");
		labelErrores.setForeground(Color.RED);
		labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_labelErrores = new GridBagConstraints();
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 2;
		gbc_labelErrores.gridy = 3;
		add(labelErrores, gbc_labelErrores);


		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		panel_1.setVisible(false);
		add(panel_1, gbc_panel_1);
		
		
		buscar = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		add(buscar, gbc_btnNewButton);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		
		
		modificar = new JButton("Modificar");
		panel_1.add(modificar);
		
		eliminar = new JButton("Eliminar");
		panel_1.add(eliminar);
		
		fmaximo = new JButton("Flujo maximo");
		panel_1.add(fmaximo);
		
		verMantenimientos = new JButton("Ver mantenimientos");
		panel_1.add(verMantenimientos);
	}


	
	private  DefaultTableModel construirTabla(String sql) throws ClassNotFoundException, SQLException{
		
		//Setear columnas
		String[] columnas = {"ID", "Nombre ", "Horario Apertura", "Horario Cierre", "Estado"};
		
		//Construir modelo de la tabla vacia
		DefaultTableModel modelo = new DefaultTableModel(null,columnas){
		    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
			//Agregar informacion a la tabla
			ArrayList<Estacion> estaciones = admin.getEstaciones(sql);
			for(int i=0; i<estaciones.size(); i++) {
				   int ID = estaciones.get(i).getId();
				   String nombre = estaciones.get(i).getNombre();
				   LocalTime hApertura = estaciones.get(i).getHorarioApertura();
				   LocalTime hCierre = estaciones.get(i).getHorarioCierre();
				   EstadoEstacion est = estaciones.get(i).getEstado();
				   Object[] estacion = {ID, nombre, hApertura, hCierre, est};
				   modelo.addRow(estacion);
			}
			return modelo;
		
	}
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
