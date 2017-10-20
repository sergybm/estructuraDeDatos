import java.util.concurrent.LinkedBlockingQueue;
import java.util.Queue;

class Ejercicio2 {
	public static void main(String args[]) {
		Queue<Cliente> cola = new LinkedBlockingQueue<Cliente>();
		int sumadorTiempo = 1;
		int tiempoTranscurrido = 0;
		Cliente cliente;
		Caja caja1 = new Caja('A', 0);
		Caja caja2 = new Caja('B', 0);
		
		cola.add(new Cliente(sumadorTiempo));
		cola.add(new Cliente(sumadorTiempo));
		cola.add(new Cliente(sumadorTiempo));
		cola.add(new Cliente(sumadorTiempo));
		
		pasandoPorCaja(cola,  caja1,caja2, sumadorTiempo, tiempoTranscurrido);

	}

	public static void pasandoPorCaja(Queue<Cliente> cola,Caja caja1, Caja caja2 ,int sumadorTiempo, int tiempoTranscurrido) {
		int tiempoAtendiendo = 0, tiempoProducto = 0;

		while (!cola.isEmpty()) {	
			Cliente clienteEnCaja;
			clienteEnCaja = cola.poll();
			int eficiencia = 0;
			for (int i = clienteEnCaja.getProductos(); i > 0; i--) {

				eficiencia = caja1.getEficienciaDespacho();

				while (tiempoProducto <= eficiencia) {
					tiempoProducto += sumadorTiempo;
				}
				tiempoAtendiendo += tiempoProducto;
				tiempoProducto = 0;
			}
			System.out.println("El tiempo transcurrido con el cliente ha sido de:" + tiempoAtendiendo);
			tiempoAtendiendo = 0;

		}
	}
}
