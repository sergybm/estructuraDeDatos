class Caja implements Comparable<Caja>{
	private char ID;
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
	@Override
	public int compareTo(Caja other) {		// Devuelve -1 si la caja actual tiene más prioridad que la anterior y 1 si la anterior tiene más prioridad que la actual


if (this.getPrioridad() ^ other.getPrioridad()) { // Se usa una XOR para que solo entre si una de ellas es verdadera
return this.getPrioridad() ? -1 : 1; // / Si la caja tiene más prioridad -1, si no, entonces tiene 1, (menos prioridad que la anterior)
}
int clase = -Integer.compare(this.getClass(), other.getClass()); //This is going to compare both numbers which
	//define each one class, so if the actual has more class than the previous one
	//it will return a 1, but as our order of classes is 0>1>2, we have to inverse it
if (clase == 0) {				//The last line will return a 0 if both have the same class so well have
	// to compare the arrival time

return this.getArrivalTime() < other.getArrivalTime() ? -1 : 1; //if the actual has less arrival time
//so it means that he has arrived earlier, well return a -1
}
return clase > 0 ? -1 : 1;		//if they have diferent classes, well return the order of them
	//-1 if the line 50 has returned a positive value so it would mean that
	//the actual client has more priority than the last one
}





}