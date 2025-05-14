package TdaLista;

import java.util.*;

import Auxiliar.Position;
import Auxiliar.PositionList;

public class ElementIterator<E> implements Iterator<E> {
	//atributos de instancia
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	public ElementIterator(PositionList<E> l) {
		list=l;
		if(list.isEmpty())
			cursor=null;
		else
			cursor=list.first();	
	}
	@Override
	public boolean hasNext() {
		return cursor!=null;
	}

	@Override
	public E next() {
		if(cursor==null)
			throw new NoSuchElementException("iterador de la lista:no hay siguiente");
		E exit=cursor.element();
		
		cursor = (cursor == list.last()) ? null : list.next(cursor); 
		return exit;
	}

}
