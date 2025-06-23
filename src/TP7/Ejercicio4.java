package TP7;

import Auxiliar.Position;
import Exceptions.EmptyTreeException;
import TdaArbolBinario.ArbolBinario;
import TdaArbolBinario.BTNodo;
import TdaArbolBinario.BinaryTree;

public class Ejercicio4 {
	public static void main(String []args) {
		ArbolBinario<String>arbol=new ArbolBinario<String>();
		arbol.createRoot("A");
		Position<String>B=arbol.addLeft(arbol.root(),"B");
		Position<String>C=arbol.addLeft(B,"C");
		Position<String>D=arbol.addLeft(C, "D");
		@SuppressWarnings("unused")
		Position<String>E=arbol.addLeft(D, "E");
		System.out.println(arbol);
		completarDerechos("puto",arbol);
		System.out.println(arbol);
	}
	public static <E> void completarDerechos(E r, BinaryTree<E> t){
		if(t.isEmpty()){throw new EmptyTreeException("El arbol esta vacio");}
		else{
			simetrico(t.root(),r,t);
		}
	}
	private static <E> void simetrico(Position<E> nodo, E rotulo, BinaryTree<E> arbol){
		if(nodo!=null) {
			BTNodo<E>nod=(BTNodo<E>)nodo;
			if(nod!=arbol.root())
				arbol.addRight(nod.getParent(), rotulo);
			if(nod.getLeft()!=null)
				simetrico(nod.getLeft(), rotulo, arbol);
		}
	}
}

