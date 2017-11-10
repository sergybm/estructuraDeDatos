/*********************************************************************
*
* Class Name: Ejercicio2
* Author/s name: Raúl Ruiz del valle Álvarez
* 						 Sergio Barrios Martínez
* 						 Lucía Alfonso García
* Release/Creation date: 12/11/2017
* Class version: Final
* Class description: Este es la clase donde se encuentra el main y los métodos que permiten gestionar los clientes de un supermercado, los clientes elegirán una caja (la que menos clientes tenga).
* Se crean las 10 cajas y los 100 clientes, de los cuales algunos, aleatoriamente, pueden ser viejos, estos pueden colarse en la fila de cajas.
*
**********************************************************************
*/
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Ejercicio2 {
	
	
	/*********************************************************************
	* Method name: CrearCajas
	
	* Description of the Method: Se crean las 10 cajas y se meten en una priority queue que contiene esas 10 cajas.
	* Return value: devuelve la priority queue con las 10 cajas.
	*********************************************************************/
	public static PriorityQueue<Caja> CrearCajas() { // Crea la lista de cajas y mete las 10 cajas 
		PriorityQueue<Caja> colacajas = new PriorityQueue<Caja>(); // Cola para meter las cajas
		
		int cajasSupermercado = 10; // Numero de cajas que hay en el supermercado
		
		for (int i = 0; i < cajasSupermercado; i++) {
			colacajas.add(new Caja(i)); // Creamos las 10 cajas y las añadimos.
		}
		return colacajas;
	}
	
	
	/*********************************************************************
	* Method name: CrearClientes
	
	* Description of the Method: El metodo crea los 100 clientes, algunos de llos pueden ser viejos por lo que creamos antes un número aleatorio de clientes viejo y luego creamos los restantes.
	* Return value: Devuelve una cola doble que es nuestra cola de clientes inicial, donde se encuentran los 100.
	*********************************************************************/

	public static Deque<Cliente> CrearClientes() { 
		Deque<Cliente> colaclientes = new LinkedBlockingDeque<Cliente>();
		int tiempoLlegada = 0; // El tiempo en el que llegan a la cola inicial es el 0.
	
		int clientesSupermercado = 100 ; // Los clientes que van al supermercado
		
		int contadorViejos=(int)(Math.random()*clientesSupermercado+1);  //Valor entre 0 y 100. 
		
		System.out.println("Hay " + contadorViejos + " clientes viejos que se han colado\n");
		
		for (int i=0;i<contadorViejos;i++) {
		colaclientes.add(new ClienteViejo(tiempoLlegada)); // Añado los clientes viejos que se hayan generado aleatoriamente a la cola.
		}
		
		for (int i = 0; i < (clientesSupermercado-contadorViejos); i++) { // La cola de los clientes iniciales, son 100 - los clientes viejos que existan.
			colaclientes.add(new Cliente(tiempoLlegada)); // Metemos los clientes en la cola de clientes.
		}
		System.out.println("Cola de clientes inicial:\n" + "\n" +colaclientes.toString());
		return colaclientes;
	}
	/*********************************************************************
	* Method name: MeterClientes
	
	* Description of the Method: En este metodo se cogen los 100 clientes y se meten en las cajas, para hacer eso miramos si el cliente que sacamos de la cola de clientes inicial es un cliente viejo o es un cliente normal,
	* si es un cliente viejo se mete el primero en la cola de la caja correspondiente, y de no ser así se le mete normalmente, finalmente se imprimen las cajas, mientras haya una caja en la cola, se imprime y después
	*  también el tiempo que ha estado trabajando la caja que más ha trabajado junto con su id.
	* 
	*
	*********************************************************************/
	public static void MeterClientes() {
		
		PriorityQueue<Caja> colacajas = CrearCajas(); /// Metemos en la cola cola de cajas las 10 cajas creadas
		Deque<Cliente> colaclientes = CrearClientes(); // Cola doble de clientes
		
		int tamaño=colaclientes.size(); // El tamño de la cola de clientes. 
		int tiempoTranscurrido = 0; // Este será el tiempo que transcurre , cada 30 segundos entrará un nuevo cliente.
		int numClientesCadaCaja = 0; 
		int numProductosTotal = 0; // Para guardar los productos totales que la caja atiende.
		int mtt =0; // La utilizo para calcular la caja con mayor tiempo de trabajo
		Caja mayorTiempoTrabajo = null; // Guardo la caja con mayor tiempo de trabajo.
		
		System.out.println("\nMetiendo clientes a las cajas... \n");
		for (int i = 0; i < tamaño; i++) {

			Cliente cli = colaclientes.poll(); // Saco cliente de la cola inicial de clientes.
			Caja caja = colacajas.poll(); // Saco una caja de la cola de cajas, que será la caja con la cola mas corta y donde debe ir el cliente que queremos meter.
			
			try {
				Thread.sleep(30); // Espera 30 milisegundos para meter a un cliente nuevo (para que se ejecute más rapido, son 30 segundos realmente en el enunciado)
				if (cli instanceof ClienteViejo) {
					tiempoTranscurrido = tiempoTranscurrido + 30; // Al meter un cliente añadimos al tiempo transcurrido 30 segundos
					cli.setTiempoLlegada(tiempoTranscurrido);
					caja.getCola().addFirst(cli); // Metemos al cliente viejo el primero.
					colacajas.add(caja); // Vuelvo a meter la caja a la cola de cajas.
				 	
					
				} else {
					tiempoTranscurrido = tiempoTranscurrido + 30;
					cli.setTiempoLlegada(tiempoTranscurrido);
					caja.getCola().add(cli);
					colacajas.add(caja); 
				}
				
				
			} catch (Exception ex) {
				System.out.println("Error al introducir los clientes a las cajas.");
			}
		}	
			
	while (!colacajas.isEmpty()) {
				
				System.out.println("Caja " + colacajas.peek().getID() + " contiene:\n" + colacajas.peek().getCola() +"\n"); // Imprimo la caja y los clientes que tiene.
				numClientesCadaCaja=colacajas.peek().getCola().size();
			
				for(int j=0;j<numClientesCadaCaja;j++) {
					numProductosTotal=numProductosTotal+colacajas.peek().getCola().poll().getProductos();  //Saco los productos totales de una caja para generar el tiempo de trabajo de la caja.
				}
				
				int tiempoDespacho=numProductosTotal*(colacajas.peek().getEficienciaDespacho()); // Calculo el tiempo que tardo en despachar en la caja y se lo asigno al tiempo trabajado de la caja.
				colacajas.peek().setTiempoTrabajo(tiempoDespacho); 
				
				if (colacajas.peek().getTiempoTrabajo()>mtt) { /// Calculo la caja con mayor tiempo de trabajo.
					mtt=colacajas.peek().getTiempoTrabajo();
					mayorTiempoTrabajo=colacajas.peek();
				}
				
				colacajas.poll(); // Una vez imprimo la caja la saco de la cola.
			
		}
	
		System.out.println("La caja con mas tiempo de trabajo es la caja: "+ mayorTiempoTrabajo.getID() + " con un tiempo de trabajo de " + mayorTiempoTrabajo.getTiempoTrabajo()+ " 'segundos' ");
		
		} 
		
	
	/*********************************************************************
	* Method name: main
	
	* Description of the Method: El main nos lleva al metodo MeterClientes(), que realizará las operaciones indicadas.
	*********************************************************************/
	

	public static void main(String args[]) {

		System.out.println("\nEjercicio de colas - Supermercado:\n");
		MeterClientes();
		
	}

}
