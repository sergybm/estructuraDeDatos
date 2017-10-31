
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Ejercicio2 {
	
	
	public static void main(String args[]) {
		
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre	
		LinkedBlockingQueue<Caja> colacaja0=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 0.
		LinkedBlockingQueue<Caja> colacaja1=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 1.
		LinkedBlockingQueue<Caja> colacaja2=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 2.
		LinkedBlockingQueue<Caja> colacaja3=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 3.
		LinkedBlockingQueue<Caja> colacaja4=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 4.
		LinkedBlockingQueue<Caja> colacaja5=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 5.
		LinkedBlockingQueue<Caja> colacaja6=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 6.
		LinkedBlockingQueue<Caja> colacaja7=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 7.
		LinkedBlockingQueue<Caja> colacaja8=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 8.
		LinkedBlockingQueue<Caja> colacaja9=new LinkedBlockingQueue<Caja>(); // La cola que se genera en la caja 9.
		
		int sumadorTiempo = 1;
		PriorityQueue<Cliente> colaclientes = new PriorityQueue<Cliente>(); // La cola de los clientes
		Cliente cliente[] = new Cliente[100]; // Vector con todos los clientes.
		
		System.out.println("Por orden de llegada:\n"); // Imprime los clientes con sus productos y el tiempo en el que llegan.
		for(int i=0; i<100; i++){
			cliente[i] = new Cliente(tiempoTranscurrido); // Creamos el cliente y lo metemos en el array.
			colaclientes.add(cliente[i]); // Metemos cada cliente en la cola de los clientes
	           tiempoTranscurrido=tiempoTranscurrido+30; // Tiempo de llegada de los clientes cada 30 segundos.
	            System.out.println("Cliente " + i +": " +cliente[i].toString());
	           
		}       
        System.out.println("\n\nPor orden de salida:\n");        
			pasandoPorCaja(colaclientes, sumadorTiempo, sumadorTiempo); // Cliente pasa por caja y sale el tiempo.
		} 

	public static void pasandoPorCaja(Queue<Cliente> colaclientes,int sumadorTiempo, int tiempoTranscurrido) {
		int tiempoAtendiendo = 0, tiempoProducto = 0;

		while (!colaclientes.isEmpty()) {	
			Cliente clienteEnCaja;
			clienteEnCaja = colaclientes.poll();
			int eficiencia = 0;
			for (int i = clienteEnCaja.getProductos(); i > 0; i--) {

				eficiencia = caja.getEficienciaDespacho();

				while (tiempoProducto <= eficiencia) {
					tiempoProducto += sumadorTiempo;
				}
				tiempoAtendiendo += tiempoProducto;
				tiempoProducto = 0;
			}
			System.out.println("El tiempo transcurrido con el cliente ha sido de:" + tiempoAtendiendo);
			tiempoAtendiendo = 0;

		}
	}
}
