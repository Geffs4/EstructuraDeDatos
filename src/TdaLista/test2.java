package TdaLista;

import java.util.Iterator;

import Auxiliar.Position;
import Auxiliar.PositionList;

public class test2 {

	public static void main(String[] args) {
		PositionList<Integer>l1=new ListaDoblementeEnlazada<Integer>();
		PositionList<Integer>l2=new ListaDoblementeEnlazada<Integer>();
		for(int i=0;i<5;i++){
			l1.addLast(i*2);
			l2.addLast(i*2);
		}
		
		System.out.println(l1+" "+l2);
		System.out.println(removeList(l1,l2));

	}
	public static <E> PositionList<E> removeList(PositionList<E>l1,PositionList<E>l2){
		PositionList<E>eliminado=new ListaDoblementeEnlazada<E>();
		if(l1.isEmpty()&&l2.isEmpty())
			return eliminado;
		
		Iterator<E>it=l1.iterator();
		while(it.hasNext()){
			E p1=it.next();
			for(Position<E>p2:l2.positions()){
				if(p1.equals(p2.element()))
					eliminado.addLast(l2.remove(p2));
			}
		}
		return eliminado;
		
	}
}
