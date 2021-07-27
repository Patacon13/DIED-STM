package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.EstadoEstacion;
import tareaDeMantenimiento.AdministradorDeTareas;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.BorderLayout;
import java.awt.Color;

public class ModificarEstacion extends JPanel {
	private JTextField estNombre;
	private JTextField estApertura;
	private JTextField estCierre;
	private AdministradorDeEstaciones admin;
	private Boolean mantExitoso = false;
	private JButton boton;
	private JLabel labelErrores;


	public ModificarEstacion(Estacion est) {

		construirInterfaz(est); //Agregar componentes a la interfaz
		
		JComboBox<EstadoEstacion> estEstado = new JComboBox<EstadoEstacion>();
		GridBagConstraints gbc_estEstado = new GridBagConstraints();
		gbc_estEstado.fill = GridBagConstraints.BOTH;
		gbc_estEstado.insets = new Insets(0, 0, 5, 0);
		gbc_estEstado.gridx = 3;
		gbc_estEstado.gridy = 5;
		add(estEstado, gbc_estEstado);
		estEstado.addItem(EstadoEstacion.OPERATIVA);
		estEstado.addItem(EstadoEstacion.EN_MANTENIMIENTO);
		estEstado.setSelectedItem(est.getEstado());
		
		boton.addActionListener(g-> {
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
					Estacion nueva = new Estacion(est.getId(), estNombre.getText().toString(), apertura, cierre, estado);
					
					if(est.getEstado() != nueva.getEstado() && nueva.getEstado() == EstadoEstacion.EN_MANTENIMIENTO) {
						RegistrarMantenimiento(nueva);
						
					} else if(est.getEstado() != nueva.getEstado() && nueva.getEstado() == EstadoEstacion.OPERATIVA) {
						RegistrarFinMantenimiento(nueva);
						
					}

					
				if(est.getEstado() == nueva.getEstado() || (est.getEstado() != nueva.getEstado() && mantExitoso)) {
					try {
						admin.modifyEstacion(est, nueva);
						cambiarJFrame();
					} catch (ClassNotFoundException | SQLException e1) {
						labelErrores.setText("Ha ocurrido un error al modificar la estacion, intente mas tarde");
					}

				}
				}
				
		});
	}
	
	private void cambiarJFrame() {
		 
		JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.add(new BMEEstacionPadre(), BorderLayout.CENTER); 
		 SwingUtilities.updateComponentTreeUI(ventana);
		
		
	}
	
	
	private void RegistrarMantenimiento(Estacion nueva) {

				
		 JPanel mantenimiento=new JPanel();


		 mantenimiento.setLayout(new GridLayout(4,1));

		 //Create a label with text (Username)
		 JLabel l1=new JLabel("Fecha inicio (dd-mm-aaaa): ");
		 JLabel l3=new JLabel("Descripcion: ");

		 JTextField finicio =new JTextField(12);
		 JTextField des = new JTextField(12);

		mantenimiento.add(l1);
		 mantenimiento.add(finicio);
		 mantenimiento.add(l3);
		 mantenimiento.add(des);
		 JFrame frame=new JFrame();
		 int a=JOptionPane.showConfirmDialog(frame,mantenimiento,"Registrar mantenimiento",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		 if(a==JOptionPane.OK_OPTION)
		 {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 LocalDate inicio = null;
			 try {
			  inicio = LocalDate.parse(finicio.getText().toString(), formatter);
			 } catch(DateTimeParseException e) {
				 JOptionPane.showMessageDialog(frame,"Alguna fecha ingresada no es valida.","False",JOptionPane.ERROR_MESSAGE);
			 }
			 String descripcion = des.getText().toString();
			 AdministradorDeTareas adminMan= new AdministradorDeTareas();
			 try {
				adminMan.createTareaDeMantenimiento(inicio, null, descripcion, nueva);
				mantExitoso = true;
				JOptionPane.showMessageDialog(frame,"Se registró correctamente el mantenimiento.","Info",JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(frame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		 }
	}
	
	private void RegistrarFinMantenimiento(Estacion nueva) {
		
		 
		 JPanel mantenimiento=new JPanel();
		 mantenimiento.setLayout(new GridLayout(4,1));
		 JLabel l1=new JLabel("Fecha fin (dd-mm-aaaa): ");
		 JTextField ffin=new JTextField(12);


		mantenimiento.add(l1);
		mantenimiento.add(ffin);
		
	    JFrame frame=new JFrame();
	    int a=JOptionPane.showConfirmDialog(frame,mantenimiento,"Registrar fin mantenimiento",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

		 if(a==JOptionPane.OK_OPTION)
		 {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 LocalDate fin = null;
			 try {
			  fin = LocalDate.parse(ffin.getText().toString(), formatter);
			 } catch(DateTimeParseException e) {
				 JOptionPane.showMessageDialog(frame,"La fecha ingresada no es valida.","False",JOptionPane.ERROR_MESSAGE);
			 }
			 try {
					AdministradorDeTareas adminMan = new AdministradorDeTareas();
					adminMan.finalizarMantenimiento(nueva, fin);
					mantExitoso = true;
					JOptionPane.showMessageDialog(frame,"Se registró correctamente el fin del mantenimiento.","Info",JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}

		 }		
	}
	
	private void construirInterfaz(Estacion est) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		admin = new AdministradorDeEstaciones();
		gridBagLayout.columnWidths = new int[]{33, 136, 62, 189, 0};
		gridBagLayout.rowHeights = new int[]{19, 35, 20, 20, 20, 22, 35, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("MODIFICAR ESTACION");
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
		estNombre.setText(est.getNombre());
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
		estApertura.setText(est.getHorarioApertura().toString());
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
		estCierre.setText(est.getHorarioCierre().toString());
		add(estCierre, gbc_estCierre);
		
		JLabel lblNewLabel_1_2 = new JLabel("Estado:  ");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 5;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		
		labelErrores = new JLabel("");
		labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelErrores.setForeground(Color.RED);
		GridBagConstraints gbc_labelErrores = new GridBagConstraints();
		gbc_labelErrores.gridwidth = 3;
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 1;
		gbc_labelErrores.gridy = 6;
		add(labelErrores, gbc_labelErrores);
		
		 boton = new JButton("Modificar estacion");
		GridBagConstraints gbc_boton = new GridBagConstraints();
		gbc_boton.anchor = GridBagConstraints.NORTH;
		gbc_boton.fill = GridBagConstraints.HORIZONTAL;
		gbc_boton.gridwidth = 3;
		gbc_boton.gridx = 1;
		gbc_boton.gridy = 7;
		add(boton, gbc_boton);
	}
	
}
