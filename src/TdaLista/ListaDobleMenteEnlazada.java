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
		if(aux==first())
			throw new BoundaryViolationException("este es el primer nodo");
		
		return aux.getPrevio();
	}
	
	@Override
	public void addFirst(E element) {
		DNodo<E>nuevo=new DNodo<E>(element,header);
		if(isEmpty()) {
			nuevo.setSiguiente(trailer);
			trailer.setPrevio(nuevo);
		}
		else {
			nuevo.setSiguiente(header.getSiguiente());
			header.getSiguiente().setPrevio(nuevo);		
			}
		header.setSiguiente(nuevo);
		size++;
	}
	@Override
	public void addLast(E element) {
		if(isEmpty()) {
			addFirst(element);
		}
		else {
			DNodo<E>nuevo=new DNodo<E>(element,trailer.getPrevio(),trailer);
			trailer.getPrevio().setSiguiente(nuevo);
			trailer.setPrevio(nuevo);
			size++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E element) {
		DNodo<E>nod=checkPosition(p);
		
		if(nod.equals(last()))
			addLast(element);
		else{
			DNodo<E>nuevo=new DNodo<E>(element,nod,nod.getSiguiente());
			nod.getSiguiente().setPrevio(nuevo);
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
		pos.setPrevio(null);
		pos.setSiguiente(null);
		
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

	public String toString() {
		Iterator<E> it = iterator(); 
		String s= "[";
		while( it.hasNext()){
			s= s + it.next(); 
			if(it.hasNext())
				s= s+" , ";
		}
		s= s+"]";
		return s;
	}
//tp4-2
	public void addSA(E e1, E e2) {
		if(isEmpty())
			throw new EmptyListException("La lista esta vacia");
		if(size==1) {
			throw new BoundaryViolationException("la lista solo tiene un elemento");
		}	
		addBefore(last(),e2);
		addAfter(first(),e1);
	}
//tp4-3
	public boolean belong(E e1) {
		Iterator<E>it=iterator();
		boolean encontre=false;
		
		while(it.hasNext()&&!encontre)
			encontre=it.next().equals(e1);
		
		return encontre;
	}
	public int cantElements(E e1) {
		int Cont=0;
		Iterator<E>it=iterator();
		while(it.hasNext())
			if(it.next().equals(e1))
				Cont++;
		
		return Cont;
	}
	
}