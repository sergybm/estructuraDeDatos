public class Aeropuerto {
	String ID;	/* ID aeropuerto */
	String ciudad;
	String nombre;
	String pais; 
	String IATA; /* Nomenglatura aeropuerto */
	String latitud;
	String longitud;
	String altitud;
	public Aeropuerto(String iD,String nombre,String ciudad, String pais, String IATA, String latitud, String longitud, String altitud) {
		this.ID = iD;
		this.nombre=nombre;
		this.ciudad=ciudad;
		this.pais = pais;
		this.IATA = IATA;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
	}
	/* 
	 * ID=1, nombre=2, ciudad=3; pais=4; IATA=5; ICAO=6, latitud=7 ; longitud=8; altitud=9;
	 * El numero de variables del fichero tiene 15 datos pero solo nos quedamos con esos.	
	 */
	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	public String getCiudad() {
		return ciudad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getIATA() {
		return IATA;
	}

	public void setIATA(String iATA) {
		IATA = iATA;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getAltitud() {
		return altitud;
	}

	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}
	@Override
	public String toString() {
		return "AirportID: "+this.ID+"\nCountry: "+this.pais+"\nIATA: "+this.IATA
				+"\nLatitud: "+this.latitud+"\nLongitud: "+this.longitud+"\nAltitud: "+this.altitud+"\n";
	}
}
