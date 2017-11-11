
/***************************************
******************************
*
* Class Name: CalculadoraCadena
* Author/s name: Lucia Alfonso Garcia	
* 				 Sergio Barrios Martinez
* 				 Raul Ruiz del Valle Alvarez
* 				
: 
* Release/Creation date: 12/11/2017
* Class version: 1.0
* Class description:Se define la siguiente notación posfija para operar con cadenas de caracteres
inspirada en la notación posfija para expresiones aritméticas:
+ Operador binario que concatena dos cadenas de caracteres
- Operador binario que elimina de la cadena suministrada los caracteres indicados desde la derecha 

**********************************************************************
*/
import java.util.Scanner;
import java.util.Stack;

class CalculadoraCadenas {
	private static Scanner src;

	public static void main(String[] args) {
		String resultado = ""; // almacenara el resultado final
		src = new Scanner(System.in);
		System.out.println("Introduce una cadena. Utiliza espacios en blanco"
				+ " \nEjemplo: abc xyz + 1 - \npepe 2 - juan + rosa + 3 - 4 -\n");
		String s = src.nextLine(); // Entrada para escribir la operacion
		String[] tokens = s.split(" "); // Division de la cadena con la operacion

		try {
			resultado = evaluar(tokens);
			System.out.printf("Resultado= %s\n", resultado);
		} catch (Exception e) {
			System.out.println("No se a introducido bien la expresion");
		}
	}

	/***************************************
	 ******************************
	 *
	 * Method name: evaluar Description of the Method: Almacena en la pila los
	 * caracteres hasta encontrar un simbolo aritmetico. Si lo encuentra saca los
	 * valores de la pila y opera. Calling arguments: Necesita un array de String
	 * con expresiones aritmeticas y caracteres. return value: Devuelve la pila con
	 * la operacion realizada.
	 * 
	 */
	public static String evaluar(String[] s) {
		Stack<String> p = new Stack<String>();
		String t1, t2, r, resul;
		char operator;
		for (int i = 0; i < s.length; i++) {
			if (!comprobarOperador(s[i])) {
				p.push(s[i]);
			} else {
				voltearPila(p);
				t1 = p.pop();
				t2 = p.pop();
				operator = s[i].charAt(0);
				r = operacion(t1, t2, operator);
				p.push(r);
			}
		}
		resul = p.pop();
		return resul;
	}

	public static boolean comprobarOperador(String s) {
		boolean b = false;
		if (s.equals("+") || s.equals("-"))
			b = true;
		return b;
	}

	/***************************************
	 ******************************
	 *
	 * Method name: operacion Description of the Method: comprueba cual es la
	 * expresion aritmetica. Si es una suma, concatena los caracteres. Si es una
	 * resta comprueba si el primer valor es un numero o una cadena, es debido a que
	 * el metodo substring tiene una sintaxis especifica. Calling arguments: 2
	 * String pueden ser o un numero o una cadena, un char con el simbolo
	 * aritmetico. value: Devuelve la operacion realizada.
	 * 
	 */

	public static String operacion(String t1, String t2, char c) {
		String r = "";
		switch (c) {
		case '+':
			r = t1 + t2;
			break;
		case '-':
			try {
				r = t1.substring(0, t1.length() - Integer.parseInt(t2));
			} catch (Exception e) {
				r = t2.substring(0, t2.length() - Integer.parseInt(t1));
			}

			break;
		}
		return r;
	}

	public static <T> void voltearPila(Stack<T> p) {
		T e;
		if (!p.empty()) {
			e = p.pop();
			voltearPila(p);
			ponerEncima(p, e);
		}
	}

	public static <T> void ponerEncima(Stack<T> p, T e) {
		T a;
		if (!p.empty()) {
			a = p.pop();
			ponerEncima(p, e);
			p.push(a);
		} else {
			p.push(e);
		}
	}
}