import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
// import java.util.Comparator;

public class Ejercicio2 {
	
	public static PriorityQueue<Caja> CrearCajas(){ /*/ Crea la lista de cajas y mete las 10 cajas /*/
		PriorityQueue<Caja> colacajas=new PriorityQueue<Caja>(); // /// Cola para meter las cajas
	
		for (int i=0; i<10;i++) {
			colacajas.add(new Caja(i)); // Creamos las 10 cajas y la añadimos
		}
		return colacajas;
		}
	

	public static Deque<Cliente> CrearClientes(){ 	/*/ Crea los clientes y los mete en una cola doble  /*/
		Deque<Cliente> colaclientes = new LinkedBlockingDeque<Cliente>();
		int tiempoLlegada = 0;
		for(int i=0; i<100; i++){ // La cola de los clientes iniciales, los 100 clientes.
			colaclientes.add(new Cliente(tiempoLlegada)); // Metemos los clientes en la cola de clientes
	        tiempoLlegada=tiempoLlegada+30; //El tiempo de llegada del proximo cliente será en 30 segundos.      
		}
		return colaclientes;
	}
	
	
	
	public static void MeterClientes() {
		PriorityQueue<Caja> colacajas=CrearCajas(); /// Metemos en la cola colacajas las 10 cajas creadas;
		Deque<Cliente> colaclientes = CrearClientes();    
		
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre	, cada 30 segundos entra un nuevo cliente
	
		for (int i = 0; i < colaclientes.size();i++) {
			
			Cliente cli = colaclientes.poll(); // Saco cliente de la cola
			Caja caja =colacajas.peek(); // Miro cual es la caja en la peek, que es en teoría, la caja con la cola mas pequeña y donde debe ir el cliente que queremos meter. 
			caja=colacajas.poll();
			
			try {
				if (cli instanceof ClienteViejo) {
					caja.getCola().addFirst(cli); // Meto el cliente viejo en la caja
						tiempoTranscurrido=tiempoTranscurrido+30; // Al meter un cliente añadimos al tiempo transcurrido 30 segundos
				}else {
					tiempoTranscurrido=+30; // Al meter un cliente añadimos al tiempo transcurrido 30 segundos.
					caja.getCola().add(cli);	// Meto el cliente normal en la caja.
			}
				
				colacajas.add(caja); // Actualizamos la coja de cajas con la nueva información
				
		}catch(Exception ex) {
			System.out.println("Error en la creación.");
		}
			
		}

	}
	

	
	
	
	public static void main(String args[]) {
		
		System.out.println("Por orden de llegada:\n"); // Imprime los clientes con sus productos y el tiempo en el que llegan.
		
		 System.out.println(CrearClientes().toString()); 
		 
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
	