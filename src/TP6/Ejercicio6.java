package TP6;

import java.util.Iterator;

import Auxiliar.Position;
import TdaArbol.ArbolGen;
import TdaArbol.Tree;

public class Ejercicio6 {
	public static void main(String []args){
		 Tree<Integer>arbol=new ArbolGen<Integer>();
		 arbol.createRoot(1);
		 arbol.addLastChild(arbol.root(), 2);
		 Position<Integer>tres=arbol.addLastChild(arbol.root(), 3);
		 
		 arbol.addLastChild(tres,4);
		 arbol.addLastChild(tres, 5);
		 arbol.addLastChild(tres, 6);
		 
		 System.out.println(arbol+" Pertenece?:[ "+5+" ]:"+pertenece(arbol,5));
	}
	public static <E> boolean pertenece(Tree<E> arbol, E elemento){
		Iterator<E>it=arbol.iterator();
		boolean encontre=false;
		while (it.hasNext()&&!encontre){
			E cursor=it.next();
			if(cursor.equals(elemento))
				encontre=true;
		}
		return encontre;
	}
}
