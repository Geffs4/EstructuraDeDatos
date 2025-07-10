package TdaLista;

import java.util.Iterator;
import Auxiliar.Position;
import Exceptions.*;


public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	//atributos de intancia
	protected DNodo<E> header;
	protected DNodo<E> trailer;
	protected int size;
	
	//constructor
	public ListaDoblementeEnlazada() {
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
		DNodo<E>nuevo=new DNodo<E>(element,trailer.getPrevio(),trailer);
		trailer.getPrevio().setSiguiente(nuevo);
		trailer.setPrevio(nuevo);
		size++;
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
        PositionList<Position<E>> toReturn = new ListaDoblementeEnlazada<Position<E>>();
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

//ejercicio de la clase 8 
	public ListaDoblementeEnlazada<E> clone(){
		ListaDoblementeEnlazada<E>nueva= new ListaDoblementeEnlazada<E>();
		DNodo<E>p=header.getSiguiente();
		while(p!=trailer) {
			nueva.addLast(p.element());
			p=p.getSiguiente();
		}
	return nueva;
	}
	public void removeAllE(E elem){
		Position<E>p=header.getSiguiente();
		while(p!=trailer)
			if(p.element().equals(elem))
				remove(p);
			p=next(p);	
	}
	public void atp(E elemen) {
		DNodo<E>p=header.getSiguiente();
		while(p!=trailer) {
			if(!p.element().equals(elemen))
				addBefore(p, elemen);
			p=p.getSiguiente();
		}
	}
	public int duplicarElem(E elem){
		if(header.getSiguiente()==trailer)
			throw new EmptyListException("La lista esta vacia");
		
		int contar=0;
		DNodo<E>d;
		DNodo<E>p=header.getSiguiente();
		while(p!=trailer){
			d=p;
			if(p.element().equals(elem)){
				d=new DNodo<E>(elem);
				d.setSiguiente(p.getSiguiente());
				d.setPrevio(p);
				p.getSiguiente().setPrevio(d);
				p.setSiguiente(d);
				contar++;
			}
			p=d.getSiguiente();
		}
		return contar;
	}
	public void encontrarEliminar(E elem){
		if(elem==null){
			throw new InvalidPositionException("El elemento pasado por parametro es nulo");
		}
		if(elem!=trailer.getPrevio().element()){
			DNodo<E>cursor=header.getSiguiente();
			int contar=0;
			while(cursor!=trailer){
				contar++;
				if(cursor.element().equals(elem)){
					cursor.getSiguiente().setPrevio(null);
					cursor.setSiguiente(trailer);
					trailer.getPrevio().setSiguiente(null);
					trailer.setPrevio(cursor);
				}
				cursor=cursor.getSiguiente();		
			}
			size=contar;
		}
	}
}
