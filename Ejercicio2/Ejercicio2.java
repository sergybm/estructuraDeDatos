import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Ejercicio2 {
	
	
	public static void main(String args[]) {
		
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre	.
		PriorityQueue<Caja> colacajas=new PriorityQueue<Caja>(); // La cola que se genera en las cajas.
		int sumadorTiempo = 1;
		Queue<Cliente> colaclientes = new LinkedBlockingQueue<Cliente>(); // La cola de los clientes.
		Cliente cliente[] = new Cliente[100]; // Vector con todos los clientes.
		Caja cajas[] = new Caja[10]; // Vector con todos los clientes.
		
		for (int i=0; i<10;i++) {
			colacajas.add(new Caja(i)); // Creamos nuestras 10 colas para nuestras cajas.
		}
		
		
		System.out.println("Por orden de llegada:\n"); // Imprime los clientes con sus productos y el tiempo en el que llegan.
		for(int i=0; i<100; i++){
			cliente[i] = new Cliente(tiempoTranscurrido); // Creamos el cliente y lo metemos en el array.
			colaclientes.add(cliente[i]); // Metemos cada cliente en la cola de los clientes.
	           tiempoTranscurrido=tiempoTranscurrido+30; // Los clientes entran cada 30 segundos.
	            System.out.println("Cliente " + i +": " +cliente[i].toString());
	           
		}       
        System.out.println("\n\nPor orden de salida:\n");        
			pasandoPorCaja(colaclientes, colacajas, sumadorTiempo, sumadorTiempo); // Cliente pasa por caja y sale el tiempo.
		} 

	public static void pasandoPorCaja(Queue<Cliente> colaclientes,PriorityQueue<Caja> colacajas,int sumadorTiempo, int tiempoTranscurrido) {
		int tiempoAtendiendo = 0, tiempoProducto = 0;

		while (!colaclientes.isEmpty()) {	
			Cliente clienteEnCaja;
			clienteEnCaja = colaclientes.poll();
			int eficiencia = 0;
			for (int i = clienteEnCaja.getProductos(); i > 0; i--) {

			

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
	