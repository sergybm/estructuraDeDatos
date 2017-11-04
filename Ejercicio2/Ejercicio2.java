import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
// import java.util.Comparator;

public class Ejercicio2 {
	
	public static PriorityQueue<Caja> CrearCajas(){
		PriorityQueue<Caja> colacajas=new PriorityQueue<Caja>(); // /// Cola para meter las cajas
	
		for (int i=0; i<10;i++) {
			colacajas.add(new Caja(i)); // Añadimos 10 cajas a nuestra cola de cajas
		
		}
		return colacajas;
		}
	public static void MeterClientes() {
		PriorityQueue<Caja> colacajas=CrearCajas();
		Deque<Cliente> colaclientes = CrearClientes();    
		
		for (int i = 0; i < colaclientes.size();i++) {
			Cliente cli = colaclientes.poll();
			Caja caja =colacajas.poll();
			try {
				if (cli instanceof ClienteViejo) {
					caja.getCola().addFirst(cli);
						colacajas.add(caja);
				}else {
					caja.getCola().add(cli);
					colacajas.add(caja);				
			}
		}catch(Exception ex) {
			System.out.println("Error en la creación.");
		}
		
		}
	       
	     
	        
	}
	
	public static Deque<Cliente> CrearClientes(){
		Deque<Cliente> colaclientes = new LinkedBlockingDeque<Cliente>();
		int tiempoTranscurrido = 0;
		for(int i=0; i<100; i++){ // La cola de los clientes iniciales, los 100 clientes.
			colaclientes.add(new Cliente(tiempoTranscurrido)); // Metemos los clientes en la cola de clientes
	        tiempoTranscurrido=tiempoTranscurrido+30; // Los clientes entran cada 30 segundos.        
		}
		return colaclientes;
	}
	public static void main(String args[]) {
		
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre	.
		int sumadorTiempo = 1;
		
		
		
		
		System.out.println("Por orden de llegada:\n"); // Imprime los clientes con sus productos y el tiempo en el que llegan.
		
		
		 System.out.println(CrearClientes().toString()); 
		 CrearCajas();
		
		 
        System.out.println("Por orden de salida:\n");  
        MeterClientes();
        	// pasandoPorCaja(colaclientes, colacajas, sumadorTiempo, sumadorTiempo); // Cliente pasa por caja y sale el tiempo
        }
        
		

	
	public static void pasandoPorCaja(Queue<Cliente> colaclientes,PriorityQueue<Caja> colacajas,int sumadorTiempo, int tiempoTranscurrido) {
		int tiempoAtendiendo = 0, tiempoProducto = 0;

		while (!colaclientes.isEmpty()) {	
			Caja c;
			
		/*/	Cliente clienteEnCaja;
			clienteEnCaja = colaclientes.poll();
			int eficiencia = 0;
			
			for (int i = clienteEnCaja.getProductos(); i > 0; i--) {
				while (tiempoProducto <= eficiencia) {
					tiempoProducto += sumadorTiempo;
				}
				tiempoAtendiendo += tiempoProducto;
				tiempoProducto = 0;
			} /*/
			System.out.println("El tiempo transcurrido con el cliente ha sido de:" + tiempoAtendiendo);
			tiempoAtendiendo = 0;

		}
	}

	
	/*/	static class colas implements Comparator<Caja>{ // Para comparar las cajas

	@Override
	public int compare(Caja uno, Caja dos) {
		if (uno.getCola().size() < dos.getCola().size()) {
			return -1;
		}
		if (uno.getCola().size() > dos.getCola().size() ) {
			return 1;
		}
		return 0;
	}
	
} /*/
	
	
}
	