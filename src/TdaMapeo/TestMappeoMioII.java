package TdaMapeo;

import TdaLista.*;
import Auxiliar.*;
import java.util.Iterator;

public class TestMappeoMioII {
	public static void main(String[] args) {
		PositionList<String>p=new ListaDoblementeEnlazada<String>();
		
		p.addLast("a");
		p.addLast("b");
		p.addLast("a");
		p.addLast("c");
		p.addLast("a");
		p.addLast("d");
		p.addLast("b");
		p.addLast("c");
		p.addLast("a");
		p.addLast("b");
		
		System.out.println(p);
		System.out.println(cantElementos(p));
	}
	public static <E> Map<E,Integer> cantElementos(PositionList<E>l){
		Map<E,Integer>cant=new MapeoConLista<E,Integer>();
		Iterator<E>it=l.iterator();
		E element=it.next();
		
		while(it.hasNext()){
			int contador=1;
			for(E clave:l)
				if(element.equals(clave))
					cant.put(element,contador++);
				
			element=it.next();
		}
		return cant;
	} 
}
