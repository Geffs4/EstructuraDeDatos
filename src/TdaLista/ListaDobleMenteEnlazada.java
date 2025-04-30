package TdaLista;

import java.util.Iterator;

import Exceptions.*;

public class ListaDobleMenteEnlazada<E> implements PositionList<E> {
	//atributos de intancia
	protected DNodo<E> header;
	protected DNodo<E> trailer;
	protected int size;
	
	//constructor
	public ListaDobleMenteEnlazada() {
		header=new DNodo<E>();
		trailer=new DNodo<E>();
		header.setSiguiente(trailer);
		trailer.setPrevio(header);
		size=0;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public Position<E> first() {
		if(isEmpty())
			throw new EmptyListException("la lista esta vacia");
		return header.getSiguiente();
	}
	public Position<E> last(){
		if(isEmpty())
			throw new EmptyListException("la lista esta vacia");
		return trailer.getPrevio();
	}
	public Position<E> next(Position<E> p) {
		DNodo<E>aux=checkPosition(p);
			if(aux.getSiguiente()==trailer)
				throw new BoundaryViolationException("este es el ultimo elemento");
		return aux.getSiguiente();
	}
	private DNodo<E> checkPosition(Position<E> p){	
		if(p==null)
			throw new InvalidPositionException("posicion nula");
		if(isEmpty())
			throw new InvalidPositionException("la lista esta vacia");
		try {
		return (DNodo<E>)p;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("p no es un nodo de la lista valido");
		}
	}
	public Position<E> prev(Position<E> p) {
		DNodo<E>aux=checkPosition(p);
		if(aux.getPrevio()==header)
			throw new BoundaryViolationException("este es el primer nodo");	
		return aux.getPrevio();
	}
	@Override
	public void addFirst(E element) {
		DNodo<E>nuevo=new DNodo<E>(element);
		if(isEmpty()) {
			nuevo.setSiguiente(trailer);
			nuevo.setPrevio(header);
			header.setSiguiente(nuevo);
			trailer.setPrevio(nuevo);
		}
		else {
			DNodo<E>primero=checkPosition(first());
			nuevo.setSiguiente(primero);
			nuevo.setPrevio(header);
			header.setSiguiente(nuevo);
			primero.setPrevio(nuevo);
		}
		size++;
	}

	@Override
	public void addLast(E element) {
		if(isEmpty()) {
			//esto lo hago para ahorrar codigo
			addFirst(element);
		}
		else {
			DNodo<E>nuevo=new DNodo<E>(element);
			DNodo<E>ult=checkPosition(last());
			ult.setSiguiente(nuevo);
			nuevo.setPrevio(ult);
			nuevo.setSiguiente(trailer);
			size++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E element) {
		DNodo<E>nod=checkPosition(p);
		
		if(nod.equals(last()))
			addLast(element);
		else{
			DNodo<E>nuevo=new DNodo<E>(element);
			nod.getSiguiente().setPrevio(nuevo);
			nuevo.setSiguiente(nod.getSiguiente());
			nuevo.setPrevio(nod);
			nod.setSiguiente(nuevo);
			size++;
		}
	}

	@Override
	public void addBefore(Position<E> p, E element) {
		DNodo<E>nod=checkPosition(p);
		DNodo<E>nuevo=new DNodo<E>(element);
		
		if(nod.equals(first())){
			addFirst(element);
		}
		else {
			nod.getPrevio().setSiguiente(nuevo);
			nuevo.setPrevio(nod.getPrevio());
			nuevo.setSiguiente(nod);
			nod.setPrevio(nuevo);
			size++;
		}
	}

	@Override
	public E remove(Position<E> p) {
		DNodo<E>pos=checkPosition(p);
		E auxE=pos.element();
		
		pos.getPrevio().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setPrevio(pos.getPrevio());
		size--;
		
		return auxE;
	}

	@Override
	public E set(Position<E> p, E element) {
		DNodo<E>pos=checkPosition(p);
		E toReturn=pos.element();
		pos.setElemento(element);
		
		return toReturn;
	}
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toReturn = new ListaDobleMenteEnlazada<Position<E>>();
	    DNodo<E> nodo = header.getSiguiente();
	    while (nodo != trailer) {
	    	toReturn.addLast(nodo);
	        nodo = nodo.getSiguiente();
	    }
	    return toReturn;
	    }
}