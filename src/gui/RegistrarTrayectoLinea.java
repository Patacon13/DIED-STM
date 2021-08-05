package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import lineaDeTransporte.LineaDeTransporte;
import ruta.AdministradorDeRutas;
import ruta.Ruta;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class RegistrarTrayectoLinea extends JPanel {
	private AdministradorDeRutas admin = new AdministradorDeRutas();
	/**
	 * Create the panel.
	 */
	public RegistrarTrayectoLinea(LineaDeTransporte linea) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 171, 0, 171, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{28, 29, 0, 114, 0, 19, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRAR TRAYECTO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton registrarRuta = new JButton("Agregar ruta");
		registrarRuta.addActionListener(e -> {
				 
				JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
				 ventana.getContentPane().removeAll(); //Remover componentes
				 ventana.getContentPane().add(new RegistrarRuta(linea), BorderLayout.CENTER); 
				 SwingUtilities.updateComponentTreeUI(ventana);
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		add(registrarRuta, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Rutas agregadas al trayecto de " + linea.getNombre() + " " + linea.getColor().toString());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		

				DefaultTableModel modelo = null;
				try {
					modelo = construirTabla(linea);
				} catch (ClassNotFoundException | SQLException e1) {
					 JOptionPane.showMessageDialog(this, "Ocurrio un error al obtener los datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				JTable table = new JTable (modelo);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
				table.setFillsViewportHeight(true);
				panel.setLayout(new BorderLayout());
				panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
				panel.add(table, BorderLayout.CENTER);
				JScrollPane js=new JScrollPane(table);
				js.setVisible(true);
				panel.add(js);
	}
	
	DefaultTableModel construirTabla(LineaDeTransporte linea) throws ClassNotFoundException, SQLException {
		//Setear columnas
		String[] columnas = {"ID", "Origen", "Destino", "Kilometros", "Duracion", "Pasajeros", "Estado", "Precio"};
		
		//Construir modelo de la tabla vacia
		DefaultTableModel modelo = new DefaultTableModel(null,columnas){
		    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		ArrayList<Ruta> rutas = admin.getRutas(linea);
		for(int i=0; i<rutas.size(); i++) {
			   int ID = rutas.get(i).getId();
			   String origen  = rutas.get(i).getOrigen().getNombre();
			   String destino = rutas.get(i).getDestino().getNombre();
			   Integer duracion = rutas.get(i).getDuracion();
			   Integer pasajeros = rutas.get(i).getCantMax();
			   String estado = rutas.get(i).getEstado().toString();
			   Double costo = rutas.get(i).getCosto();
			   Double kilometros = rutas.get(i).getKilometros();
			   Object[] ruta = {ID, origen, destino, kilometros, duracion, pasajeros, estado, costo};
			   modelo.addRow(ruta);
			   }		
		return modelo;
		
	}
	
	

}
