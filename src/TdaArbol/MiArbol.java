package TdaArbol;

import java.util.Iterator;

import Auxiliar.Position;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class MiArbol<E> implements Tree<E> {

	protected TNodo<E> root;
	protected int size;
	
	public MiArbol() {
		this.size = 0;
		this.root = null;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size==0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		preordenElementos(root, resultado);
		return resultado.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		preordenPositions(root, resultado);
		return resultado;
	}

	@Override
	public E replace(Position<E> v, E e) {
		TNodo<E> nodo = checkPosition(v);
		E resultado = nodo.element();
		nodo.setElemento(e);
		return resultado;
	}

	private TNodo<E> checkPosition(Position<E> v) {
		TNodo<E> resultado = null;
		if( v == null) {throw new InvalidPositionException("Posicion es nula");}
		if( this.isEmpty()) {throw new InvalidPositionException("El árbol está vacío");}
		try {
			resultado = (TNodo<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		return resultado;
	}

	@Override
	public Position<E> root() {
		if(this.isEmpty()) {throw new EmptyTreeException("El árbol está vacío");}
		return this.root;
	}

	@Override
	public Position<E> parent(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E> n : nodo.getHijos()) {
			resultado.addLast(n);
		}
 		return resultado;
	}

	@Override
	public boolean isInternal(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		return this.root == nodo;
	}

	@Override
	public void createRoot(E e) {
		if(this.root != null) {throw new InvalidOperationException("El árbol ya tiene una raíz");}
		else {
			this.root = new TNodo<E>(e, null);
			this.size++;
		}
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e, nodo);
		nodo.getHijos().addFirst(hijo);
		return hijo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e, nodo);
		nodo.getHijos().addLast(hijo);
		return hijo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hder = checkPosition(rb);
		if(hder.getPadre()!= padre ) {throw new InvalidPositionException("El padre no es el verdadero padre");}
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		Iterator<Position<TNodo<E>>> ite = padre.getHijos().positions().iterator();
		Position<TNodo<E>> posBuscada = null;
		while(ite.hasNext()&&posBuscada==null) {
			Position<TNodo<E>> pos = ite.next();
			if(pos.element() == hder ) {posBuscada = pos;}
		}
		padre.getHijos().addBefore(posBuscada, nuevo);
		this.size++;
		return nuevo;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hder = checkPosition(lb);
		if(hder.getPadre()!= padre ) {throw new InvalidPositionException("El padre no es el verdadero padre");}
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		Iterator<Position<TNodo<E>>> ite = padre.getHijos().positions().iterator();
		Position<TNodo<E>> posBuscada = null;
		while(ite.hasNext()&&posBuscada==null) {
			Position<TNodo<E>> pos = ite.next();
			if(pos.element() == hder ) {posBuscada = pos;}
		}
		padre.getHijos().addAfter(posBuscada, nuevo);
		this.size++;
		return nuevo;
	}

	@Override
	public void removeExternalNode(Position<E> p) {
		TNodo<E> nodo = checkPosition(p);		
		if(this.isInternal(nodo)) {throw new InvalidPositionException("El nodo no es externo");}
		if(this.root == nodo) {
			//el nodo externo es la raiz
			this.root = null;
			this.size = 0;
		} else {
			Iterator<Position<TNodo<E>>> ite = nodo.getPadre().getHijos().positions().iterator();
			Position<TNodo<E>> posBuscada = null;
			while(ite.hasNext()&&posBuscada==null) {
				Position<TNodo<E>> pos = ite.next();
				if(pos.element() == nodo ) {posBuscada = pos;}
			}
			nodo.getPadre().getHijos().remove(posBuscada);
			this.size--;
		}
	}

	@Override
	public void removeInternalNode(Position<E> p) {
		TNodo<E> nodo = checkPosition(p);
		if( this.isExternal(nodo)) {throw new InvalidPositionException("No es un nodo interno");}
		if(this.root == nodo ) {
			if(this.root.getHijos().size()>1){throw new InvalidPositionException("No puedo eliminar la raiz porque tiene muchos hijos");}
			this.root = this.root.getHijos().first().element();
			this.root.setPadre(null);
			this.size--;
		} else {
			Iterator<Position<TNodo<E>>> ite = nodo.getPadre().getHijos().positions().iterator();
			Position<TNodo<E>> posBuscada = null;
			while(ite.hasNext()&&posBuscada==null) {
				Position<TNodo<E>> pos = ite.next();
				if(pos.element() == nodo ) {posBuscada = pos;}
			}
			//posBuscada es la posicion en la lista del padre, del nodo que quiero eliminar
			for(TNodo<E> hijo : nodo.getHijos()) {
				nodo.getPadre().getHijos().addBefore(posBuscada, hijo);
				hijo.setPadre(nodo.getPadre());
			}
			nodo.getPadre().getHijos().remove(posBuscada);
			nodo.setPadre(null);
			this.size--;
		}
	}

	@Override
	public void removeNode(Position<E> p) {
		TNodo<E> nodo = checkPosition(p);
		if(this.isInternal(nodo)) {this.removeInternalNode(p);}
		else {this.removeExternalNode(p);}
	}
	
	protected void preordenElementos( TNodo<E> nodo, PositionList<E> lista ) {
		lista.addLast(nodo.element()); //Visitar nodo
		for(TNodo<E> n : nodo.getHijos()) {
			preordenElementos(n, lista);
		}
	}
	
	protected void preordenPositions( TNodo<E> nodo, PositionList<Position<E>> lista ) {
		lista.addLast(nodo);
		for(TNodo<E> n : nodo.getHijos()) {
			preordenPositions(n, lista);
		}
	}

	
	protected int aparicionesAuxiliar( TNodo<E> n, E e ) {
		int cont = 0;
		if(n.element().equals(e)) { cont++; }
		for(TNodo<E> nodo : n.getHijos()) {
			cont = cont + aparicionesAuxiliar(nodo, e);
		}
		return cont;
	}

}
