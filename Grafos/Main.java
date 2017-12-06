
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
		int cont=0;

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
				//System.out.println("\n Elemento: "+ ++cont);
				System.out.print(traversal.remove().getElement().toString() + " ");
		}
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
		ElementoDecorado<Aeropuerto> e1 = null;
		ElementoDecorado<Aeropuerto> e2 = null;
		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");

			Ruta ruta = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));
			for (Aeropuerto b1 : list) {
				/* Elemento decorado Origen*/
				if (b1.getIATA().equals(ruta.getCodeOrigen())) {
					e1 = new ElementoDecorado<Aeropuerto>(b1.getID(), b1);
				}
				/* Elemento decorado Destino*/
				if (b1.getIATA().equals(ruta.getCodeDestino())) { 
					e2 = new ElementoDecorado<Aeropuerto>(b1.getID(), b1);

				}
			}
			/* Une los dos vertices si son Origen y destino */
			if (e1 != null && e2 != null) {
				gr.insertEdge(e1, e2);

			}
		}
		b.close();

	}

	public static String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
