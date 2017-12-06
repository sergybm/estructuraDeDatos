import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.TreeMapGraph;
import graphsDSESIUCLM.Vertex;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Graph gr = new TreeMapGraph<>();
		Queue<DecoratedElementTraversal> traversal;

		Vertex<DecoratedElementTraversal> startVertex;
		String startVID;

		createGraph(gr);

		startVID = "1";
		startVertex = gr.getVertex(startVID);
		if (startVertex == null) {
			System.out.println("The vertex does not exist");
		}
	public static void createGraph(Graph gr) throws IOException {

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
				//DecoratedElementTraversal e1 = new DecoratedElementTraversal(cadena[0], ap1); Cadena[0] es la llave con la que podemos identificarlo
				System.out.println(ap1.toString());
			}
		}
		b.close();

		f = new FileReader(rutas);
		b = new BufferedReader(f);
		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");

			Ruta ruta = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));
	

			// System.out.println(rutas.toString());

		}
		b.close();


	}

	public static void CargarRutas(String archivo) throws FileNotFoundException, IOException {

	}

	public static String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
