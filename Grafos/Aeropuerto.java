
public class Aeropuerto {
	int ID;
	String pais;
	String IATA;
	String ICAO;
	double latitud;
	double longitud;
	int altitud;
	public Aeropuerto(int iD, String pais, String IATA,String ICAO, double latitud, double longitud, int altitud) {
		ID = iD;
		this.pais = pais;
		this.IATA = IATA;
		this.ICAO=ICAO;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
	}
	/* 
	 * ID=1; pais=4; IATA=5; ICAO=6, latitud=7 ; longitud=8; altitud=9;
	 * El numero de variables del fichero tiene 15 datos pero solo nos quedamos con esos.	
	 */

	public int getID() {
		return ID;
	}


	public String getPais() {
		return pais;
	}


	public String getIATA() {
		return IATA;
	}


	public String getICAO() {
		return ICAO;
	}


	public double getLatitud() {
		return latitud;
	}


	public double getLongitud() {
		return longitud;
	}


	public int getAltitud() {
		return altitud;
	}
	
	@Override
	public String toString() {
		return "AirportID: "+this.ID+"\nCountry: "+this.pais+"\nIATA: "+this.IATA+"\nICAO: "
				+ ""+this.ICAO+"\nLatitud: "+this.latitud+"\nLongitud: "+this.longitud+"\nAltitud: "+this.altitud+"\n";
	}

}
