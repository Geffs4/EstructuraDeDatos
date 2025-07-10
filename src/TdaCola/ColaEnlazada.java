package TdaCola;

import TdaLista.*;
import Exceptions.*;
import TdaCola.Queue;

public class ColaEnlazada<E> implements Queue<E> {

	protected Nodo<E> frente;
	protected Nodo<E> cola;
	protected int cantidad;

	public ColaEnlazada() {
		this.frente = null;
		this.cola = null;
		this.cantidad = 0;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    Nodo<E> actual = frente;
	    while (actual != null) {
	        sb.append(actual.element()).append(", ");
	        actual = actual.getSiguiente();
	    }

	    sb.append("null]");
	    return sb.toString();
	}

	@Override
	public int size() {
		return this.cantidad;
	}

	@Override
	public boolean isEmpty() {
		return this.cantidad == 0;
//Otra posibilidad es: return (frente==null && cola==null); 
	}

	@Override
	public E front() {
		if (this.isEmpty()) {
			throw new EmptyQueueException("Consultaste el front de una cola vacía.");
		}
		E resultado = this.frente.element();
		return resultado;
	}

	@Override
	public void enqueue(E element) {
//Caso general
		Nodo<E> nodo = new Nodo(element);
		if (!this.isEmpty()) { // Si la estructura no está vacía, solo hay que modificar el siguiente de la
								// cola.
			this.cola.setSiguiente(nodo);
		} else {
			this.frente = nodo;
		} // Esta instrucción es por si la cola está vacía
		this.cola = nodo; // Estas últimas instrucciones fuera del if y el else, es para optimizar,
		this.cantidad++; // dado que en cualquier caso se tienen que hacer, las hacemos fuera para todo
							// caso.
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new EmptyQueueException("Quisiste sacar un elemento de una cola vacía.");
		}
		E resultado = this.frente.element(); // Obtengo el elemento que está en el frente
		this.frente = this.frente.getSiguiente(); // El frente pasa a ser el siguiente al antiguo frente,
		this.cantidad--;// Si el siguiente es nulo, no hay problema, frente queda ligado a un nulo
		if (this.cantidad == 0) { // Si se vacío la cola al sacar un elemento, pongo nulo
			this.cola = null;
		}
		return resultado;
	}

//Ejercico 2 práctica parcial
	public Integer maximoEnCola(Queue<Integer> q) {
		if (q.isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		} else {
			Integer maximo = 0;
			for (int i = 0; i < q.size(); i++) {
				Integer elemento = q.dequeue();
				if (elemento > maximo) {
					maximo = elemento;
				}
				q.enqueue(elemento);
			}
			return maximo;
		}
	}

}
