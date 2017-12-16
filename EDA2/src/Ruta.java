/***************************************
 ******************************
 *
 * Class Name: Aeropuerto 
 * Author/s name: Lucía Alfonso García 
 * 				Sergio Barrios Martínez 
 * 				Raul Ruiz del Valle Arevalo
 * 
 * Class description: Crea la clase ruta, declarando sus variables y
 * creando su constructor, getters, setters y toString de la misma
 **********************************************************************
 */
public class Ruta {
	String Aerolinea; //Compañia del viaje
	String IDAerolinea; 
	String codeOrigen; //IATA del aeropuerto origen
	String IDorigen;
	String codeDestino; //IATA del aeropuerto destino
	String IDdestino;

	//Constructor de la clase
	public Ruta(String Aerolinea, String IDAerolinea, String codeOrigen, String IDorigen, String codeDestino, String IDdestino) {
		this.Aerolinea = Aerolinea;
		this.IDAerolinea = IDAerolinea;
		this.codeOrigen = codeOrigen;
		this.IDorigen = IDorigen;
		this.codeDestino=codeDestino;
		this.IDdestino = IDdestino;

	}
//Getters, Setters y toString de la clase
	public String getAerolinea() {
		return Aerolinea;
	}


	public String getIDAerolinea() {
		return IDAerolinea;
	}


	public String getCodeOrigen() {
		return codeOrigen;
	}


	public String getIDorigen() {
		return IDorigen;
	}


	public String getCodeDestino() {
		return codeDestino;
	}


	public String getIDdestino() {
		return IDdestino;
	}


	@Override
	public String toString() {
		return "Aerolinea: " + Aerolinea + "\nIDAerolinea: " + IDAerolinea + "\nIATA origen: " + codeOrigen
				+ "\nIDorigen: " + IDorigen + "\nIATA destino: " + codeDestino + "\nIDdestino: " + IDdestino + "\n";
	
	}



}
