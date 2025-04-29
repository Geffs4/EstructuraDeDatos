package TdaLista;

import java.util.Iterator;

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
			throw new Exceptions.EmptyListException("la lista esta vacia");
		return header.getSiguiente();
	}
	public Position<E> last(){
		if(isEmpty())
			throw new Exceptions.EmptyListException("la lista esta vacia");
		return trailer.getPrevio();
	}
	public Position<E> next(Position<E> p) {
		DNodo<E>aux=checkPosition(p);
			if(aux.getSiguiente()==trailer)
				throw new Exceptions.InvalidPositionException("este es el ultimo elemento");
		return aux.getSiguiente();
	}
	private DNodo<E> checkPosition(Position<E> p){
		try {
		if(p==null)
			throw new Exceptions.InvalidPositionException("posicion nula");
			if(p.element()==null)
				throw new Exceptions.InvalidPositionException("posicion no valida");
		return (DNodo<E>)p;
		}catch(ClassCastException e) {
			throw new Exceptions.InvalidPositionException("p no es un nodo de la lista valido");
		}
	}
	public Position<E> prev(Position<E> p) {
		DNodo<E>aux=checkPosition(p);
		if(aux.getSiguiente()==header)
			throw new Exceptions.InvalidPositionException("este es el primer nodo");
		
		return aux.getSiguiente();
	}

	@Override
	public void addFirst(E element) {
		

	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAfter(Position<E> p, E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBefore(Position<E> p, E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public E remove(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(Position<E> p, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

}
