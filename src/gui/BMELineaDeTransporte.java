package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class BMELineaDeTransporte extends JPanel {
    
	private String selectedData = null;
	public BMELineaDeTransporte() {


		setLayout(null);

		
		//Columnas de la tabla
		String[] columnas = {"Nombre ",
                "Color",
                "Estado",};
		//Informacion de la tabla (Reemplazar luego con llamar al metodo obtenerDatos())
		Object[][] data = {{"LINEA 1", "Verde", "Activa"},
				{"LINEA 2", "Roja", "No Activa"} 
		};
		
		//Crear modelo de la tabla
		DefaultTableModel modelo = new DefaultTableModel(data,columnas){
		    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		//Crear tabla
		JTable table = new JTable (modelo);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //Solo permitir seleccionar una fila
		table.setFillsViewportHeight(true);
		table.setRowSelectionInterval(0, 0); //Seleccionar la primera fila automaticamente

		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(table, BorderLayout.CENTER);
		//Crear el panel que contiene los botones modificar y eliminar
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		//Agregar los botones
		JButton eliminar = new JButton("Eliminar");
		panel.add(eliminar);
		JButton modificar = new JButton("Modificar");
		panel.add(modificar);
		
		JButton registrarMantenimiento = new JButton("Registrar trayecto");
		panel.add(registrarMantenimiento);
        
			    //Accion si se elimina una fila
			    eliminar.addActionListener(f->{
				    int selectedRow = table.getSelectedRow();
			        selectedData = (String) table.getValueAt(selectedRow, 0);
			        System.out.println("Se eliminara fila " + selectedData);
			    });
			    //Accion si se modifica una fila
			   modificar.addActionListener(f->{
				    int selectedRow = table.getSelectedRow();
			        selectedData = (String) table.getValueAt(selectedRow, 0);
			        System.out.println("Se modificara fila " + selectedData);


		
	});
	}


private Object[][] obtenerDatos(){
	return null; //Llamar al adminsitrador de lineas de transporte	
}
}