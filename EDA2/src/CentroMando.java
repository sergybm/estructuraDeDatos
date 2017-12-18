
/***************************************
 ******************************
 *
 * Class Name: Aeropuerto 
 * Author/s name: Lucía Alfonso García 
 * 				  Sergio Barrios Martínez 
 * 				  Raul Ruiz del Valle Arevalo
 * 
 * Class description: Es la clase donde se encuentran la mayoría de los métodos, 
 * documentados cada uno de ellos.
 **********************************************************************
 */
//Paquetes importados
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.Vertex;

public class CentroMando {
	final static String aeropuertos = "airports.txt";
	final static String rutas = "routes.txt";
	private List<Aeropuerto> listAeropuertos = new ArrayList<Aeropuerto>();
	private List<Ruta> listRutas = new ArrayList<Ruta>();

	/*********************************************************************
	 *
	 * Method name:leerAeropuertos
	 *
	 * Description of the Method: este metodo lee todos los datos de los aeropuertos
	 * y se queda con los aeropuertos que nos interesan, es decir, con los que
	 * pertenecen a España, Italia, Alemania y Francia.
	 *
	 * Calling arguments: Graph gr
	 * 
	 * Return value: List listAeropuertos.
	 *
	 * Required Files: airports.txt
	 * 
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown. - IOException
	 *
	 *********************************************************************/

	public List<Aeropuerto> leerAeropuertos(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr)
			throws IOException {
		String linea;
		String[] cadena;
		FileReader f = new FileReader(aeropuertos);
		BufferedReader b = new BufferedReader(f);

		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			if ((cadena[3].equals("\"Spain\"") || cadena[3].equals("\"Italy\"") || cadena[3].equals("\"Germany\"")
					|| cadena[3].equals("\"France\"")) && quitarComillas(cadena[4]).length() == 3) {

				Aeropuerto ap1 = new Aeropuerto((cadena[0]), quitarComillas(cadena[1]), quitarComillas(cadena[2]),
						quitarComillas(cadena[3]), quitarComillas(cadena[4]), cadena[6], cadena[7], cadena[8]);

				listAeropuertos.add(ap1);

			}

		}
		b.close();
		return listAeropuertos;
	}

	/*********************************************************************
	 *
	 * Method name: leerRutas
	 *
	 * Description of the Method: Se ejecuta mientras haya más lineas de en el
	 * documento, llama al constructor y comprueba si los dos aeropuertos implicados
	 * son de los que nos interesan, en caso afirmativo, se guarda dicha ruta.
	 *
	 * Calling arguments: -List listAeropuertos;
	 *
	 * Return value: List<Ruta>;
	 * 
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown. -IOException;
	 *
	 *********************************************************************/

	public List<Ruta> leerRutas(List<Aeropuerto> listAeropuertos) throws IOException {
		int i, j;
		String linea;
		String[] cadena;
		FileReader f = new FileReader(rutas);
		BufferedReader b = new BufferedReader(f);

		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			Ruta ruta = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));

			// Añade a la lista las rutas que tienen un iATA que coinciden con los de los
			// aeropuertos y por lo tanto con los paises
			for (i = 0; i < listAeropuertos.size(); i++) {
				if ((cadena[2]).equals(listAeropuertos.get(i).getIATA())) {
					for (j = 0; j < listAeropuertos.size(); j++) {
						if ((cadena[4]).equals(listAeropuertos.get(j).getIATA())) {
							listRutas.add(ruta);
						}
					}
				}
			}

		}

		b.close();
		vuDobleRutaInversa(listRutas);
		return listRutas;

	}

	/*********************************************************************
	 *
	 * Method name: vuDobleRutaInversa
	 *
	 * Description of the Method: Este método elimina las rutas repetidas, ya sean
	 * en el mismo sentido o en sentido contrario
	 *
	 * Calling arguments: List listRutas.
	 *
	 * Return value: List listRutas.
	 * 
	 *********************************************************************/

	public List<Ruta> vuDobleRutaInversa(List<Ruta> listRutas) {
		int i, j;
		String iOrigen, iDestino, jOrigen, jDestino;
		String iAerolinea, jAerolinea;

		// Elimino los vuelos dobles, es decir, los que son de varias compañias pero
		// tienen la misma ruta
		for (i = 0; i < listRutas.size(); i++) {
			iAerolinea = listRutas.get(i).getAerolinea();
			iOrigen = listRutas.get(i).getCodeOrigen(); /// iOrigen = iATA aeropuerto de origen
			iDestino = listRutas.get(i).getCodeDestino(); /// iDestino = iATA aeropuerto de destino
			for (j = 0; j < listRutas.size(); j++) {
				jAerolinea = listRutas.get(j).getAerolinea();
				jOrigen = listRutas.get(j).getCodeOrigen(); // jOrigen = iATA aeropuerto de origen
				jDestino = listRutas.get(j).getCodeDestino(); // jDestino = iATA aeropuerto de destino
				if (iOrigen.equals(jOrigen) && iDestino.equals(jDestino) && !iAerolinea.equals(jAerolinea)) {
					listRutas.remove(listRutas.get(j));
				}
			}
		}

		/// Eliminar las rutas inversas

		for (i = 0; i < listRutas.size(); i++) {
			iOrigen = listRutas.get(i).getCodeOrigen();
			iDestino = listRutas.get(i).getCodeDestino();
			for (j = 0; j < listRutas.size(); j++) {
				jOrigen = listRutas.get(j).getCodeOrigen();
				jDestino = listRutas.get(j).getCodeDestino();
				if (iDestino.equals(jOrigen) && iOrigen.equals(jDestino)) {
					listRutas.remove(listRutas.get(j));
				}

			}
		}
		return listRutas;
	}

	/*********************************************************************
	 *
	 * Method name: createGraph
	 *
	 * Description of the Method: En el caso de que el IATA origen y destino de la
	 * ruta coincidan con el de los aeropuertos que nos interesan, lo introduce en
	 * un elemento decorado e inserta la arista, creando así el grafo.
	 *
	 * Calling arguments: Graph gr, List listAeropuertos, List listRutas, ArrayList
	 * vertices.
	 *
	 * Return value: void
	 *
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown. -IOException
	 *
	 *********************************************************************/

	public void createGraph(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr,
			List<Aeropuerto> listAeropuertos, List<Ruta> listRutas,
			ArrayList<Vertex<ElementoDecorado<Aeropuerto>>> vertices) throws IOException {
		// Creamos el grafo
		int i, o, p;
		ElementoDecorado<Aeropuerto> e1 = null;
		ElementoDecorado<Aeropuerto> e2 = null;
		Aeropuerto b1, b2;

		for (i = 0; i < listRutas.size(); i++) {
			for (o = 0; o < listAeropuertos.size(); o++) {
				b1 = listAeropuertos.get(o);
				if (b1.getIATA().equals(listRutas.get(i).getCodeOrigen())) {
					e1 = new ElementoDecorado<Aeropuerto>(b1.getIATA(), b1); // Elemento decorado Origen
					for (p = 0; p < listAeropuertos.size(); p++) {
						b2 = listAeropuertos.get(p);
						if (b2.getIATA().equals(listRutas.get(i).getCodeDestino())) {
							e2 = new ElementoDecorado<Aeropuerto>(b2.getIATA(), b2); // Elemento decorado Destino
							// Une los dos vertices si son Origen y destino
							if (e1 != null && e2 != null) {
								gr.insertEdge(e1, e2);
							}
						}
					}
				}

			}

		}
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		while (itr.hasNext()) {
			vertices.add(itr.next());
		}
	} // FIN

	/*********************************************************************
	 *
	 * Method name: mostrarGrafo
	 *
	 * Description of the Method: Muestra por pantalla los vertices con una de sus
	 * conexiones de las rutas.
	 *
	 * Calling arguments: Graph gr.
	 *
	 * Return value: void.
	 *
	 *********************************************************************/

	public void mostrarGrafo(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {
		int cont = 0;
		Vertex[] v;
		Iterator<Edge<ElementoDecorado<Ruta>>> ite = gr.getEdges();
		System.out.println("Conexiones del grafo");
		while (ite.hasNext()) {
			v = gr.endVertices(ite.next());
			System.out.print(v[0].getElement().toString());
			System.out.print("--->" + v[1].getElement().toString());
			System.out.println();
			cont++;

		}
		System.out.println(cont);
	}

	/*********************************************************************
	 *
	 * Method name: verAeropuertos
	 *
	 * Description of the Method: Muestra por pantalla los distintos aeropuertos que
	 * nos interesan.
	 *
	 * Calling arguments: Graph gr.
	 *
	 * Return value: void.
	 *
	 *********************************************************************/

	public void verAeropuertos(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {
		int cont = 0;
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> a;
		while (itr.hasNext()) {
			a = itr.next();
			System.out.println(a.getElement().getElement().toString());
			cont++;
		}
		System.out.println(cont);

	}

	/*********************************************************************
	 *
	 * Method name: GenerarInforme
	 *
	 * Description of the Method: nos indica cuáles son los aeropuertos que nos pide
	 * la práctica, es decir, los que están más al norte, más al oeste, el más alto
	 * y el que más conexiones tiene.
	 *
	 * Calling arguments: Graph gr.
	 *
	 * Return value: void.
	 *
	 *********************************************************************/

	public void GenerarInforme(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {

		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		String masNorte = null;
		String masOeste = null;
		double altitudMedia = 0;
		double aux = 0;
		double sumaAltitud = 0;
		double aux2 = 0;
		String masNorteCiudad = null;
		String masOesteCiudad = null;
		Iterator aux3;
		Vertex<ElementoDecorado<Aeropuerto>> a;

		int conexiones = 0;

		while (itr.hasNext()) {
			a = itr.next();

			/* / Aeropuerto mas al norte / */
			if (Double.parseDouble(a.getElement().getElement().getLatitud()) <= 90) {
				if (aux == 0) {
					aux = Double.parseDouble(a.getElement().getElement().getLatitud());
				} else if (Double.parseDouble(a.getElement().getElement().getLatitud()) > aux) {
					aux = (Double.parseDouble(a.getElement().getElement().getLatitud()));
					masNorte = a.getElement().getElement().getNombre();
					masNorteCiudad = a.getElement().getElement().getCiudad();
				}
			}
		}
		/* / Altitud media de los paises / */
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itrM = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> m;
		while (itrM.hasNext()) {
			m = itrM.next();
			sumaAltitud = Double.parseDouble(m.getElement().getElement().getAltitud()) + sumaAltitud;
		}
		altitudMedia = (sumaAltitud / gr.getN()); // gr.getN me da los vértices que tengo, en este caso los aeropuertos

		/* / Aeropuerto mas al oeste / */
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr1 = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> a2;
		while (itr1.hasNext()) {
			a2 = itr1.next();
			if (Double.parseDouble(a2.getElement().getElement().getLongitud()) <= 180) {
				if (aux2 == 0) {
					aux2 = Double.parseDouble(a2.getElement().getElement().getLongitud());
				} else if (Double.parseDouble(a2.getElement().getElement().getLongitud()) < aux2) {
					aux = (Double.parseDouble(a2.getElement().getElement().getLongitud()));
					masOeste = a2.getElement().getElement().getNombre();
					masOesteCiudad = a2.getElement().getElement().getCiudad();
				}
			}
		}

		/* / Aeropuerto con mas conexiones / */
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr2 = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> a3, mayor = null;
		int conexiones2 = 0;// Auxi
		while (itr2.hasNext()) {
			a3 = itr2.next();
			aux3 = gr.incidentEdges(a3);
			while (aux3.hasNext()) {
				aux3.next();
				conexiones++;
			}
			if (conexiones2 == 0) {
				conexiones2 = conexiones;
				mayor = a3;
			} else if (conexiones > conexiones2) {
				conexiones2 = conexiones;
				mayor = a3;
			}

			conexiones = 0;

		}

		System.out.println("El aeropuerto más al norte es: " + masNorte + "  localizado en " + masNorteCiudad);
		System.out.println("El aeropuerto más al oeste es: " + masOeste + "  localizado en " + masOesteCiudad);
		System.out.println("El aeropuerto que mas conexiones tiene es: " + mayor.getElement().getElement().getNombre()
				+ " con " + conexiones2 + " conexiones");
		System.out.println("La altitud media de todos los aeropuertos es: " + altitudMedia + " pies");

	}

	/*********************************************************************
	 *
	 * Method name: BFSRecur
	 *
	 * Description of the Method: Recorre el grafo en BFS.
	 *
	 * Calling arguments: Graph gr, vertex v, vertex ultimo, queue traversal
	 *
	 * Return value: void.
	 *
	 *********************************************************************/
	public static void BFSRecur(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr,
			Vertex<ElementoDecorado<Aeropuerto>> v, Vertex<ElementoDecorado<Aeropuerto>> ultimo,
			Queue<Vertex<ElementoDecorado<Aeropuerto>>> traversal) {
		Vertex<ElementoDecorado<Aeropuerto>> u = null; // Elemento decorado del siguiente vertice al que miramos su
														// parametro visitado
		Edge<ElementoDecorado<Ruta>> e; // Arista que se mira en cada iteración
		Iterator<Edge<ElementoDecorado<Ruta>>> it; // iterator para iterar las aristas incidentes de v
		Vertex<ElementoDecorado<Aeropuerto>> aux = null;

		v.getElement().setVisited(true);
		traversal.offer(v);
		it = gr.incidentEdges(v);
		while (it.hasNext()) {
			e = it.next();
			u = gr.opposite(v, e);
			if (u.equals(ultimo)) {
				ultimo.getElement().setVisited(true);
				traversal.offer(u);
			} else {
				if (gr.areAdjacent(u, ultimo)) {
					aux = u;
				}
			}
		}
		if ((!aux.getElement().getVisited()) && (ultimo.getElement().getVisited() == false)) {
			BFSRecur(gr, aux, ultimo, traversal);
		}
	}

	/******************************************************************************
	 *
	 * Method name: pedirIATA
	 *
	 * Description of the Method: pide por teclado el IATA de los aeropuertos de
	 * origen y destino
	 *
	 * Calling arguments: Graph gr, ArrayList vertices.
	 *
	 * Return value: Vertex.
	 *
	 *******************************************************************************/

	public Vertex<ElementoDecorado<Aeropuerto>> pedirIATA(
			Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr,
			ArrayList<Vertex<ElementoDecorado<Aeropuerto>>> vertices) {
		// Declaración de las variables que vamos a usar
		boolean aerCorrecto = false; // Utilizado para comprobar si el nombre introducido es correcto
		Vertex<ElementoDecorado<Aeropuerto>> ap = null; // Almacena el aeropuerto que has introducido por teclado
		Vertex<ElementoDecorado<Aeropuerto>> aux; // Vértice auxiliar
		Scanner teclado = new Scanner(System.in);

		do {

			String v1 = teclado.nextLine().toUpperCase(); // Almacena el IATA introducido (da igual si en mayúsculas que
															// en minúsuclas)
			// Comprueba si el IATA es correcto
			for (int j = 0; j < gr.getN(); j++) {
				if (vertices.get(j).getID().equals(v1)) {
					aerCorrecto = true;
				}
			}
			if (aerCorrecto) { // Si el aeropuerto es correcto, lo almacena en ap.
				for (int i = 0; i < gr.getN(); i++) {
					aux = gr.getVertex(vertices.get(i).getID());
					if (aux.getElement().getID().contentEquals(v1)) {
						ap = gr.getVertex(v1);
					}
				}
				if (aerCorrecto == false) {
					System.out.printf("La estación no existe.\n", v1);
				}
			}
		} while (aerCorrecto != true);
		return ap;
	}

	/******************************************************************************
	 *
	 * Method name: espiaLento
	 *
	 * Description of the Method: Muestra la ruta que efectua el espía que es
	 * espiado.
	 *
	 * Calling arguments: Graph gr, ArrayList vertices.
	 *
	 * Return value: void
	 *
	 *******************************************************************************/
	public void espiaLento(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr,
			ArrayList<Vertex<ElementoDecorado<Aeropuerto>>> vertices) {

		Scanner teclado = new Scanner(System.in);
		Vertex<ElementoDecorado<Aeropuerto>> primero = null; // Vértice que contiene el primer vértice de la búsqueda
		Vertex<ElementoDecorado<Aeropuerto>> ultimo = null; // Vértice que contiene el último vértice de la búsqueda
		System.out.println("Introduzca el IATA origen: ");
		primero = pedirIATA(gr, vertices); // Vértice con el aeropuerto de origen
		System.out.println("Introduzca el IATA destino: ");
		ultimo = pedirIATA(gr, vertices); // Vértice con el aeropuerto de destino
		boolean noPath;// Usado para comprobar si hay algún camino disponible
		Stack<Edge> p1 = new Stack(), p2 = new Stack();
		Vertex[] v; // Vector para recorrer los elementos de la pila
		noPath = pathDFS(gr, primero, ultimo, p1); // Llamada al método

		if (!noPath) {// Si hay un camino disponible, se cambia de una pila a la otra
			while (!p1.isEmpty()) {
				p2.push(p1.pop());

			}

			System.out.println("\nRuta del espia perseguido: \n");
			Vertex aux1 = null;
			int i = 0;
			while (!p2.isEmpty()) { // Muestra los diferentes viajes que realiza
				v = gr.endVertices(p2.pop());
				if (i == 0) {
					System.out.print(v[0].getElement().toString() + " ==> ");
					System.out.print(v[1].getElement().toString() + "\t");
					i++;
					aux1 = v[1];
				} else {
					if (!(v[1].getElement().equals(aux1.getElement()))) {
						System.out.print(v[0].getElement().toString() + " ==> ");
						System.out.print(v[1].getElement().toString() + "\t");
					} else {
						System.out.print(v[1].getElement().toString() + " ==> ");
						System.out.print(v[0].getElement().toString() + "\t");
					}
					i++;
					if (i % 5 == 0) {
						System.out.println();
					}
				}
				aux1 = v[1];
			}
		} else {
			System.out.println("\n No hay ningun camino posible entre esos dos nodos. ");
		}

		espiaRapido(gr, vertices, primero, ultimo); // Llama al método

	}

	/******************************************************************************
	 *
	 * Method name: pathDFS
	 *
	 * Description of the Method: elabora el grafo recorriendolo en DFS
	 *
	 * Calling arguments: Graph g, Vertex primero, Vertex ultimo, Stack sp.
	 *
	 * Return value: Boolean
	 *
	 *******************************************************************************/
	public static boolean pathDFS(Graph g, Vertex<ElementoDecorado<Aeropuerto>> primero,
			Vertex<ElementoDecorado<Aeropuerto>> ultimo, Stack<Edge> sp) {

		boolean noEnd = !primero.equals(ultimo);
		Edge e;
		Iterator<Edge<ElementoDecorado<Ruta>>> it; // Iterator para iterar las aristas incidentes de v
		Vertex<ElementoDecorado<Aeropuerto>> aux = null; // Vértice auxiliar
		primero.getElement().setVisited(true);
		it = g.incidentEdges(primero);
		while (it.hasNext() && noEnd) {
			e = it.next();
			aux = g.opposite(primero, e);
			if (!aux.getElement().getVisited()) {
				sp.push(e);
				noEnd = pathDFS(g, aux, ultimo, sp);
				if (noEnd)
					sp.pop();
			}
		}
		return noEnd;
	}

	/******************************************************************************
	 *
	 * Method name: espiaRapido
	 *
	 * Description of the Method: Busca la ruta más corta entre el aeropuerto origen
	 * y destino Calling arguments: Graph gr, ArrayList vertices, Vertex primero,
	 * Vertex ultimo.
	 *
	 * Return value: Void.
	 *
	 *******************************************************************************/
	public void espiaRapido(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr,
			ArrayList<Vertex<ElementoDecorado<Aeropuerto>>> vertices, Vertex<ElementoDecorado<Aeropuerto>> primero,
			Vertex<ElementoDecorado<Aeropuerto>> ultimo) {

		boolean aerCorrecto = false;
		Scanner teclado = new Scanner(System.in);
		Queue<Vertex<ElementoDecorado<Aeropuerto>>> traversal = new LinkedList(); // Cola que usaremos en el DFS
		Vertex<ElementoDecorado<Aeropuerto>> e;
		Vertex<ElementoDecorado<Aeropuerto>> aux;

		for (int i = 1; i < gr.getN(); i++) {
			aux = gr.getVertex(vertices.get(i).getID());
			aux.getElement().setVisited(false);
		}
		traversal.clear();
		BFSRecur(gr, primero, ultimo, traversal); // Llamada al método
		String mensaje;
		if (ultimo.getElement().getVisited()) { // Muestra un mensaje cuando visitas llegas a tu destino
			mensaje = "\n La ruta más corta entre " + primero.getElement().getElement().getIATA() + " y "
					+ ultimo.getElement().getElement().getIATA() + " es: \n";
			for (int i = 0; i < traversal.size(); i++) {
				e = traversal.poll();
				mensaje += e.getID() + " ==>  ";
			}

			mensaje += ultimo.getID();

		} else {
			mensaje = "No existe ruta posible.";
		}
		System.out.println(mensaje);

	}

	public String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
