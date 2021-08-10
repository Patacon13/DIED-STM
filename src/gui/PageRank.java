package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class PageRank extends JPanel {
	private JTable table;
	private AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
	private JLabel labelErrores;
	private DefaultTableModel modelo = null;
	private JPanel panel;
	private JLabel lblNewLabel;
	
	public PageRank() {
		
		construirInterfaz();
		

		try {
			modelo = construirTabla();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar los datos.","Error",JOptionPane.ERROR_MESSAGE);
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
	
	public void construirInterfaz() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 360, 40, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 248, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("PageRank");
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



	private  DefaultTableModel construirTabla() throws ClassNotFoundException, SQLException{
		
		//Setear columnas
		String[] columnas = {"RANK", "ID Estacion", "Nombre", "PageRank" };
		
		//Construir modelo de la tabla vacia
		DefaultTableModel modelo = new DefaultTableModel(null,columnas){
		    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		admin.pageRank();
		Map<Integer, Double> estaciones = admin.getPageRank();
		final Map<Integer, Double> ordenado = estaciones.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		
		Iterator<Map.Entry<Integer, Double>> entries = ordenado.entrySet().iterator();
		Integer c= 1;
		while (entries.hasNext()) {
			Entry<Integer, Double> entry = entries.next();
			   int ID = entry.getKey();
			   String nombre = admin.getEstacion(entry.getKey()).getNombre();
			   Double pageRank = entry.getValue();
			   Object[] estacion = {c, ID, nombre, pageRank};
			   modelo.addRow(estacion);
			   c++;
		};	
		return modelo;
	
	}

}
