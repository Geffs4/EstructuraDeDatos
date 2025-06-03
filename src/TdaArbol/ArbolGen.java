package TdaArbol;

import java.util.Iterator;
import Auxiliar.Position;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class ArbolGen<E> implements Tree<E> {
	//atributos de instancia
	protected TNodo<E>root;
	protected int size;
	
	//constructor
	public ArbolGen(){
		root=null;
		size=0;
	}
	private TNodo<E> checkPosition(Position<E> p){
		if(p==null){throw new InvalidPositionException("la posicion es nula");}
		if(isEmpty()){throw new InvalidPositionException("el arbol esta vacio");}
		try {
			return (TNodo<E>)p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("p no es un nodo valido");
			}
		}
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public Position<E> root(){
		if(isEmpty()){
			throw new EmptyTreeException("El arbol esta vacio en root()");
		}
		return root;
	}
	@Override
	public Iterator<E> iterator() {
		PositionList<E>exit=new ListaDoblementeEnlazada<E>();
		
		return exit.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>>exit=new ListaDoblementeEnlazada<Position<E>>();
		preOrdenPos(root,exit);
		return exit;
	}

	public E replace(Position<E> v, E e) {
		TNodo<E>nodo=checkPosition(v);
		E exit=nodo.element();
		nodo.setElemento(e);
		return exit;
	}

	public Position<E> parent(Position<E> v) {
		TNodo<E>nodo=checkPosition(v);
		return nodo.getPadre();
	}

	public Iterable<Position<E>> children(Position<E> v) {
		TNodo<E>nodo=checkPosition(v);
		PositionList<Position<E>>exit=new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E>n:nodo.getHijos()){
			exit.addLast(n);
		}		
		return exit;
	}
	
	public boolean isInternal(Position<E> v) {
		TNodo<E>nodo=checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}
	
	public boolean isExternal(Position<E> v) {
		TNodo<E> nodo=checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	public boolean isRoot(Position<E> v) {
		TNodo<E>nodo=checkPosition(v);
		return nodo==root;
	}

	public void createRoot(E e) {
		if(root!=null){
			throw new InvalidOperationException("ya existe una raiz ");
		}
		root=new TNodo<E>(e);
		size++;
	}

	public Position<E> addFirstChild(Position<E> p, E e) {
		TNodo<E>Pnodo=checkPosition(p);
		TNodo<E>nuevo=new TNodo<E>(e,Pnodo);
		Pnodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addLastChild(Position<E> p, E e) {
		TNodo<E>Pnodo=checkPosition(p);
		TNodo<E>nuevo=new TNodo<E>(e,Pnodo);
		Pnodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
	 	TNodo<E>NodoP=checkPosition(p);
		TNodo<E>nodoHead=checkPosition(rb);
		TNodo<E>nuevo=new TNodo<E>(e,NodoP);
		PositionList<TNodo<E>>sons=NodoP.getHijos();
		boolean encontre=false;
		Position<TNodo<E>>cursor=sons.first();
		while(cursor!=null&&!encontre) {
			if(nodoHead==cursor.element())
				encontre=true;
			else
				cursor=cursor!=sons.last()?sons.next(cursor):null;
		}
		if(!encontre){throw new InvalidPositionException("p no es padre de rb");}
		sons.addBefore(cursor,nuevo);
		size++;
		return nuevo;
	}
	
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
		TNodo<E>NodoP=checkPosition(p);
		TNodo<E>nodoHead=checkPosition(lb);
		TNodo<E>nuevo=new TNodo<E>(e,NodoP);
		PositionList<TNodo<E>>sons=NodoP.getHijos();
		
		boolean encontre=false;
		Position<TNodo<E>>cursor=sons.first();
		while(cursor!=null && !encontre){
			if(nodoHead==cursor.element()){
				encontre=true;
			}
			else cursor=cursor!=sons.last()? sons.next(cursor):null;
		}
		if(!encontre){throw new InvalidPositionException("p no es padre de lb");}
		sons.addAfter(cursor, nuevo);
		size++;
		return nuevo;
	}

	public void removeExternalNode(Position<E> p) {
		TNodo<E>nodo=checkPosition(p);
		if(isInternal(nodo)){
			throw new InvalidPositionException("p es un nodo interno");
		}
		if(nodo==root){
			root=null;
			size=0;
		}
		else{
			Iterator<Position<TNodo<E>>>sons=nodo.getPadre().getHijos().positions().iterator();
			Position<TNodo<E>>posBuscada=null;
			while(posBuscada==null && sons.hasNext());
				Position<TNodo<E>>cursor=sons.next();
				if(cursor.element()==nodo) {
					posBuscada=cursor;
				}
			nodo.getPadre().getHijos().remove(posBuscada);
			size--;
		}
		
	}

	public void removeInternalNode(Position<E> p) {
		TNodo<E>nodo=checkPosition(p);
		if(isExternal(nodo)) {
			throw new InvalidPositionException("p es un nodo externo");
		}
		if(nodo==root){
			if(nodo.getHijos().size()>1){
				throw new InvalidPositionException("p tiene mas de un hijo por lo tanto no se puede crear una nueva raiz");
			}
			this.root=root.getHijos().first().element();
			root.setPadre(null);
			size--;
		}
		else{
			Iterator<Position<TNodo<E>>>sons=nodo.getHijos().positions().iterator();
			Position<TNodo<E>>posBuscada=null;
			while(sons.hasNext()&&posBuscada==null){
				Position<TNodo<E>>cursor=sons.next();
				if(cursor.element()==nodo)
					posBuscada=cursor;
				}
			for(TNodo<E> hijo:nodo.getHijos()){
				nodo.getPadre().getHijos().addBefore(posBuscada,hijo);
				hijo.setPadre(nodo.getPadre());
			}
			nodo.getPadre().getHijos().remove(posBuscada);
			nodo.setPadre(null);
			size--;
		}
	}

	public void removeNode(Position<E> p) {
		TNodo<E>nodo=checkPosition(p);
		if(isInternal(nodo)){removeInternalNode(nodo);}	
		else{removeExternalNode(nodo);}
	}
	protected void preOrdenElem(TNodo<E> nodo,PositionList<E>list){
		list.addLast(nodo.element());
		for(TNodo<E>n:nodo.getHijos()){
			preOrdenElem(n, list);
		}
	}
	protected void preOrdenPos(TNodo<E> nodo,PositionList<Position<E>>list) {
		list.addLast(nodo);
		for(TNodo<E>n:nodo.getHijos())
			preOrdenPos(n,list);
	}
}
