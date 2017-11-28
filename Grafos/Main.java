import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Main {

	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		CargarAeropuertos("airports.txt");
		CargarRutas("routes.txt");

	}
	
	
	 public static void CargarAeropuertos(String archivo) throws FileNotFoundException, IOException {
	        String linea;
	        String [] cadena;
	  
	        
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	        while((linea = b.readLine())!=null) {
	        	cadena = linea.split(",");
	        	if(linea.contains("\"Spain\"")||linea.contains("\"Italy\"")||linea.contains("\"Germany\"")||linea.contains("\"France\"")) {
	        	Aeropuerto aeropuertos= new Aeropuerto(Integer.parseInt(quitarComillas(cadena[0])),quitarComillas(cadena[3]),quitarComillas(cadena[4]),quitarComillas(cadena[5]), Double.parseDouble(cadena[6]),Double.parseDouble(cadena[7]), Integer.parseInt(cadena[8]));
	        	System.out.println(aeropuertos.toString());
	        	}
	        }
	        b.close();
	    }
	 
	 public static void CargarRutas(String archivo) throws FileNotFoundException, IOException {
	        String linea;
	        String [] cadena;
	  
	        
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	        while((linea = b.readLine())!=null) {
	        	cadena = linea.split(",");    
	        	Ruta rutas= new Ruta((cadena[0]),(cadena[1]),(cadena[2]),(cadena[3]),(cadena[4]),(cadena[5]));
	        	System.out.println(rutas.toString());        	
	        }
	        b.close();
	    }
	 
		public static String quitarComillas(String token) {
			return token.substring(1, token.length()-1);	
		}


}
