public class Caja implements Comparable<Caja>{
	private int ID;
	private int tiempoTrabajado; /*Cada posicion es el tiempo en pasar todos los productos de un cliente */
	private boolean prioridad; // La utilizamos para saber que caja tiene prioridad para el cliente.

	public Caja(int i) {
		int ID = i;
	}
	
	public boolean getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(boolean prioridadcaja) {
		prioridad = prioridadcaja;
	}

	public int getID() {
		return ID;
	}

	public void setID(char iD) {
		ID = iD;
	}

	public int getEficienciaDespacho() { // Se generará aleatoriamente (máximo 20 segundos/producto).
		return (int) (Math.random() * (10 - 1) + 1);
	}

	public int getTiempoTrabajo() { // Es el agregado del tiempo que una caja ha estado trabajando.
		return this.tiempoTrabajado = tiempoTrabajado;
	}

	public void setTiempoTrabajo(int sumadorTiempo) { 
		tiempoTrabajado += sumadorTiempo;
	}
	
	/*/ @Override
	public int compareTo(Caja c1) {
		int r=0;
		if (c1.getCola().size() < c2.getCola().size()) {
			return -1;
		}
		if (c1.getCola().size() > c2.getCola().size() ) {
			return 1;
		}
		return 0;
	} 	
	} /*/
	






}