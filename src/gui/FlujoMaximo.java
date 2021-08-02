package gui;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.html.parser.ParserDelegator;

import estacion.AdministradorDeCaminos;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.Pedido;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.ColorLineaDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import pair.Pair;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class FlujoMaximo extends JPanel {

	/**
	 * Create the panel.
	 */
	public FlujoMaximo(Estacion origen) {
		

		  
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Flujo m\u00E1ximo");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox<Estacion> comboBox = new JComboBox<Estacion>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		try {
			this.cargarEstaciones(comboBox);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error al cargas las estaciones");
		}
		
		
		JButton boton = new JButton("Elegir estacion destino");
		GridBagConstraints gbc_boton = new GridBagConstraints();
		gbc_boton.insets = new Insets(0, 0, 5, 5);
		gbc_boton.gridx = 3;
		gbc_boton.gridy = 4;
		add(boton, gbc_boton);
		

  		JProgressBar progressBar = new JProgressBar(0, 100);
  		 progressBar.setStringPainted(true);
  		GridBagConstraints gbc_progressBar = new GridBagConstraints();
  		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
  		gbc_progressBar.gridx = 3;
  		gbc_progressBar.gridy = 6;
  		add(progressBar, gbc_progressBar);
  		
  		JLabel lblNewLabel_1 = new JLabel("");
  		GridBagConstraints gbc_labelValor = new GridBagConstraints();
  		gbc_labelValor.insets = new Insets(0, 0, 5, 5);
  		gbc_labelValor.gridx = 3;
  		gbc_labelValor.gridy = 7;
  		add(lblNewLabel_1, gbc_labelValor);
  		
  		JLabel res = new JLabel("");
  		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
  		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
  		gbc_lblNewLabel_1.gridx = 3;
  		gbc_lblNewLabel_1.gridy = 8;
  		add(res, gbc_lblNewLabel_1);
  		
  		JLabel labelValor = new JLabel("Flujo maximo: ");
  		GridBagConstraints gbc_labelValor1 = new GridBagConstraints();
  		gbc_labelValor1.insets = new Insets(0, 0, 5, 5);
  		gbc_labelValor1.gridx = 3;
  		gbc_labelValor1.gridy = 10;
  		add(labelValor, gbc_labelValor1);
  		
  		JLabel estacionesFMax = new JLabel("Camino: ");
  		GridBagConstraints gbc_estacionesFMax = new GridBagConstraints();
  		gbc_estacionesFMax.insets = new Insets(0, 0, 0, 5);
  		gbc_estacionesFMax.gridx = 3;
  		gbc_estacionesFMax.gridy = 12;
  		add(estacionesFMax, gbc_estacionesFMax);

		boton.addActionListener(e->{
		
			boton.setVisible(false);
			lblNewLabel_1.setText("Caminos posibles: ");
			Estacion destino = comboBox.getItemAt(comboBox.getSelectedIndex());
			ArrayList<Estacion> lista = new ArrayList<Estacion>();
	    	AdministradorDeCaminos admin = new AdministradorDeCaminos();	
	    	AdministradorDeCaminos admin2 = new AdministradorDeCaminos();	
			Thread thread = new Thread(){
				    public void run(){
				    
				    	List<Estacion> estaciones;
						try {
							estaciones = new AdministradorDeEstaciones().getEstaciones("");
							List<LineaDeTransporte> lineas = new AdministradorDeLineasDeTransporte().getLineasDeTransporte("");
							
							//System.out.println(admin2.caminoPedido(estaciones, lineas, origen, destino, Pedido.MAXIMOPESO));
							Integer valor = admin.mayorPesoDeAaB(estaciones, lineas, origen, destino);
							List<Deque<Pair<Estacion, ColorLineaDeTransporte>>> resultado = admin.getCaminos(estaciones, origen, destino);
							ParserDelegator workaround = new ParserDelegator();
							String texto = "";
							texto = texto + "<html>";
							for(int i=0; i<resultado.size(); i++) {
								for(int j=0; j<resultado.get(i).size(); j++){
								texto = texto + " " + resultado.get(i).poll().first.toString() + " -->";
								}
								texto = texto + "" + destino.toString();
								texto = texto + "<br></br>";
							}
							texto = texto + "</html>";
							res.setText(texto);
							labelValor.setText(labelValor.getText() + " " + valor + " pasajeros");
							estacionesFMax.setText(estacionesFMax.getText() + admin.getEstacionesMaximoFlujo());
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				    }
				  };
				  
				  Thread barra = new Thread(){
					    public void run(){
					    	while(!admin.finalizado) {
							        progressBar.setValue(admin.porcentaje);
							        progressBar.repaint();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}		
					    	}
					    	progressBar.setValue(100);
					        progressBar.repaint();
					    	}
					    	
					    };
		    	  thread.start();
		    	  barra.start();
				 
		});
		
	}
	
	private void  cargarEstaciones(JComboBox<Estacion> ests) throws ClassNotFoundException, SQLException {
		AdministradorDeEstaciones admin = new AdministradorDeEstaciones();
		ArrayList<Estacion> estaciones = admin.getEstaciones("");
		estaciones.stream().forEach(e -> ests.addItem(e));

	}

}