package TdaLista;

public class Nodo<E> implements Position<E> {
	private E elemento;
	private Nodo<E> siguiente;
// Constructores:
	public Nodo( E item, Nodo<E> sig ){
		elemento=item; 
		siguiente=sig;
	}
	public Nodo( E item ) { 
		this(item,null);
	}
// Setters:
	public void setElemento( E elemento ){
		this.elemento = elemento; 
	}
	public void setSiguiente( Nodo<E> siguiente ){
		this.siguiente = siguiente; 
	}
// Getters:
	public E element() { 
		return elemento; 
	}
	public Nodo<E> getSiguiente() { 
		return siguiente; 
	}
}