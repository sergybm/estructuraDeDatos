public class Ruta {
	String Aerolinea; 
	String IDAerolinea; // Los tengo que hacer Strings por que tienen /N 
	String codeOrigen; // el code puede ser o ATA o ICAO.
	String IDorigen; // Los tengo que hacer Strings por que tienen /N 
	String codeDestino;
	String IDdestino;// Los tengo que hacer Strings por que tienen /N 

	
	public Ruta(String Aerolinea, String IDAerolinea, String codeOrigen, String IDorigen, String codeDestino, String IDdestino) {
		this.Aerolinea = Aerolinea;
		this.IDAerolinea = IDAerolinea;
		this.codeOrigen = codeOrigen;
		this.IDorigen = IDorigen;
		this.codeDestino=codeDestino;
		this.IDdestino = IDdestino;

	}
	/* 
	 * Aerolinea=0,IDAerolinea=1,IATAorigen=2,IDorigen=3,ICAOorigen=4,IDdestino=5
	 * El numero de variables del fichero tiene 9 datos pero solo nos quedamos con esos.	
	 */


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
		return "Ruta [Aerolinea=" + Aerolinea + ", IDAerolinea=" + IDAerolinea + ", codeOrigen=" + codeOrigen
				+ ", IDorigen=" + IDorigen + ", codeDestino=" + codeDestino + ", IDdestino=" + IDdestino + "]";
	}



}