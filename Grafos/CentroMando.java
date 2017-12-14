import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.Vertex;

public class CentroMando {
	final static String aeropuertos = "airports.txt";
	final static String rutas = "routes.txt";
	private List<Aeropuerto> listAeropuertos = new ArrayList<Aeropuerto>();
	private List<Ruta> listRutas = new ArrayList<Ruta>();

	public List<Aeropuerto> leerAeropuertos() throws IOException {
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
			iOrigen = listRutas.get(i).getCodeOrigen(); /// iOrigen = iATA aeropuerto de origen
			iDestino = listRutas.get(i).getCodeDestino(); /// iDestino = iATA aeropuerto de destino
			// System.out.println(listrutas.get(i));
			for (j = 0; j < listRutas.size(); j++) {
				jOrigen = listRutas.get(j).getCodeOrigen(); // jOrigen = iATA aeropuerto de origen
				jDestino = listRutas.get(j).getCodeDestino(); // jDestino = iATA aeropuerto de destino
				// System.out.println(listrutas.get(j));
				if (iDestino.equals(jOrigen) && iOrigen.equals(jDestino)) {
					listRutas.remove(listRutas.get(j));
				}

			}
		}
		return listRutas;
	}

	public void createGraph(Graph <ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr, List<Aeropuerto> listAeropuertos, List<Ruta> listRutas) throws IOException {
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

	} // FIN

	/*
	 * / public static void mostrarGrafo(Graph gr){
	 * 
	 * int contadorAeropuertos=0; int contadorRutas=0;
	 * 
	 * 
	 * System.out.println("\n El grafo tiene: " + gr.getN() + " Vertices");
	 * System.out.println("\n El grafo tiene: " + gr.getM() + " Aristas");
	 * 
	 * System.out.println("\n Conexiones del grafo:");
	 * 
	 * 
	 * Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
	 * 
	 * while(itr.hasNext()){//Dos While anidados para mostrar cada aeropuerto del
	 * grafo y sus adyacentes. Vertex<ElementoDecorado<Aeropuerto>> a = itr.next();
	 * 
	 * Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr1 = gr.getVertices();
	 * 
	 * System.out.print("Aeropuerto  " +a.getElement().getElement().getIATA()
	 * +"  -----------> "); contadorAeropuertos++; while(itr1.hasNext()){ //Por cada
	 * aeropuerto recorremos los vértices e imprimimos los adyacentes.
	 * Vertex<ElementoDecorado<Aeropuerto>> a2 = itr1.next();
	 * if(gr.areAdjacent(a,a2) &&
	 * a.getElement().getElement().getIATA()!=a2.getElement().getElement().getIATA()
	 * ){ System.out.print(a2.getElement().getElement().IATA+","); contadorRutas++;
	 * } } System.out.println(); }
	 * 
	 * System.out.println();
	 * 
	 * System.out.println(contadorAeropuertos); System.out.println(contadorRutas); }
	 * /
	 */

	public void mostrarGrafo(Graph <ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {
		Vertex[] v;
		Iterator<Edge<ElementoDecorado<Ruta>>> ite = gr.getEdges();
		System.out.println("Conexiones del grafo");
		while (ite.hasNext()) {
			v = gr.endVertices(ite.next());
			System.out.print(v[0].getElement().toString());
			System.out.print("--->" + v[1].getElement().toString() + "//");
			System.out.println();
		}
	}

	public void verAeropuertos(Graph <ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> a;
		while (itr.hasNext()) {
			a = itr.next();
			System.out.println(a.getElement().getElement().toString());

		}

	}

	public void GenerarInforme(Graph <ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr) {

		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		String masNorte = null;
		String masOeste = null;
		Aeropuerto masConexiones = null;
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

		altitudMedia = (sumaAltitud / gr.getN()); // gr.getN me da los vertices que tengo, en este caso los aeropuertos

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
				+ " conexiones:" + conexiones2);
		System.out.println("La altitud media de todos los aeropuertos es: " + altitudMedia + " pies");

	}
	
	/*********************************************************************
	    * Method name: BFSRecur
	    *
	    * Description of the Method: Implementa Breadth-first search (BFS), que es una técnica para recorrer el grafo.
		*
	    * Calling arguments: Graph gr, vertex v, vertex ultimo, queue traversal
	    *********************************************************************/
	public static void BFSRecur (Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr, Vertex<ElementoDecorado<Aeropuerto>> v,Vertex<ElementoDecorado<Aeropuerto>> ultimo,Queue<Vertex<ElementoDecorado<Aeropuerto>>> traversal){
		Vertex<ElementoDecorado<Aeropuerto>> u = null;  			// Elemento decorado del siguiente vertice al que miramos su parametro visitado
         Edge<ElementoDecorado<Ruta>> e;						// Arista que se mira en cada iteración
         Iterator<Edge<ElementoDecorado<Ruta>>> it;    //iterator para iterar las aristas incidentes de v
         Vertex<ElementoDecorado<Aeropuerto>> aux = null;
         
        
         v.getElement().setVisited(true);
         traversal.offer(v);
         it = gr.incidentEdges(v);
         while(it.hasNext()){
             e = it.next();
             u = gr.opposite(v, e);
             if(u.equals(ultimo)) {
                 ultimo.getElement().setVisited(true);
                 traversal.offer(u);
             }else{
             	if(gr.areAdjacent(u,ultimo)){
             		aux=u;
             	}
             }
         }
         if((!aux.getElement().getVisited()) && (ultimo.getElement().getVisited() == false)){
         	BFSRecur(gr, aux, ultimo,traversal);
         }
 }
	
	public void seeWay(Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>>  gr,List<Aeropuerto> a){
		boolean flag=false;
		Vertex<ElementoDecorado<Aeropuerto>> primero = null;  						 // Vertice cuyo elemento contiene al primer preso de la busqueda
		Vertex<ElementoDecorado<Aeropuerto>> ultimo = null;  						 // Vertice cuyo elemento contiene al segundo preso de la busqueda
        Queue <Vertex<ElementoDecorado<Aeropuerto>>> traversal = new LinkedList();   // Cola que usaremos en el DFS  					 
        Vertex<ElementoDecorado<Aeropuerto>> e;
		Vertex<ElementoDecorado<Aeropuerto>> aux; // Vértice cual parametro visitado de su elemento correspondiente iremos viendo
		String conti="y";										 // Nos indica si se quiere seguir con el bucle o no, en caso de ser == "y" se sigue, en caso contrario no.
        
        
       
        	
        	for (int i=1;i<gr.getN();i++) {
	        	 aux=gr.getVertex(a.get(i).getIATA());
	        	 aux.getElement().setVisited(false);
	        }
        	
		do{
			System.out.println("El primer avión que coge es en :");
			String v1 = a.get(0).getIATA(); /// Hay que coger el aeropuerto de inicio del ladrón.
			for(int i=0;i<gr.getN();i++){
				aux=gr.getVertex(a.get(i).getIATA());
				if(aux.getElement().getElement().getIATA().contentEquals(v1)){
					flag=true;
					primero=gr.getVertex(v1);
				}
			}
			if(flag == false){
				System.out.printf("\"El aeropuerto %s no existe.\n",v1);
			}
		}while(flag!=true);
		flag=false;
		do{
			System.out.println("El aeropuerto de destino final es: ");
			String v2 = a.get(20).getIATA(); /// Hay que coger el aeropuerto destino final del ladrón.
			for(int i=0;i<gr.getN();i++){
				aux=gr.getVertex(a.get(i).getIATA());
				if(aux.getElement().getElement().getIATA().contentEquals(v2)){
					flag=true;
					ultimo=gr.getVertex(v2);
				}
			}
			if(flag == false){
				System.out.printf("El aeropuerto %s no existe.\n",v2);
			}
		}while(flag!=true);
		flag=false;
		
		traversal.clear();
        BFSRecur(gr, primero, ultimo,traversal);
		
        String mensaje;
        if (ultimo.getElement().getVisited()){
            mensaje="El camino es: \n";
            for (int i = 0; i<traversal.size();i++){
            	e=traversal.poll();
            	mensaje+=e.getID()+" ";
            }
            
            mensaje+=ultimo.getID();
            
        } else {
            mensaje="No existe camino posible";
        }
       System.out.println(mensaje);
        
	

        }
	
	public String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
