import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		muestraAeropuertos("airports.txt");

	}
	 public static void muestraAeropuertos(String archivo) throws FileNotFoundException, IOException {
	        String linea;
	        String [] cadena;
	  
	        
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	        while((linea = b.readLine())!=null) {
	        	cadena = linea.split(",");
	        	Aeropuerto aeropuertos= new Aeropuerto(Integer.parseInt(cadena[0]),cadena[3],cadena[4],cadena[5], Double.parseDouble(cadena[6]),Double.parseDouble(cadena[7]), Integer.parseInt(cadena[8]));
	            System.out.println(aeropuertos.toString());
	        }
	        b.close();
	    }

}
