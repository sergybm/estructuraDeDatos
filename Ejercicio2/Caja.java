class Caja {
	private char ID;
	private int tiempoTrabajado; /*Cada posicion es el tiempo en pasar todos los productos de un cliente */


	public Caja(int i, int tiempoTrabajado) {
		int ID = i;
		this.tiempoTrabajado = tiempoTrabajado;
	}

	public char getID() {
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


}