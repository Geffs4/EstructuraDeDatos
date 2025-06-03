package TdaPila; 

public class PilaEnlazada<E> implements Stack<E>{
	//atributos de instancia
	protected Nodo<E> top;
	protected int size;
	//constructor 
	public PilaEnlazada(){
		top=null;
		size=0;
		}
	//comandos
	public void push(E element) {
		top = new Nodo<E>(element,top);
		size++;
	}
	//consultas 
	public int size(){
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public E top() {
		if (isEmpty())
			throw new Exceptions.EmptyStackException("la pila esta vacia");
		return top.getElemento();
	}
	public E pop(){
		E aux=top();
		top = top.getSiguiente();
		size--;
		return aux;
		}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("PilaEnlazada [");

	    Nodo<E> actual = top;
	    while (actual != null) {
	        sb.append(actual.getElemento());
	        if (actual.getSiguiente() != null) {
	            sb.append(", ");
	        }
	        actual = actual.getSiguiente();
	    }

	    sb.append("]");
	    return sb.toString();
	}	
	}
