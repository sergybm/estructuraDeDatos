/*********************************************************************
*
* Class Name: ClienteViejo
* Author/s name: Raúl Ruiz del valle Álvarez
* 						 Sergio Barrios Martínez
* 						 Lucía Alfonso García
* Release/Creation date: 12/11/2017
* Class version: Final
* Class description: Esta clase representa a los clientes viejos, que trataremos de forma diferente al meterlos a las colas de las cajas.
*
**********************************************************************
*/
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

