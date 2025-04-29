package TdaLista;

public class DNodo<E> implements Position<E>{
	//atrbutos de instancia
	private DNodo<E> prev;
	private DNodo<E> next;
	private E element;
	
	//Constructor 
	public DNodo(DNodo<E> previo,E elem) {
		prev=previo;
		next=null;
		element=elem;
	}
	public DNodo(E elem){
		this(null,elem);
	}
	public DNodo() {
		this (null);
	} 
	//metoodos
	public void setElemento(E elemento) {
			element=elemento;
	}
	public void setSiguiente(DNodo<E> siguiente) {
		next=siguiente;
	}
	public void setPrevio(DNodo<E> previo) {
		prev=previo;
	}
	
	//Consultas
	public E element() {
		return element;
	}
	public DNodo<E> getSiguiente(){
		return next;
	}
	public DNodo<E> getPrevio(){
		return prev;
	}
}
