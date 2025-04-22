package TdaPila; 

public class PilaEnlazada<E> implements Stack<E>{
	//atributos de instancia
	protected Nodo<E> head;
	protected int size;
	//constructor 
	public PilaEnlazada(){
		head=null;
		size=0;
		}
	//comandos
	public void push(E element) {
		Nodo<E>Aux= new Nodo<E>(element,head);
		head=Aux;
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
		return head.getElemento();
	}
	public E pop(){
			E aux=top();
			head = head.getSiguiente();
			size--;
			return aux;
		}
	}
