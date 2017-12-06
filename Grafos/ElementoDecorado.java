import graphsDSESIUCLM.Element;
/*********************************************************************
*
* Class Name: DecoratedElement
* Author/s name: Daniel Romero, Pablo Mora & Beka Bekeri
* Release/Creation date: 18/12/2016
* Class description: This is the class to create decorated elements.
* We can obtain if an element is visited or not.
* We can compare two elements.
*
**********************************************************************
*/
public class ElementoDecorado<T> implements Element {

  private String ID;                 //Vertex ID
  private T element;                // Data Element
  private boolean visited;         //Attribute to label the node as visited
  /*********************************************************************
   * Method name: DecoratedElement
   *
   * Description of the Method: This is the constructor of the class.
   * Arguments: String key, T element
   *********************************************************************/
  public ElementoDecorado (String key, T element) {
    this.element = element;
    ID = key;
    visited = false;
  }
  /*********************************************************************
   * Method name: getters and setters
   *
   * Description of the Method: These are the getters and setters to obtain the element.
   * Arguments: element, boolean visited and ID for the stations
   *********************************************************************/
  public T getElement() {
    return element;
  }
  public boolean getVisited() {
    return visited;
  }
  public void setVisited(boolean t) {
    visited = t;
  }
  
  /* In this case, to check if two Vertices are identical, both the key and the
   * element must be equal.
  */
  /*********************************************************************
   * Method name: equals
   *
   * Description of the Method: This is the method that compare the elements..
   * Arguments: Object n
   *********************************************************************/
  public boolean equals (Object n) {
   return (ID.equals(((ElementoDecorado) n).getID())
        && element.equals(((ElementoDecorado<T>) n).getElement()));
  }
  /*********************************************************************
   * Method name: toString
   *
   * Description of the Method: This is the method that prints the information of the element.
   *********************************************************************/
  public String toString() {
    return element.toString();   
  }
  public String getID() {
    return ID;
  }
}

