package TdaArbol;

import Auxiliar.Position;
import TdaLista.PositionList;
import TdaLista.ListaDoblementeEnlazada;

public class TNodo<E> implements Position<E> {
	//atributos de instancia
	private E elemento;
	private TNodo<E> padre;
	private PositionList<TNodo<E>> hijos;
	
	//constructor
	public TNodo(E element,TNodo<E> padre){
		elemento=element;
		this.padre=padre;
		hijos=new ListaDoblementeEnlazada<TNodo<E>>();
	}
	public TNodo(E element){
		this(element,null);
	}
	//getters
	public E element() {
		return elemento;
	}
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	public TNodo<E> getPadre(){
		return padre;
	}
	//setters
	public void setElemento(E element){
		elemento=element;
	}
	public void setPadre(TNodo<E> padre) {
		this.padre=padre;
	}
}
