import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Ejercicio2 {

	public static PriorityQueue<Caja> CrearCajas() { /* / Crea la lista de cajas y mete las 10 cajas / */
		PriorityQueue<Caja> colacajas = new PriorityQueue<Caja>(); // Cola para meter las cajas

		for (int i = 0; i < 10; i++) {
			colacajas.add(new Caja(i)); // Creamos las 10 cajas y la añadimos
		}
		return colacajas;
	}

	public static Deque<Cliente> CrearClientes() { // Crea los clientes y los mete en una cola doble 
		Deque<Cliente> colaclientes = new LinkedBlockingDeque<Cliente>();
		int tiempoLlegada = 0;
		int contadorViejos=(int)Math.random()*(100-0+1)+100; //Valor entre 0 y 100, ambos incluidos. 
		
		for (int i=0;i<contadorViejos;i++) {
		colaclientes.add(new ClienteViejo(tiempoLlegada)); // Añado los clientes viejos que se hayan generado aleatoriamente a la cola.
		}
		
		for (int i = 0; i < (100-contadorViejos); i++) { // La cola de los clientes iniciales, los 100 clientes.
			colaclientes.add(new Cliente(tiempoLlegada)); // Metemos los clientes en la cola de clientes.
		}
		return colaclientes;
	}

	public static void MeterClientes() {
		
		PriorityQueue<Caja> colacajas = CrearCajas(); /// Metemos en la cola colacajas las 10 cajas creadas;
		Deque<Cliente> colaclientes = CrearClientes(); // Cola doble de clientes
		int size=colaclientes.size();
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre , cada 30 segundos entra un nuevo cliente
		int numClientesCadaCaja = 0;
		int numProductosTotal = 0;
		

		for (int i = 0; i < size; i++) {

			Cliente cli = colaclientes.poll(); // Saco cliente de la cola
			Caja caja = colacajas.poll(); // Saco una caja de la cola de cajas, que es en teoría, la caja con la cola mas corta y donde debe ir el cliente que queremos meter.
			
			try {
				// Thread.sleep(30000); // Espera 30 segundos para meter a un cliente nuevo
				if (cli instanceof ClienteViejo) {
					tiempoTranscurrido = tiempoTranscurrido + 30; // Al meter un cliente añadimos al tiempo transcurrido 30 segundos
					cli.setTiempoLlegada(tiempoTranscurrido);
					caja.MeterClienteEnCola(cli);
					colacajas.add(caja); // Vuelvo a meter la caja a la cola de cajas.
				 	
					
				} else {
					tiempoTranscurrido = tiempoTranscurrido + 30; // Al meter un cliente añadimos al tiempo transcurrido 30 segundos.
					cli.setTiempoLlegada(tiempoTranscurrido);
					caja.MeterClienteEnCola(cli);
					colacajas.add(caja); // Vuelvo a meter la caja a la cola de cajas.
				}
				
				

			} catch (Exception ex) {
				System.out.println("Error en la creación.");
			}
		}

		
			
	while (!colacajas.isEmpty()) {
				System.out.println("Caja " + colacajas.peek().getID() + " contiene:\n" + colacajas.peek().getCola()); // Imprimo la caja y los clientes que tiene
			for(int j=0;j<numClientesCadaCaja;j++) {
					numProductosTotal=numProductosTotal+colacajas.peek().getCola().poll().getProductos();  //Saco los productos totales de una caja 
				}
				int tiempoDespacho=numProductosTotal*(colacajas.peek().getEficienciaDespacho()); // Calculo el tiempo que tardo en despachar en la caja y se lo asigno.
				colacajas.peek().setTiempoTrabajo(tiempoDespacho); 
		
				colacajas.poll();
							
		}

		
		} /// FIN DEL MÉTODO MeterClientes
		
	

	

	public static void main(String args[]) {

		System.out.println("Los clientes llegan a la cola:\n"); // Imprime los clientes con sus productos y el tiempo en el que llegan.
		System.out.println(CrearClientes().toString());

		System.out.println("\nLos clientes entran a las cajas:\n");
		MeterClientes();
		
		System.out.println("");
	}

}
