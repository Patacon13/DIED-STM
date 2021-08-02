package gui; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import org.graphstream.graph.*;
    import org.graphstream.graph.implementations.*;
import org.graphstream.ui.layout.springbox.EdgeSpring;

import estacion.AdministradorDeCaminos;
import estacion.AdministradorDeEstaciones;
import estacion.Estacion;
import estacion.Pedido;
import lineaDeTransporte.AdministradorDeLineasDeTransporte;
import lineaDeTransporte.ColorLineaDeTransporte;
import lineaDeTransporte.LineaDeTransporte;
import pair.Pair;

    public class GraphExplore extends JPanel{

        public GraphExplore(Estacion origen, Estacion destino) {
        	System.setProperty("org.graphstream.ui", "swing");
            Graph graph = new SingleGraph("tutorial 1");
            AdministradorDeCaminos admin = new AdministradorDeCaminos();
            AdministradorDeEstaciones admin2 = new AdministradorDeEstaciones();
            AdministradorDeLineasDeTransporte admin3 = new AdministradorDeLineasDeTransporte();
            try {
            	ArrayList<Estacion> estaciones= admin2.getEstaciones("");
            	ArrayList<LineaDeTransporte> lineas= admin3.getLineasDeTransporte("");
            	System.out.println(admin.caminoPedido(estaciones, lineas, origen, destino, Pedido.MASBARATO));
            	List<Deque<Pair<Estacion, LineaDeTransporte>>> resultado = admin.getCaminos(estaciones, origen, destino);
            	System.out.println(resultado);
            	Iterator<Pair<Estacion, LineaDeTransporte>> it;
            	Integer count = 0;
            	for(int i=0; i<resultado.size(); i++) {
           		 	ArrayList<String> nodospedges = new ArrayList<String>();
            		 it = resultado.get(i).iterator();
					while(it.hasNext()) {
						String actual = it.next().first.toString();
						if(graph.getNode(actual) == null) {
							System.out.println(actual);
							graph.addNode(actual).setAttribute("hola", null);
						}
						nodospedges.add(actual);
					}
					for(Integer k= 0; k<nodospedges.size()-1; k++) {
			            graph.addEdge(count.toString(), nodospedges.get(k), nodospedges.get(k+1));
			            graph.getEdge(count.toString()).setAttribute("ui.label", "Linea 1");
			            count++;
					}
					}

            	
			} catch (ClassNotFoundException | SQLException e) {

			}
            
            //graph.addEdge("Ruta 2", "Estacion A1", "Estacion A3");

            graph.setAutoCreate(true);
            graph.setStrict(true);
            graph.display();

            for (Node node : graph) {
                node.setAttribute("ui.label", node.getId());
            }
            /*
           graph.getEdge("Ruta 1").setAttribute("ui.label", "Linea 1");
           graph.getEdge("Ruta 2").setAttribute("ui.label", "Linea 1");
           graph.getEdge("Ruta 3").setAttribute("ui.label", "Linea 3");
           graph.getEdge("Ruta 4").setAttribute("ui.label", "Linea 2");
*/
           



        }


    }