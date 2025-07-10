package TP6;



import java.util.Iterator;

import Auxiliar.Position;
import TdaArbol.ArbolGen;
import TdaArbol.TNodo;
import TdaArbol.Tree;
import TdaLista.PositionList;
import TdaLista.ListaDoblementeEnlazada;
public class Ejercicio4 {

	public static void main(String[] args) {
		try {
			ArbolGen<String>arbol=new ArbolGen<>();
			arbol.createRoot("A");
			arbol.addLastChild(arbol.root(),"B");
			Position<String>C=arbol.addLastChild(arbol.root(),"C");
			arbol.addLastChild(C,"A");
			Position<String>E=arbol.addLastChild(C,"E");
			arbol.addLastChild(C,"F");
			arbol.addLastChild(E,"A");
			System.out.println(arbol);
			for(Position<String> s:postString(arbol,"A"))
				System.out.println(s.element()+" "+s);
			}
		catch(Exception e){System.out.println(e);}
		}
	private static <E> Iterable<Position<E>> postString(Tree<E> arbol,String s){
		PositionList<Position<E>>exit=new ListaDoblementeEnlazada<Position<E>>();
		postOrden(arbol.root(),exit,s);
		return exit;
	}
	private static<E> void postOrden(Position<E> nodo,PositionList<Position<E>> list,String s){
		if(nodo!=null) {
			TNodo<E> n=(TNodo<E>)nodo;
			for(Position<E> hijos:n.getHijos()){
				postOrden(hijos,list,s);
			}
			if(nodo.element()==s)
					list.addLast(nodo);
		}
	}
	@SuppressWarnings("unused")
	private static <E> String toString(Tree<E> arbol){
		Iterator<E> it=arbol.iterator();
		String s="[ ";
		while(it.hasNext()){
			s+=it.next();
			if(it.hasNext())
				s+=" , " ;
		}
		s+=" ]";
		return s;
	} 
}
