import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.TreeMapGraph;
import graphsDSESIUCLM.Vertex;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int cont = 0;

		Graph gr = new TreeMapGraph<>();
		Queue<ElementoDecorado> traversal;

		Vertex<ElementoDecorado> startVertex;
		String startVID;

		createGraph(gr);

		startVID = "1";
		startVertex = gr.getVertex(startVID);
		if (startVertex == null) {
			System.out.println("The vertex does not exist");
		} else {
			traversal = DFSIter(gr, startVertex);
			System.out.println("Nonrecursive DFS traversal with the node " + startVID + " as starting vertex\n");
			while (!traversal.isEmpty())
				// System.out.println("\n Elemento: "+ ++cont);
				System.out.print(traversal.remove().getElement().toString() + " ");
		} 
		 mostrarGrafo(gr);
		
		GenerarInforme(gr);
	}

	public static Queue<ElementoDecorado> DFSIter(Graph g, Vertex<ElementoDecorado> s) {

		Stack<Vertex<ElementoDecorado>> p;
		Queue<ElementoDecorado> traversal;
		p = new Stack<Vertex<ElementoDecorado>>();
		traversal = new LinkedList<ElementoDecorado>();

		Vertex<ElementoDecorado> u, v;
		Edge e;
		Iterator<Edge<ElementoDecorado>> it;

		p.push(s);
		while (!p.isEmpty()) {
			u = p.pop();
			if (!u.getElement().getVisited()) {
				traversal.offer(u.getElement());
				u.getElement().setVisited(true);
				it = g.incidentEdges(u);
				while (it.hasNext()) {
					e = it.next();
					v = g.opposite(u, e);
					if (!v.getElement().getVisited())
						p.push(v);
				}
			}
		}
		return traversal;
	}

	public static void createGraph(Graph gr) throws IOException {
		List<Aeropuerto> list = new ArrayList<Aeropuerto>();
		String aeropuertos = "airports.txt";
		String rutas = "routes.txt";
		String linea;
		String[] cadena;
	

		FileReader f = new FileReader(aeropuertos);
		BufferedReader b = new BufferedReader(f);
		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			if ((cadena[3].equals("\"Spain\"") || cadena[3].equals("\"Italy\"") || cadena[3].equals("\"Germany\"")
					|| cadena[3].equals("\"France\"")) && quitarComillas(cadena[4]).length() == 3) {

				Aeropuerto ap1 = new Aeropuerto(quitarComillas(cadena[0]), quitarComillas(cadena[3]),
						quitarComillas(cadena[4]), cadena[6], cadena[7], cadena[8]);
				/* Añadimos los aeropuertos a una lista para luego manejarlos */
				list.add(ap1);

			}
		}
		b.close();

		f = new FileReader(rutas);
		b = new BufferedReader(f);
		List<Ruta> listrutas = new ArrayList<Ruta>();
		String iOrigen,iDestino,jOrigen,jDestino;
		int i,j;

		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			Ruta ruta = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));
			listrutas.add(ruta);
		}
		b.close();
		
	
	
	
		/// Eliminar las rutas inversas 
		
		for(i=0;i<listrutas.size();i++) {
				iOrigen=listrutas.get(i).getCodeOrigen(); ///iOrigen = iATA aeropuerto de origen
				iDestino=listrutas.get(i).getCodeDestino(); /// iDestino = iATA aeropuerto de destino
				for (j=0;j<listrutas.size();j++) {			
					jOrigen=listrutas.get(j).getCodeOrigen(); // jOrigen = iATA aeropuerto de origen
					jDestino=listrutas.get(j).getCodeDestino(); // jDestino = iATA aeropuerto de destino
						if (iDestino.equals(jOrigen) && iOrigen.equals(jDestino)){
							listrutas.remove(listrutas.get(j));
						}
				}
			} 
		
		
		
		
			ElementoDecorado<Aeropuerto> e1 = null;
			ElementoDecorado<Aeropuerto> e2 = null;
			
		
		for (i=0;i<listrutas.size();i++) {
					
			for (Aeropuerto b1 : list) {
				if (b1.getIATA().equals(listrutas.get(i).getCodeOrigen())) {
					for (Aeropuerto b2 : list) {
						if (b2.getIATA().equals(listrutas.get(i).getCodeDestino())){
					e1 = new ElementoDecorado<Aeropuerto>(b1.getIATA(), b1);	/* Elemento decorado Origen */
					e2 = new ElementoDecorado<Aeropuerto>(b2.getID(), b2);/* Elemento decorado Destino */
					
						}
				}
			}
		}
				/* Une los dos vertices si son Origen y destino */
				if (e1 != null && e2 != null) {
					gr.insertEdge(e1, e2);
				}	
			}
		
		
		}
		

		
		
	
	

	public static void mostrarGrafo(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr){
		System.out.println("\nGrafo:");
		
		Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		
		int contadorAeropuertos=0;
		int contadorRutas=0;
    	boolean iguales=false;
		
		while(itr.hasNext()){//Dos While anidados para mostrar cada aeropuerto del grafo  y sus adyacentes.
			Vertex<ElementoDecorado<Aeropuerto>> u = itr.next(); 
			Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr1 = gr.getVertices();
			System.out.print("Conexión: " +u.getElement().getElement().IATA +"  -----------> ");
			contadorAeropuertos++;
				while(itr1.hasNext()){ //Por cada aeropuerto recorremos los vértices e imprimimos los adyacentes.
					Vertex<ElementoDecorado<Aeropuerto>> u2 = itr1.next(); 
			
				if(u.equals(u2)) {
					iguales=true;
				}
				else {
					iguales=false;
				}
					if(gr.areAdjacent(u, u2) && iguales==false){						
						System.out.print(u2.getElement().getElement().IATA+", ");
						contadorRutas++;
					}
				}
				System.out.println();
			}
		System.out.println();
		
		System.out.println(contadorAeropuertos);
		System.out.println(contadorRutas);
	}
	
	public static void GenerarInforme(Graph gr) {
		
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		String masNorte = null;
		String masOeste = null;
		String masConexiones = null;
		double altitudMedia = 0;
		double aux = 0;
		double sumaAltitud = 0;
		double aux2=0;
		
		double conexiones=0;
		
		while (itr.hasNext()) {		
			Vertex<ElementoDecorado<Aeropuerto>> a = itr.next();
			/*/ Aeropuerto mas al norte /*/	
			if (Double.parseDouble(a.getElement().getElement().getLatitud()) > aux) {
				aux=(Double.parseDouble(a.getElement().getElement().getLatitud()));
				masNorte=a.getElement().getElement().getPais(); /// Deberíamos poner el nombre del aeropuerto pero no lo hemos cogido de los datos
			}
		  /*/ Altitud media de los paises /*/
			sumaAltitud=Double.parseDouble(a.getElement().getElement().getAltitud()) + sumaAltitud;
		
			/*/ Aeropuerto mas al oeste /*/
			if (Double.parseDouble(a.getElement().getElement().getLongitud()) < aux2) {
				aux=(Double.parseDouble(a.getElement().getElement().getLongitud()));
				masOeste=a.getElement().getElement().getPais(); /// Deberíamos poner el nombre del aeropuerto pero no lo hemos cogido de los datos
			}
			/*/ Aeropuerto con mas conexiones /*/
			
			// FALTA POR HACER
			
		}
		
		altitudMedia=(sumaAltitud/gr.getN()); // gr.getN me da los vertices que tengo, en este caso los aeropuertos
		
		/*/ NOTAS
		 * La altitud media creo que está mal y faltan las conexiones
		 */
		
		
		System.out.println("El aeropuerto más al norte es: " + masNorte); 
		System.out.println("El aeropuerto más al oeste es: " + masOeste);
		System.out.println("El aeropuerto que mas conexiones tiene es: " + masConexiones);
		System.out.println("La altitud media de todos los aeropuertos es: " + altitudMedia + " pies");
	
		
	}

	public static String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
