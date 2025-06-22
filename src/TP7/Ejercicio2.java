package TP7;
import Auxiliar.Position;
import TdaArbolBinario.ArbolBinario;

public class Ejercicio2 {
	public static void main (String []args){
		ArbolBinario<String>arbol=new ArbolBinario<String>();
		arbol.createRoot("A");
		arbol.addLeft(arbol.root(),"B");
		Position<String>C=arbol.addRight(arbol.root(),"C");
		arbol.addLeft(C, "D");
		arbol.addRight(C, "E");
		System.out.println(arbol);
		System.out.println(arbol.diccionario());
	}
}