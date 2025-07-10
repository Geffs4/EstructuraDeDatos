package TP7;

import Auxiliar.Position;
import TdaArbolBinario.ArbolBinario;
import TdaArbolBinario.BTNodo;
import TdaArbolBinario.BinaryTree;
import TdaLista.PositionList;
import TdaLista.ListaDoblementeEnlazada;

public class Ejercicio3 {

	public static void main(String[] args) {
		ArbolBinario<String>arbol=new ArbolBinario<String>();
		arbol.createRoot("/");
		Position<String>mult=arbol.addLeft(arbol.root(),"*");
		Position<String>mas=arbol.addRight(arbol.root(), "+");
		Position<String>mas1=arbol.addLeft(mult, "+");
		arbol.addRight(mult,"3");
		arbol.addLeft(mas1,"3");
		arbol.addRight(mas1,"1");
		Position<String>menos=arbol.addLeft(mas,"-");
		arbol.addLeft(menos,"9");
		arbol.addRight(menos, "5");
		arbol.addRight(mas,"2");
		
		System.out.println(arbol);
		System.out.println(aritmetica(arbol));
	}
	private static <E> Iterable<E> aritmetica(BinaryTree<E> arbol){
		PositionList<E>list=new ListaDoblementeEnlazada<E>();
		postOrden((BTNodo<E>)arbol.root(),list);
		return list;
	}
	private static <E> void postOrden(BTNodo<E> nodo, PositionList<E> list) {
		if(nodo!=null){
			postOrden(nodo.getLeft(),list);
			list.addLast(nodo.element());
			postOrden(nodo.getRight(),list);	
		}	
	}
}
