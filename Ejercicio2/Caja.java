/*********************************************************************
*
* Class Name: Caja
* Author/s name: Raúl Ruiz del valle Álvarez
* 						 Sergio Barrios Martínez
* 						 Lucía Alfonso García
* Release/Creation date: 12/11/2017
* Class version: Final
* Class description: La clase caja implementa un metodo Comparable para que la caja con menos clientes sea la elegida para introducir un nuevo cliente. 
* La eficiencia de despacho de la caja se genera automaticamente.
* Cada caja tiene su propia cola doble, donde se podran colar los clientes viejos.
*
**********************************************************************
*/
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Caja implements Comparable<Caja> {
	
	private int id;
	private int tiempoTrabajado; 
	Deque<Cliente> clientescaja = new LinkedBlockingDeque<Cliente>(); // Es una cola doble para poder colar luego a los viejecitos.

	public Caja(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setID(char iD) {
		id = iD;
	}

	public int getEficienciaDespacho() { // Se generará aleatoriamente (máximo 20 segundos/producto).
		return (int) (Math.random() * (10 - 1) + 1);
	}

	public int getTiempoTrabajo(){ // Es el agregado del tiempo que una caja ha estado trabajando.
		return  tiempoTrabajado;
	}

	public void setTiempoTrabajo(int sumadorTiempo) {
		this.tiempoTrabajado = tiempoTrabajado + sumadorTiempo;
	}


	public Deque<Cliente> getCola() { // Para retornar la cola de clientes de la caja
		return clientescaja;
	}
	/*********************************************************************
	* Method name: CompateTo
	
	* Description of the Method: Este metodo compara las cajas que tenemos en la cola de cajas. 
	* Al comparar los tamaños deja que la caja con menos clientes, es decir, menor tamaño de la cola, sea la siguiente a la que se introduzca un cliente.
	*
	*********************************************************************/
	@Override
	public int compareTo(Caja uno) {
		if (uno.getCola().size() < getCola().size()) {
			return 1;
		}
		if (uno.getCola().size() > getCola().size()) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Caja [ID=" + id + ", tiempoTrabajado=" + tiempoTrabajado + ", clientescaja=" + clientescaja + "]";
	}

}
