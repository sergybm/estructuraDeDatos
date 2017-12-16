/***************************************
 ******************************
 *
 * Class Name: Aeropuerto Author/s name: Luc�a Alfonso Garc�a Sergio Barrios
 * Mart�nez Raul Ruiz del Valle Arevalo
 * 
 * Class description: Crea la clase aeropuerto, declarando sus variables y
 * creando su constructor, getters, setters y toString de la misma
 **********************************************************************
 */
public class Aeropuerto {
	
	//Declaraci�n de las variables
	String ID; /* ID aeropuerto */
	String ciudad;
	String nombre;
	String pais;
	String IATA; /* Nomenclatura aeropuerto */
	String latitud;
	String longitud;
	String altitud;

	// Constructor de la clase
	public Aeropuerto(String iD, String nombre, String ciudad, String pais, String IATA, String latitud,
			String longitud, String altitud) {
		this.ID = iD;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.IATA = IATA;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
	}

	// Getters, setters y toString de la clase
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
		return IATA;
	}

}