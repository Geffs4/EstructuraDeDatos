package TdaLista;

import java.util.Iterator;

import Auxiliar.Position;
import Auxiliar.PositionList;

public class ListaSimplementeEnlazada<E> implements PositionList<E> {
	//atributos de instancia
	protected Nodo<E> head;
	protected int size;
	
	//constructor
	public ListaSimplementeEnlazada(){
		head=null;
		size=0;
	}
	
	//metodos
	public void addFirst(E element){
		head=new Nodo<E>(element,head);
		size++;
	}
	public void addLast(E element) {
		if(isEmpty())
			addFirst(element);
		else {
			Nodo<E>Aux=head;
			while(Aux.getSiguiente()!=null);
				Aux=Aux.getSiguiente();
		
			Aux.setSiguiente(new Nodo<E>(element));
			size++;
		}
	}
	public void addAfter(Position<E> p,E element) {
			Nodo<E>n=checkPosition(p);
			Nodo<E>nuevo=new Nodo<E>(element);
			nuevo.setSiguiente(n.getSiguiente());
			n.setSiguiente(nuevo);
			size++;
	}
	public void addBefore(Position <E>p,E element) {
		checkPosition(p);
		if(p==first())
			addFirst(element);
		else {
			addAfter(prev(p),element);
		}
	}
	public E remove(Position<E> p) {
		Nodo<E> n=checkPosition(p);
		if(p==first())
			head=n.getSiguiente();
		else
			checkPosition(prev(p)).setSiguiente(n.getSiguiente());
		size--;
		
		E Aux=p.element();
		n.setElemento(null);
		n.setSiguiente(null);
		return Aux;
	}
	//consultas 
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public Position<E> first(){
		if(isEmpty())
			throw new Exceptions.EmptyListException("La lista esta vacia");
		return head;
	}
	public Position<E> last(){
		if(isEmpty())
			return first();
		else {
			Nodo<E>last=head;
			while(last.getSiguiente()!=null) {
				last=last.getSiguiente();
			}
			return last;	
		}
	}
	public Position<E> next(Position<E> p){
		Nodo<E> n=checkPosition(p);
		if(n.getSiguiente()==null)
			throw new Exceptions.BoundaryViolationException("el siguiente es null");
		
		 return n.getSiguiente();	
	}
	
	private Nodo<E> checkPosition(Position<E> p){
		try {
			if(p==null)
				throw new Exceptions.InvalidPositionException("Posicion nula");
			if(p.element()==null)
				throw new Exceptions.InvalidPositionException("P eleminada previamente");
			return (Nodo<E>)p;
		}
		catch(ClassCastException e) {
			throw new Exceptions.InvalidPositionException("p no es un nodo de la lista valido");
		}
	}
	public  Position<E> prev(Position<E> p){
		checkPosition(p);
		if(p==first())
			throw new Exceptions.BoundaryViolationException("p es el primer elemento");
		
		Nodo<E>Aux=head;
		while(Aux.getSiguiente()!=p&& Aux.getSiguiente()!=null)
			Aux=Aux.getSiguiente();
		if(Aux.getSiguiente()==null)
			throw new Exceptions.InvalidPositionException("Posicion no pertenece a La lista");
		
		return Aux;
	}
	@Override
	public E set(Position<E> p, E element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
}