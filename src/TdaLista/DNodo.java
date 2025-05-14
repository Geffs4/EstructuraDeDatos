package TdaLista;

import Auxiliar.Position;

public class DNodo<E> implements Position<E>{
	//atrbutos de instancia
	private DNodo<E> prev;
	private DNodo<E> next;
	private E element;
	
	//Constructor 
	public DNodo(E elem,DNodo<E>previo,DNodo<E>siguiente) {
		prev=previo;
		next=siguiente;
		element=elem;
	}	
		
	public DNodo(E elem,DNodo<E> previo) {
		this(elem,previo,null);
	}
	public DNodo(E elem){
		this(elem,null);
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
