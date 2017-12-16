/***************************************
 ******************************
 *
 * Class Name: Aeropuerto 
 * Author/s name: Lucía Alfonso García
 *  Sergio Barrios Martínez 
 *  Raul Ruiz del Valle Arevalo
 * 
 * Class description:  Clase main donde se muestra el menú del programa
 **********************************************************************
 */

//Paquetes importados
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.TreeMapGraph;
import graphsDSESIUCLM.Vertex;

public class Main {

	private static Scanner capturar;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		menu();

	}
	public static void menu() throws IOException {
		capturar = new Scanner(System.in);
		boolean condicion = true;

		
		
		System.out.println("\n BIENVENIDO AL OPERADOR DE VUELOS \n");
		Graph<ElementoDecorado<Aeropuerto>, ElementoDecorado<Ruta>> gr= new TreeMapGraph<>();
		ArrayList<Vertex<ElementoDecorado<Aeropuerto>>> vertices=new ArrayList<Vertex<ElementoDecorado<Aeropuerto>>>();
		CentroMando pc= new CentroMando();
		List <Aeropuerto> a=pc.leerAeropuertos(gr);
		List <Ruta> r=pc.leerRutas(a);
		
		
		pc.createGraph(gr, a, r,vertices);
		//Bucle que muestra el menú del programa
		do {
			System.out.println(
					"\n Seleccione una opcion : \n 1- Ver rutas.\n 2- Ver aeropuertos.\n 3- Generar informe.\n 4- Espias.\n 5- Salir");
			char x = capturar.next().charAt(0);
			if (x >= '0' && x <= '5') {
				switch (x) {
				case '1':
					pc.mostrarGrafo(gr);
					break;
				case '2':
					pc.verAeropuertos(gr);
					break;
				case '3':
					pc.GenerarInforme(gr);
					break;
				case '4':
					pc.espiaLento(gr,vertices);
					break;
				case '5':
					condicion = false;
					break;
				default:
					System.out.println("Seleccione una opcion valida.");
				}
			}
			
		} while (condicion);
		System.out.println("Saliendo...");
	}

}
