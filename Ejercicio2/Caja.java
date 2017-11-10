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
