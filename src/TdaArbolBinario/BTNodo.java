package TdaArbolBinario;

import Auxiliar.Position;

public class BTNodo<E> implements Position<E> {
	//atributos de istancia
	protected E element;
	protected BTNodo<E>padre,left,right;
	
	//constructor 
	public BTNodo(E elem,BTNodo<E>hi,BTNodo<E>hd,BTNodo<E>pd){
		element=elem;
		left=hi;
		right=hd;
		padre=pd;
	}
	public BTNodo(E elem,BTNodo<E>hi,BTNodo<E>hd){
		//constructor de la raiz binaria
		this(elem,hi,hd,null);
	}
	public BTNodo(E elem){
		this(elem,null,null);
	}
	//setters
	public void setElement(E elem){
		element=elem;
	}
	public void setLeft(BTNodo<E> left){
		this.left=left;
	}
	public void setRight(BTNodo<E> right){
		this.right=right;
	}
	public void setParent(BTNodo<E> parent){
		this.padre=parent;
	}
	//getters
	public E element(){
		return element;
	}
	public BTNodo<E> getParent(){
		return padre;
	}
	public BTNodo<E> getRight(){
		return right;
	}
	public BTNodo<E> getLeft(){
		return left;
	}
}
