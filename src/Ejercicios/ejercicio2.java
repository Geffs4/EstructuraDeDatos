package Ejercicios;

import java.util.Iterator;
import TdaLista.*;

public class ejercicio2 {
	public <E> boolean aparece(E elem,PositionList<E> lista) {
		if(lista.isEmpty())
			return false;
		
		Position<E>p=lista.first();
		boolean encontre=false;
		
		while(p!=null&& !encontre) {
			encontre=p.equals(elem);
			if(!encontre)
				p=(p==lista.last()? null:lista.next(p));
		}
		return encontre;
	}
	//el siguiente se puede hacer si la lista es iterable
	
	public <E> boolean apareceI(E item,PositionList<E> lista) {
		Iterator<E>it=lista.iterator();
		boolean encontre=false;
		while(it.hasNext()&&!encontre) {
			E elemen=it.next();
			encontre=elemen.equals(item);
		}
		return encontre;
	}
	
	//con for each
	
	public <E> boolean apareceF(E item,PositionList<E> lista) {
		for(E elem:lista)
			if(elem.equals(item))
				return true;
		
		return false;
	}
}