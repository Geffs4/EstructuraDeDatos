package TP6;
import TdaArbol.ArbolGen;
import TdaArbol.TNodo;
import TdaArbol.Tree;
import Auxiliar.Position;
public class Ejercicio5 {
	public static void main(String []args){
		try {
		Tree<String>arbol=new ArbolGen<String>();
		arbol.createRoot("A");
		Position<String>B=arbol.addLastChild(arbol.root(),"B");
		Position<String>C=arbol.addLastChild(arbol.root(),"C");
		arbol.addLastChild(C,"F");
		arbol.addLastChild(B,"D");
		arbol.addLastChild(B,"E");
		
		for(int i=0;i<=3;i++) {arbol.addLastChild(C,"G");}
		System.out.println(arbol);
		System.out.println("elementos elimnados:"+eliminarElemento(arbol,"G"));
		System.out.println(arbol);
		
		}catch(Exception e) {System.out.println(e);}}
	public static <E> int eliminarElemento(Tree<E> arbol,E elemento){
		int cant=0;
		cant=postOrden(arbol.root(),elemento,cant,arbol);
		return cant;
	}
	private static <E> int postOrden(Position<E> nodo, E elemento, int cant,Tree<E> arbol) {
		if(nodo!=null){
			if(nodo.element()==elemento){
				arbol.removeNode(nodo);
				cant++;
			}
			TNodo<E>n=(TNodo<E>)nodo;
			for(Position<E>sons:n.getHijos())
				cant=postOrden(sons,elemento,cant,arbol);
			
			}	
		return cant;
		}
	}
