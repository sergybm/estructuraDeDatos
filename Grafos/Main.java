import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import graphsDSESIUCLM.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		CargarRutas("routes.txt");

	}

	public static Aeropuerto[] CargarAeropuertos(String archivo, String origen, String destino)
			throws FileNotFoundException, IOException {
		String linea;
		String[] cadena;
		Aeropuerto[] viaje = new Aeropuerto[1];

		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			if (cadena[3].equals("\"Spain\"") || cadena[3].equals("\"Italy\"") || cadena[3].equals("\"Germany\"")
					|| cadena[3].equals("\"France\"")) {
				if (cadena[4].equals(origen) || cadena[4].equals(origen) || cadena[4].equals(origen)
						|| cadena[4].equals(origen)) {

					Aeropuerto ap1 = new Aeropuerto(Integer.parseInt(quitarComillas(cadena[0])),
							quitarComillas(cadena[3]), quitarComillas(cadena[4]),
							Double.parseDouble(cadena[6]), Double.parseDouble(cadena[7]), Integer.parseInt(cadena[8]));
					viaje[0] = ap1;
					

				} else if (cadena[4].equals(destino) || cadena[4].equals(destino) || cadena[4].equals(destino)
						|| cadena[4].equals(destino)) {

					Aeropuerto ap2 = new Aeropuerto(Integer.parseInt(quitarComillas(cadena[0])),
							quitarComillas(cadena[3]), quitarComillas(cadena[4]),
							Double.parseDouble(cadena[6]), Double.parseDouble(cadena[7]), Integer.parseInt(cadena[8]));
					viaje[1] = ap2;
				
				}
				
			}
		}
		b.close();

		return viaje;
	}

	public static void CargarRutas(String archivo) throws FileNotFoundException, IOException {
		String linea;
		String[] cadena;
		Aeropuerto aeropuerto[];

		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while ((linea = b.readLine()) != null) {
			cadena = linea.split(",");
			Ruta rutas = new Ruta((cadena[0]), (cadena[1]), (cadena[2]), (cadena[3]), (cadena[4]), (cadena[5]));
			aeropuerto = CargarAeropuertos("airports.txt", rutas.getCodeOrigen(), rutas.getCodeDestino());
			for (int i = 0; i < aeropuerto.length; i++) {
				if (aeropuerto[i] != null) {
					System.out.println(aeropuerto[i].toString());
				}
			}
			
			//System.out.println(rutas.toString());

		}
		b.close();
	}
	
		
	

	public static String quitarComillas(String token) {
		return token.substring(1, token.length() - 1);
	}

}
