package TdaLista;

import Auxiliar.Position;
import Auxiliar.PositionList;

public class test2 {

	public static void main(String[] args) {
		PositionList<Integer>l1=new ListaDoblementeEnlazada<Integer>();
		PositionList<Integer>l2=new ListaDoblementeEnlazada<Integer>();
		for(int i=0;i<5;i++){
			l1.addLast(i*2);
			l2.addLast(i*2+4);
		}
		
		System.out.println(l1+" "+l2);
		System.out.println(equivalentes(l1,l2));

	}
	public static <E> PositionList<E> equivalentes(PositionList<E> l1,PositionList<E> l2){
		PositionList<E>exit=new ListaDoblementeEnlazada<E>();
		Position<E>cursor=l1.first();
		while(cursor!=null){
			for(E elem:l2){
				if(elem.equals(cursor.element()))
						exit.addLast(cursor.element());
			}
			cursor=(cursor!=l1.last()) ? l1.next(cursor):null;
		}
		return exit;
	}
}
