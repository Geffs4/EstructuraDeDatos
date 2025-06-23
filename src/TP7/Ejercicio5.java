package TP7;

import Auxiliar.Position;
import TdaArbolBinario.ArbolBinario;

public class Ejercicio5 {
	public static void main(String []args){
		try {
			ArbolBinario<String>arbol=new ArbolBinario<String>();
			arbol.createRoot("A");
			arbol.addLeft(arbol.root(),"B");
			Position<String>C=arbol.addRight(arbol.root(),"C");
			Position<String>D=arbol.addLeft(C, "D");
			Position<String>E=arbol.addRight(C, "E");
			arbol.addRight(D, "F");
			arbol.addLeft(E, "G");
			System.out.println(arbol+":"+arbol.size());
			arbol.eliminarSubarbol(C);
			System.out.println(arbol+":"+arbol.size());
		}catch(Exception e) {System.out.println(e);}
	}
}
