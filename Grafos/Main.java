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
		

		Graph gr = new TreeMapGraph<>(); // Crea el grafo
		Queue<ElementoDecorado> traversal;

		Vertex<ElementoDecorado> startVertex;
		String startVID;

		createGraph(gr); // rellena el grafo
		mostrarGrafo(gr); // muestra el grafo
	//	printGraph(gr);
		GenerarInforme(gr);

		
		
		startVID = "1";
		startVertex = gr.getVertex(startVID);
		if (startVertex == null) {
			System.out.println("The vertex does not exist");
		} else {
			traversal = DFSIter(gr, startVertex);
		//	System.out.println("Nonrecursive DFS traversal with the node " + startVID + " as starting vertex\n");
			while (!traversal.isEmpty())
				// System.out.println("\n Elemento: "+ ++cont);
				//System.out.print(
				traversal.remove().getElement().toString();
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

				Aeropuerto ap1 = new Aeropuerto( (cadena[0]),quitarComillas(cadena[1]),quitarComillas(cadena[2]) , quitarComillas(cadena[3]),
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
		String iAerolinea,jAerolinea;

		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			Ruta ruta = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));
			
			// Añade a la lista las rutas que tienen un iATA que coinciden con los de los aeropuertos y por lo tanto con los paises
			for (i=0;i<list.size();i++) {
				if ((cadena[2]).equals( list.get(i).getIATA())) {
					for (j=0;j<list.size();j++) {
						if ((cadena[4]).equals(list.get(j).getIATA())) {
							listrutas.add(ruta);
						}
					}
				}	
			}
		}
	b.close();
		
	
		
	
		// Elimino los vuelos dobles, es decir, los que son de varias compañias pero tienen la misma ruta
		for(i=0;i<listrutas.size();i++) {
			iAerolinea=listrutas.get(i).getAerolinea();
	
			iOrigen=listrutas.get(i).getCodeOrigen(); ///iOrigen = iATA aeropuerto de origen
			iDestino=listrutas.get(i).getCodeDestino(); /// iDestino = iATA aeropuerto de destino
			for (j=0;j<listrutas.size();j++) {			
				jAerolinea=listrutas.get(j).getAerolinea();
				jOrigen=listrutas.get(j).getCodeOrigen(); // jOrigen = iATA aeropuerto de origen
				jDestino=listrutas.get(j).getCodeDestino(); // jDestino = iATA aeropuerto de destino
				if (iOrigen.equals(jOrigen) && iDestino.equals(jDestino) && !iAerolinea.equals(jAerolinea)){					
					listrutas.remove(listrutas.get(j));					
				}
			}
		}
	
		/// Eliminar las rutas inversas 
	
		for(i=0;i<listrutas.size();i++) {
				iOrigen=listrutas.get(i).getCodeOrigen(); ///iOrigen = iATA aeropuerto de origen
				iDestino=listrutas.get(i).getCodeDestino(); /// iDestino = iATA aeropuerto de destino
			//	System.out.println(listrutas.get(i));
				for (j=0;j<listrutas.size();j++) {			
					jOrigen=listrutas.get(j).getCodeOrigen(); // jOrigen = iATA aeropuerto de origen
					jDestino=listrutas.get(j).getCodeDestino(); // jDestino = iATA aeropuerto de destino
				//	System.out.println(listrutas.get(j));
						if (iDestino.equals(jOrigen) && iOrigen.equals(jDestino)){
							listrutas.remove(listrutas.get(j));
						}
						
				}
			} 
	

		// Creamos el grafo
		ElementoDecorado<Aeropuerto> e1 = null;
		ElementoDecorado<Aeropuerto> e2 = null;	
		int o,p;
		Aeropuerto b1,b2;
		for (i=0;i<listrutas.size();i++) {					
			for (o=0;o<list.size();o++) {
				b1=list.get(o);
				if (b1.getIATA().equals(listrutas.get(i).getCodeOrigen())) {
					e1 = new ElementoDecorado<Aeropuerto>(b1.getIATA(), b1);	// Elemento decorado Origen 		
					for (p=0;p<list.size();p++) {
						b2=list.get(p);
						if (b2.getIATA().equals(listrutas.get(i).getCodeDestino())){					
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
		
/*/
	public static void mostrarGrafo(Graph gr){
		
		int contadorAeropuertos=0;
		int contadorRutas=0;
		
		
		System.out.println("\n El grafo tiene: " + gr.getN() + " Vertices");
		System.out.println("\n El grafo tiene: " + gr.getM() + " Aristas");
		
		System.out.println("\n Conexiones del grafo:");
		
	
		Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		
		while(itr.hasNext()){//Dos While anidados para mostrar cada aeropuerto del grafo  y sus adyacentes.
			Vertex<ElementoDecorado<Aeropuerto>> a = itr.next(); 
			
			Iterator <Vertex<ElementoDecorado<Aeropuerto>>> itr1 = gr.getVertices();
			
			System.out.print("Aeropuerto  " +a.getElement().getElement().getIATA() +"  -----------> ");
			contadorAeropuertos++;
				while(itr1.hasNext()){ //Por cada aeropuerto recorremos los vértices e imprimimos los adyacentes.
					Vertex<ElementoDecorado<Aeropuerto>> a2 = itr1.next(); 
					if(gr.areAdjacent(a,a2) && a.getElement().getElement().getIATA()!=a2.getElement().getElement().getIATA()){						
						System.out.print(a2.getElement().getElement().IATA+",");
						contadorRutas++;
					}
				}
				System.out.println();
			}
		
		System.out.println();
		
		System.out.println(contadorAeropuertos);
		System.out.println(contadorRutas);
	}
	/*/

	 public static void mostrarGrafo(Graph gr){
		    Vertex [] v;
		    Iterator<Edge> ite = gr.getEdges();
		    System.out.println("Conexiones del grafo");
		    while (ite.hasNext()) {
		      v = gr.endVertices(ite.next());
		      System.out.print(v[0].getElement().toString());
		      System.out.print("--->" + v[1].getElement().toString());
		      System.out.println();
		    }
		    
		  }
	
	public static void GenerarInforme(Graph gr) {
		
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr = gr.getVertices();
		String masNorte = null;
		String masOeste = null;
		Aeropuerto masConexiones = null;
		double altitudMedia = 0;
		double aux = 0;
		double sumaAltitud = 0;
		double aux2=0;
		String masNorteCiudad = null;
		String masOesteCiudad = null;
		Iterator aux3;
		Vertex<ElementoDecorado<Aeropuerto>> a;
		
		int conexiones=0;
		
		while (itr.hasNext()) {		
			a = itr.next();
			
			/*/ Aeropuerto mas al norte /*/	
			if (Double.parseDouble(a.getElement().getElement().getLatitud())<=90) {
				if(aux == 0) {
					aux = Double.parseDouble(a.getElement().getElement().getLatitud());
				}else if (Double.parseDouble(a.getElement().getElement().getLatitud()) > aux) {
					aux=(Double.parseDouble(a.getElement().getElement().getLatitud()));
					masNorte=a.getElement().getElement().getNombre();
					masNorteCiudad=a.getElement().getElement().getCiudad();
				}
			}
		}
		  /*/ Altitud media de los paises /*/
		Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itrM = gr.getVertices();
		Vertex<ElementoDecorado<Aeropuerto>> m;
		while (itrM.hasNext()) {		
			m = itrM.next();
			sumaAltitud=Double.parseDouble(m.getElement().getElement().getAltitud()) + sumaAltitud;
		}
		
		altitudMedia=(sumaAltitud/gr.getN()); // gr.getN me da los vertices que tengo, en este caso los aeropuertos
		
			/*/ Aeropuerto mas al oeste /*/
			Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr1 = gr.getVertices();
			Vertex<ElementoDecorado<Aeropuerto>> a2;
			while (itr1.hasNext()) {		
				a2 = itr1.next();
				if (Double.parseDouble(a2.getElement().getElement().getLongitud())<=180) {
					if(aux2 == 0) {
						aux2 = Double.parseDouble(a2.getElement().getElement().getLongitud());
					}else if (Double.parseDouble(a2.getElement().getElement().getLongitud()) < aux2) {
					aux=(Double.parseDouble(a2.getElement().getElement().getLongitud()));
					masOeste=a2.getElement().getElement().getNombre();
					masOesteCiudad = a2.getElement().getElement().getCiudad();
					}
				}
			}
			
			/*/ Aeropuerto con mas conexiones /*/
			Iterator<Vertex<ElementoDecorado<Aeropuerto>>> itr2 = gr.getVertices();
			Vertex<ElementoDecorado<Aeropuerto>> a3, mayor = null;
			int conexiones2 = 0;//Auxi
			while (itr2.hasNext()) {		
					a3 = itr2.next();
					aux3=gr.incidentEdges(a3);
						while (aux3.hasNext()) {
							aux3.next();
							conexiones++;
						}
						if(conexiones2 ==0) {
							conexiones2 = conexiones;
							mayor = a3;
						}else if(conexiones>conexiones2) {
							conexiones2 = conexiones;
							mayor = a3;
						}
						
						conexiones = 0;
						
						
			}
		
		
		
		
		
	
		
		
		System.out.println("El aeropuerto más al norte es: " + masNorte + "  localizado en " + masNorteCiudad); 
		System.out.println("El aeropuerto más al oeste es: " + masOeste+ "  localizado en " + masOesteCiudad);
		System.out.println("El aeropuerto que mas conexiones tiene es: "  + mayor.getElement().getElement().getNombre() + " conexiones:"+conexiones2);
		System.out.println("La altitud media de todos los aeropuertos es: " + altitudMedia + " pies");
	
		
	}

	public static String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
