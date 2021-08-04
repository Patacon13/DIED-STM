package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import boleto.AdministradorDeBoletos;
import boleto.Boleto;
import estacion.AdministradorDeCaminos;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.Pedido;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import pair.Pair;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JSeparator;
import java.awt.Color;



public class VenderBoleto2 extends JPanel {
	
    AdministradorDeCaminos admin = new AdministradorDeCaminos();
    AdministradorDeEstaciones admin2 = new AdministradorDeEstaciones();
    AdministradorDeLineasDeTransporte admin3 = new AdministradorDeLineasDeTransporte();

	public VenderBoleto2(Estacion origen, Estacion destino, String nombre, String email) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 145, 0, 0, 0, 140, 0, 0, 96, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 25, 0, 0, 0, 0, 0, 25, 0, 0, 17, 24, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		ButtonGroup grupo = new ButtonGroup();
			
			JLabel lblNewLabel_4 = new JLabel("Elegir camino");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 5;
			gbc_lblNewLabel_4.gridy = 0;
			add(lblNewLabel_4, gbc_lblNewLabel_4);
		
			JRadioButton rdbtnNewRadioButton = new JRadioButton("MASBARATO");
			GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
			gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton.gridx = 4;
			gbc_rdbtnNewRadioButton.gridy = 1;
			add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
			rdbtnNewRadioButton.setSelected(true);
			grupo.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("MASRAPIDO");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 5;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		grupo.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("MENORDISTANCIA");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 6;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		grupo.add(rdbtnNewRadioButton_2);
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 10;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		
		add(separator, gbc_separator);
		

		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("Email Cliente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel emailValue = new JLabel("");
		GridBagConstraints gbc_emailValue = new GridBagConstraints();
		gbc_emailValue.insets = new Insets(0, 0, 5, 5);
		gbc_emailValue.gridx = 5;
		gbc_emailValue.gridy = 3;
		add(emailValue, gbc_emailValue);
		emailValue.setText(email);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre Cliente:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 4;
		gbc_lblNewLabel_1_1.gridy = 4;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel clienteValue = new JLabel("");
		GridBagConstraints gbc_clienteValue = new GridBagConstraints();
		gbc_clienteValue.insets = new Insets(0, 0, 5, 5);
		gbc_clienteValue.gridx = 5;
		gbc_clienteValue.gridy = 4;
		add(clienteValue, gbc_clienteValue);
		clienteValue.setText(nombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Estacion Origen:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 4;
		gbc_lblNewLabel_1_1_1.gridy = 5;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JLabel origenValue = new JLabel("");
		GridBagConstraints gbc_origenValue = new GridBagConstraints();
		gbc_origenValue.insets = new Insets(0, 0, 5, 5);
		gbc_origenValue.gridx = 5;
		gbc_origenValue.gridy = 5;
		add(origenValue, gbc_origenValue);
		origenValue.setText(origen.toString());
		
		JLabel lblNewLabel_1_2 = new JLabel("Estacion Destino:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 4;
		gbc_lblNewLabel_1_2.gridy = 6;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel destinoValue = new JLabel("");
		GridBagConstraints gbc_destinoValue = new GridBagConstraints();
		gbc_destinoValue.insets = new Insets(0, 0, 5, 5);
		gbc_destinoValue.gridx = 5;
		gbc_destinoValue.gridy = 6;
		add(destinoValue, gbc_destinoValue);
		destinoValue.setText(destino.toString());
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 7;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel precioValue = new JLabel("");
		GridBagConstraints gbc_precioValue = new GridBagConstraints();
		gbc_precioValue.insets = new Insets(0, 0, 5, 5);
		gbc_precioValue.gridx = 5;
		gbc_precioValue.gridy = 7;
		add(precioValue, gbc_precioValue);
		
		JLabel lblNewLabel_3 = new JLabel("Camino a seguir:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 8;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
				
				JLabel caminoASeguirValue = new JLabel("");
				caminoASeguirValue.setFont(new Font("Tahoma", Font.PLAIN, 10));
				GridBagConstraints gbc_caminoASeguirValue = new GridBagConstraints();
				gbc_caminoASeguirValue.anchor = GridBagConstraints.WEST;
				gbc_caminoASeguirValue.gridwidth = 2;
				gbc_caminoASeguirValue.insets = new Insets(0, 0, 5, 5);
				gbc_caminoASeguirValue.gridx = 5;
				gbc_caminoASeguirValue.gridy = 8;
				add(caminoASeguirValue, gbc_caminoASeguirValue);
		
				JSeparator separator_1 = new JSeparator();
				separator_1.setForeground(Color.BLACK);
				separator_1.setBackground(Color.BLACK);
				GridBagConstraints gbc_separator_1 = new GridBagConstraints();
				gbc_separator_1.gridwidth = 10;
				gbc_separator_1.insets = new Insets(0, 0, 5, 0);
				gbc_separator_1.gridx = 1;
				gbc_separator_1.gridy = 9;
				gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
				add(separator_1, gbc_separator_1);
				
		        JButton btnMapa = new JButton("Siguiente");
		        GridBagConstraints gbc_btnMapa = new GridBagConstraints();
		        gbc_btnMapa.insets = new Insets(0, 0, 5, 5);
		        gbc_btnMapa.gridx = 5;
		        gbc_btnMapa.gridy = 11;
		        add(btnMapa, gbc_btnMapa);
		        
		        JButton btnCancelar = new JButton("Cancelar");
		        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		        gbc_btnCancelar.gridx = 4;
		        gbc_btnCancelar.gridy = 12;
		        btnCancelar.setVisible(false);
		        add(btnCancelar, gbc_btnCancelar);
		        
		        JButton btnVolverMapa = new JButton("Ver mapa");
		        GridBagConstraints gbc_btnVolverMapa = new GridBagConstraints();
		        gbc_btnVolverMapa.insets = new Insets(0, 0, 5, 5);
		        gbc_btnVolverMapa.gridx = 5;
		        gbc_btnVolverMapa.gridy = 12;
		        btnVolverMapa.setVisible(false);
		        add(btnVolverMapa, gbc_btnVolverMapa);
		        
		        JButton btnConfirmar = new JButton("Confirmar venta");
		        GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		        gbc_btnConfirmar.insets = new Insets(0, 0, 5, 5);
		        gbc_btnConfirmar.gridx = 6;
		        gbc_btnConfirmar.gridy = 12;
		        add(btnConfirmar, gbc_btnConfirmar);
		        btnConfirmar.setVisible(false);
		        btnMapa.addActionListener(e-> {
		        	
		        	Pedido pedido = Pedido.valueOf(getSelectedButtonText(grupo));
					//new Mapa(origen, destino, pedido).start();
			    	System.setProperty("org.graphstream.ui", "swing");
			        Graph graph = new MultiGraph("Mapa");
			        try {
			        	ArrayList<Estacion> estaciones= admin2.getEstaciones("");
			        	ArrayList<LineaDeTransporte> lineas= admin3.getLineasDeTransporte("");
			        	List<Estacion> camino= admin.caminoPedido(estaciones, lineas, origen, destino, pedido);
			        	List<Deque<Pair<Estacion, LineaDeTransporte>>> resultado = admin.getCaminos(estaciones, origen, destino);
			        	Iterator<Pair<Estacion, LineaDeTransporte>> it;
			        	Integer count = 0;
			        	
			        	if(camino != null) {
			        	for(int i=0; i<resultado.size(); i++) {
			       		 	ArrayList<Pair<Estacion, LineaDeTransporte>> nodospedges = new ArrayList<Pair<Estacion, LineaDeTransporte>>();
			        		 it = resultado.get(i).iterator();
							while(it.hasNext()) {
								Pair<Estacion, LineaDeTransporte> actual2 = it.next();
								String actual = actual2.first.toString();
								if(graph.getNode(actual) == null) {
									graph.addNode(actual);
								}
								//System.out.println(actual);
								nodospedges.add(actual2);
							}
							for(Integer k= 1; k<nodospedges.size(); k++) {
								if(graph.getEdge(nodospedges.get(k).toString()) == null) {
									graph.addEdge(nodospedges.get(k).toString(), nodospedges.get(k-1).first.toString(), nodospedges.get(k).first.toString(), true);
									graph.getEdge(nodospedges.get(k).toString()).setAttribute("ui.label", nodospedges.get(k).second.getNombre());
								}
					            count++;
							}
							}
			        	Double costo = Double.valueOf(admin.valor(camino));
				        precioValue.setText(costo.toString());
				        caminoASeguirValue.setText(camino.toString());
				        
				        graph.setAutoCreate(true);
				        graph.setStrict(true);
				        for (Node node : graph) {
				            node.setAttribute("ui.label", node.getId());
				        }
				        
				        
				        Object[] opciones = {"De acuerdo!", "No"};
				        Object defaultOp = opciones[0];
				        
				        btnMapa.setVisible(false);
				        btnConfirmar.setVisible(true);
				        btnCancelar.setVisible(true);
				        btnVolverMapa.setVisible(true);
				        
				        btnConfirmar.addActionListener(f-> {
				        
				        
				        int elegido = JOptionPane.showOptionDialog(this, "Se registrara la venta con los datos descritos. ¿Esta seguro?", "Registrar venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, defaultOp);
				        if(elegido == JOptionPane.YES_OPTION) {
				        	Boleto nuevo = new Boleto(null, email, nombre, LocalDate.now(), origen, destino, costo, camino);
				        	AdministradorDeBoletos admin4 = new AdministradorDeBoletos();
				        	try {
								admin4.addBoleto(nuevo);
								btnConfirmar.setVisible(false);
								btnCancelar.setVisible(false);
								btnVolverMapa.setVisible(false);
								btnMapa.setVisible(true);
								precioValue.setText("");
								caminoASeguirValue.setText("");
								JOptionPane.showMessageDialog(this, "La venta se realizo correctamnete.","Info",JOptionPane.INFORMATION_MESSAGE);
								 cambiarVentana();
				        	} catch (ClassNotFoundException | SQLException e1) {
								JOptionPane.showMessageDialog(this, "Ocurrio al registrar la venta.","Error",JOptionPane.ERROR_MESSAGE);
							}
				        } 
				       });
				        
				        btnCancelar.addActionListener(f-> { 
				        	cambiarVentana();
				        });
				        
				        btnVolverMapa.addActionListener(f-> { 
					        Viewer viewer2 = graph.display();
					        viewer2.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
				        });
				        
				        
				        
			        	} else {
			        		JOptionPane.showMessageDialog(this, "No existe camino entre las estaciones elegidas.","Error",JOptionPane.ERROR_MESSAGE);
			        		 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
							 ventana.getContentPane().removeAll(); //Remover componentes
							 ventana.add(new VenderBoleto(), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
							 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
			        	}
					} catch (ClassNotFoundException | SQLException f) {
						JOptionPane.showMessageDialog(this, "Ocurrio un error al mostrar el mapa.","Error",JOptionPane.ERROR_MESSAGE);	//Agregar excepcion
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
	
	public void cambiarVentana() {
		 JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtener  Jframe donde está el Jpanel
		 ventana.getContentPane().removeAll(); //Remover componentes
		 ventana.add(new VenderBoleto(), BorderLayout.CENTER); //Agregar 2da interfaz de vender boleto
		 SwingUtilities.updateComponentTreeUI(ventana); //Actualizar componentes de la ventana
	}
	
}
