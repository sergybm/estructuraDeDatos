import java.util.Scanner;
import java.util.Stack;

class CalculadoraCadenas {
  private static Scanner src;
public static void main(String[] args) {
    String resultado="";
    src = new Scanner(System.in);
    System.out.println("Introduce una cadena. Utiliza espacios en blanco");
    String s=src.nextLine();
    String [] tokens=s.split(" ");
    resultado=evaluar(tokens);
    System.out.printf("Resultado= %s\n",resultado);
  }

  public static String evaluar(String []s){
    Stack<String> p = new Stack<String>();
    String t1, t2,r,resul;
    char operator;
    for (int i=0; i<s.length; i++) {
      if (!comprobarOperador(s[i])){
        p.push(s[i]);
      }
      else  {
    	voltearPila(p);
        t1=p.pop();
        t2=p.pop();
        operator=s[i].charAt(0);
        r=operacion(t1,t2,operator);
        p.push(r);
      }
    }
    resul=p.pop();
    return resul;
  }

  public static boolean comprobarOperador(String s){
    boolean b=false;
    if (s.equals("+") || s.equals("-"))
      b=true;
    return b;
  }
  public static String operacion(String t1,String t2, char c){
   String r="";
    switch(c) {
      case '+':
        r=t1+t2;
        break;
       case '-':
    	   try {
    		   r=t1.substring(0,t1.length()- Integer.parseInt(t2));
    	   }catch(Exception e) {
    		   r=t2.substring(0,t2.length()- Integer.parseInt(t1));
    	   }
      
        break;
    }
    return r;
  }
  
  public static <T> void voltearPila (Stack<T> p){
	    T e;
	    if (!p.empty()) {
	      e=p.pop();
	      voltearPila(p);
	      ponerEncima (p,e);
	    }
	  }
	  public static<T> void ponerEncima (Stack<T> p, T e){
	    T a;
	    if (!p.empty()){
	      a=p.pop();
	      ponerEncima(p,e);
	      p.push(a);
	    }
	    else {
	      p.push(e);
	  }
	 }
}