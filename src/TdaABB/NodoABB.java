package TdaABB;

import Auxiliar.Entry;

public class NodoABB<K, V> {
	private Entry<K,V> entrada;
	private NodoABB<K,V> left,right,padre;
	
	public NodoABB(Entry<K,V> entrada){
		this.entrada=entrada;
		left=null;
		right=null;
		padre=null;
	}
	
	public void setElements(Entry<K,V> e){
		entrada=e;
	}
	public void setRight(NodoABB<K,V> right) {
		this.right=right;
	}
	public void setLeft(NodoABB<K,V>left) {
		this.left=left;
	}
	public void setPadre(NodoABB<K,V> p) {
		padre=p;
	}
	public Entry<K,V> getElement(){return entrada;}
	public NodoABB<K,V> getRight(){return right;}
	public NodoABB<K,V> getLeft(){return left;}
	public NodoABB<K,V> getPadre(){return padre;}
}
