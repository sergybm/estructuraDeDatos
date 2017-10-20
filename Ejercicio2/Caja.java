class Caja {
	private char ID;
	private int tiempoTrabajado; /*Cada posicion es el tiempo en pasar todos los productos de un cliente */


	public Caja(char iD, int tiempoTrabajado) {
		ID = iD;
		this.tiempoTrabajado = tiempoTrabajado;
	}

	public char getID() {
		return ID;
	}

	public void setID(char iD) {
		ID = iD;
	}

	public int getEficienciaDespacho() {
		return (int) (Math.random() * (10 - 1) + 1);
	}

	public int getTiempoTrabajo() {
		return this.tiempoTrabajado = tiempoTrabajado;
	}

	public void setTiempoTrabajo(int sumadorTiempo) {
		tiempoTrabajado += sumadorTiempo;
	}


}