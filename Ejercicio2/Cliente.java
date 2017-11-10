/*********************************************************************
*
* Class Name: Cliente
* Author/s name: Raúl Ruiz del valle Álvarez
* 						 Sergio Barrios Martínez
* 						 Lucía Alfonso García
* Release/Creation date: 12/11/2017
* Class version: Final
* Class description: Clase de clientes, el numero de productos se genera aleatoriamente, utilizamos un toString para imprimir  los clientes, que se componen de los productos que poseen y del tiempo en el que llegan a la caja.
*
**********************************************************************
*/
class Cliente {
		int productos;
		double tiempoLlegada;

		public Cliente(double tiempoLlegada) {
			this.productos = (int) (Math.random() * (10 - 1) + 1);
			this.tiempoLlegada = tiempoLlegada ;
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