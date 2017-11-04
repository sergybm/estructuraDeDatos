
public class  ClienteViejo extends Cliente {
	public ClienteViejo(double tiempoLlegada) {
		super(tiempoLlegada);	
	}

	public int getProductos() {
		return productos;
	}

	public void setProductos(int productos) {
		this.productos = productos;
	}

	public double getTiempoLlegada() {
		return tiempoLlegada;
	}

	public void setTiempoLlegada(double tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}
	


	@Override
	public String toString() {
		return " [productos=" + productos + ", tiempoLlegada=" + tiempoLlegada + "]";
	}

}

