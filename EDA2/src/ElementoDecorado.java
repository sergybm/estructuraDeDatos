/***************************************
******************************
*
* Class Name: DecoratedElementEstacion
* Author/s name: Lucía Alfonso García
* 				Sergio Barrios Martínez
* 				Raul Ruiz del Valle Arevalo
* Class description: elemento decorado que contiene el elemento 'estacion' y es usado
* para introducir vertices en grafo y para recorrerlo con BFS a traves 
* del boolean visited y de parent. Tiene diversos gets y sets para obtener y modificar sus datos,
* un metodo equals para comparar vertices y sobrescribe metodo toString para imprimir por pantalla.
**********************************************************************
*/

//importacion de paquetes usados
import graphsDSESIUCLM.*;



public class ElementoDecorado<T> implements Element {
  private String ID;                 //Id del vertice
  private T element;                // Datos del elemento
  private boolean visited;         // Si esta visitado o no
  private ElementoDecorado<T> parent; // Vertice desde el cual se accede al nodo actual
  private int distance;    // Distancia en vertices del nodo original
    
  
  //Constructor de la clase
  public ElementoDecorado (String ID, T element) {
    this.ID=ID;   
    this.element = element;
    visited = false;
    parent = null;
    distance=0; 
  }
  
//Getters y Setters de la clase

  public T getElement() {
    return element;
  }
  
  public String getID() {
    return ID;
  }
  
  public boolean getVisited() {
    return visited;
  }
  
  public void setVisited(boolean t) {
    visited = t;
  }
  
  public void setID(String ID) {
    this.ID = ID;
}
  
  public ElementoDecorado<T> getParent() {
    return parent;
  }
  public void setParent(ElementoDecorado<T> u) {
    parent = u;
  }
  public int getDistance() {
    return distance;
  }
  public void setDistance(int d) {
    distance = d;
  }
    
  /* metodo para comparar 2 objetos, seran iguales si el numero es el mismo.
   * se realiza un cast para convertirn (class Object) a clase
   */
  @Override
  public boolean equals (Object n) {
   return (ID.equals(((ElementoDecorado) n).getID())
        && element.equals(((ElementoDecorado<T>) n).getElement()));
  }
   //Cambio en el toString para esta clase.
  @Override
  public String toString() {
    return element.toString();   
  }
  
}  

