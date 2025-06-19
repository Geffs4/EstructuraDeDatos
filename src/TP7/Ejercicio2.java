package TP7;

import Auxiliar.Position;
import TdaArbol.ArbolGen;


public class Ejercicio2 {

	public static void main(String[] args) {
		ArbolGen<String> arbol = new ArbolGen<>();
		arbol.createRoot("A");
		Position<String> b = arbol.addLastChild(arbol.root(), "B");
		Position<String> c = arbol.addLastChild(arbol.root(), "C");
		
		arbol.addLastChild(b,"1");
		System.out.println(arbol);
		try {
		arbol.eliminarUltimoHijo(c);
		}catch(Exception e){System.out.println(e);}
		System.out.println(arbol);
	}
	//calcular tiempo en ejecucion=?
}

