package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Ventana extends JFrame {
	JPanel actual = new VenderBoleto();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					frame.setSize(600, 400);
					frame.setTitle("Sistema de Gestión Transporte Multimodal");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(25, 25));
		this.getContentPane().add(new VenderBoleto());//Ventana por default
		//JPanels
		
		
		JMenuBar menuBar = new JMenuBar();
		//Opciones del menu
		JMenu menu3 = new JMenu("Boletos");
		JMenu menu1 = new JMenu("Estaciones");
		JMenu menu2 = new JMenu("Lineas de Transportes");
		menuBar.add(menu3);
		menuBar.add(menu1);
		menuBar.add(menu2);
		//Opciones submenu Estaciones
		JMenuItem mi1 = new JMenuItem("Agregar");
		JMenuItem mi2 = new JMenuItem("Consultar estaciones");
		JMenuItem mi3 = new JMenuItem("PageRank");
		JMenuItem mi4 = new JMenuItem("Proximo matenimiento");
		menu1.add(mi1);
		menu1.add(mi2);
		menu1.add(mi3);
		menu1.add(mi4);
		//Opciones submenu Lineas de Transporte
		JMenuItem mi6 = new JMenuItem("Agregar");
		JMenuItem mi7 = new JMenuItem("Consultar lineas de transporte");
		menu2.add(mi6);
		menu2.add(mi7);
		//Opciones menu Boletos
		JMenuItem mi11 = new JMenuItem("Vender Boleto");
		menu3.add(mi11);

		mi1.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new RegistrarEstacion());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		mi2.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new BMEEstacionPadre());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		mi3.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new PageRank());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		mi4.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new ProximoMantenimiento());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});

		mi6.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new RegistrarLineaDeTransporte());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		mi7.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new BMELineaDeTransporte());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		mi11.addActionListener(e -> {
			 this.getContentPane().removeAll();
			 this.getContentPane().add(new VenderBoleto());
			 SwingUtilities.updateComponentTreeUI(this.getContentPane());
		});
		setJMenuBar(menuBar);
	}
		

		 }
		
