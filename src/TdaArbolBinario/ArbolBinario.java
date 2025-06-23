package TdaArbolBinario;

import TdaDiccionario.*;
import java.util.Iterator;

import Auxiliar.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class ArbolBinario<E> implements BinaryTree<E> {
	//atributos de instancia
	protected int size;
	protected BTNodo<E> root ;
	
	//constuctor 
	public ArbolBinario(){
		size=0;
		root=null;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Iterator<E> iterator() {
		PositionList<E>exit=new ListaDoblementeEnlazada<E>();
		preOrdenElem(root,exit);
		return exit.iterator();
	}

	private void preOrdenElem(BTNodo<E> nodo, PositionList<E> list){
		if(nodo!=null) {
			list.addLast(nodo.element);
			preOrdenElem(nodo.getLeft(),list);
			preOrdenElem(nodo.getRight(),list);
		}
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>>exit=new ListaDoblementeEnlazada<Position<E>>();
		preOrdenPos(root,exit);
		return exit;
	}

	private void preOrdenPos(BTNodo<E> nodo, PositionList<Position<E>> list) {
		if(nodo!=null){
			list.addLast(nodo);
			preOrdenPos(nodo.getLeft(),list);
			preOrdenPos(nodo.getRight(),list);
		}
		
	}
	protected BTNodo<E> checkPosition(Position<E> v){
		if(isEmpty()){throw new InvalidPositionException("Posicion invalida:arbol vacio");}
		if(v==null) {throw new InvalidPositionException("Posicion invalid:nodo nulo");}
		if(v.element()==null) {throw new InvalidPositionException("Posicion Invalida:posicion eliminda previamente");}
		try {
			return (BTNodo<E>)v;
		}catch(ClassCastException e) {throw new InvalidPositionException("Posicion invalida:nodo no perteneciente al del tipo del arbol");}
	}
	
	public E replace(Position<E> v, E e) {
		BTNodo<E>nodo=checkPosition(v);
		E exit=nodo.element;
		nodo.setElement(e);
		return exit;
	}

	
	public Position<E> root() {
		if(isEmpty()){throw new EmptyTreeException("El arbol esta vacio");}
		return root;
	}

	public Position<E> parent(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		if(nodo==root){throw new BoundaryViolationException("El nodo es la raiz");}
		return nodo.getParent();
	}
	
	public Iterable<Position<E>> children(Position<E> v) {
		PositionList<Position<E>>exit=new ListaDoblementeEnlazada<Position<E>>();
		BTNodo<E>nodo=checkPosition(v);
		if(nodo.getLeft()!=null) {
			exit.addLast(nodo.getLeft());
		}
		if(nodo.getRight()!=null) {
			exit.addLast(nodo.getRight());
		}
		return exit;
	}

	public boolean isInternal(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		return nodo.getLeft()!=null||nodo.getRight()!=null;
	}

	public boolean isExternal(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		return nodo.getLeft()==null && nodo.getRight()==null; 
	}

	@Override
	public boolean isRoot(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		return nodo==root;
	}

	@Override
	public void createRoot(E e) {
		if(root!=null){throw new InvalidOperationException("ya exite una raiz");}
		root=new BTNodo<E>(e);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) {
		return null;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeExternalNode(Position<E> p) {
		BTNodo<E>nodo=checkPosition(p);
		if(isInternal(nodo)){throw new InvalidPositionException("p es un nodo interno");}
		if(nodo==root){
			root=null;
			size=0;
		}else {
			BTNodo<E>parent=nodo.getParent();
			if(parent.getLeft()==nodo){
				parent.setLeft(null);
			}
			else if(parent.getRight()==nodo) {
				parent.setRight(nodo);
			}
			size--;
		}
	}

	@Override
	public void removeInternalNode(Position<E> p) {
		BTNodo<E> nodo = checkPosition(p);

	    if (isExternal(nodo)) {
	        throw new InvalidPositionException("p es un nodo externo");
	    }

	    BTNodo<E> left = nodo.getLeft();
	    BTNodo<E> right = nodo.getRight();

	    // Solo permitimos eliminar nodos con un solo hijo
	    if (left != null && right != null) {
	        throw new InvalidPositionException("p tiene dos hijos; no se puede eliminar directamente");
	    }

	    BTNodo<E> child = (left != null) ? left : right;

	    if (nodo == root) {
	        root = child;
	        child.setParent(null);
	    } else {
	        BTNodo<E> parent = nodo.getParent();
	        if (parent.getLeft() == nodo) {
	            parent.setLeft(child);
	        } else {
	            parent.setRight(child);
	        }
	        child.setParent(parent);
	    }

	    size--;
	  
		
	}

	@Override
	public void removeNode(Position<E> p) {
		BTNodo<E>nodo=checkPosition(p);
		if(isInternal(nodo)){removeInternalNode(nodo);}	
		else{removeExternalNode(nodo);}
	}
	@Override
	public Position<E> left(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		if(nodo.getLeft()==null){throw new BoundaryViolationException("No tiene hijo izquierdo");}
		return nodo.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		if(nodo.getRight()==null){throw new BoundaryViolationException("No tiene hijo derecho");}
		return nodo.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		return nodo.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) {
		BTNodo<E>nodo=checkPosition(v);
		return nodo.getRight()!=null;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) {
		BTNodo<E>nodo=checkPosition(v);
		if(hasLeft(nodo)){throw new InvalidOperationException("el nodo ya tiene un hijo izquierdo");}
		BTNodo<E>left=new BTNodo<E>(r,null,null,nodo);
		nodo.setLeft(left);
		size++;
		return left;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) {
		BTNodo<E>nodo=checkPosition(v);
		if(hasRight(nodo)){throw new InvalidOperationException("el nodo ya tiene un hijo derecho");}
		BTNodo<E>right=new BTNodo<E>(r,null,null,nodo);
		nodo.setRight(right);
		size++;
		return right;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) {
		BTNodo<E>nodo=checkPosition(r);
		if(!isExternal(nodo)) {throw new InvalidPositionException("La posición no corresponde a una hoja.");}
		if(isExternal(nodo)){
			if(!T1.isEmpty()){
				BTNodo<E>root1=checkPosition(T1.root());
				nodo.setLeft(root1);
				size=size+T1.size();
			}
			if(!T2.isEmpty()){
				BTNodo<E>root2=checkPosition(T2.root());
				nodo.setRight(root2);
				size=size+T2.size();
			}
		}
	}
	//Ejercicio-2
	public Dictionary<E,E> diccionario(){
		Dictionary<E,E>dic=new DiccionarioConLista<E, E>();
		preOrden(root,dic);
		return dic;
		
	}
	public String toString(){
		if(isEmpty())return "[]";
		return "["+ String(root)+"]";
	}

	private String String(BTNodo<E> nodo){
		String result =nodo.element().toString();
	    if (!isExternal(nodo)) {
	        result += "(";
	        Iterator<Position<E>> it = children(nodo).iterator();
	        while (it.hasNext()) {
	            result += String((BTNodo<E>) it.next());
	            if (it.hasNext()) result += ",";
	        }
	        result += ")";
	    }
	    return result;
	}

	private void preOrden(BTNodo<E> nodo, Dictionary<E, E> dic) {
		if(nodo!=null){
			if(nodo!=root) {
				dic.insert(nodo.getParent().element(),nodo.element());
			}	
				preOrden(nodo.getLeft(), dic);
				preOrden(nodo.getRight(),dic);
		}
	}
}