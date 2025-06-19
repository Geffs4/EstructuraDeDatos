package TP7;

import Auxiliar.Position;
import TdaArbol.ArbolGen;
import TdaArbol.TNodo;
import TdaArbol.Tree;
import TdaMapeo.Map;
import TdaMapeo.MapeoConLista;

public class Ejercicio3 {
	public static  Map<Character, Integer>cantidadRepeticiones(Tree<Character> t){
		Map<Character,Integer>map=new MapeoConLista<Character,Integer>();
		preOrdenCant(map,t.root());
		System.out.println(map);
		return map;
	}
	private static void preOrdenCant(Map<Character,Integer>m,Position<Character> nodo){
		if(nodo!=null) {
			Integer valor=m.get(nodo.element())==null ? 1:m.get(nodo.element())+1;
			m.put(nodo.element(),valor);
			TNodo<Character>aux=(TNodo<Character>)nodo;
			for(Position<Character>hijos:aux.getHijos()){
				preOrdenCant(m,hijos);
			}
		}
	}
	public static void main(String[]args) {
		try {
		ArbolGen<Character>arbol=new ArbolGen<>();
		arbol.createRoot('A');
		arbol.addLastChild(arbol.root(),'B');
		Position<Character>C=arbol.addLastChild(arbol.root(),'C');
		arbol.addLastChild(C,'D');
		Position<Character>E=arbol.addLastChild(C,'E');
		arbol.addLastChild(C,'F');
		arbol.addLastChild(E,'G');
		
		System.out.println(arbol);
		cantidadRepeticiones(arbol);
		}catch(Exception e){System.out.println(e);}
	}
}
