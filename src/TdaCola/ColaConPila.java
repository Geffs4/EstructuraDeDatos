package TdaCola;
import java.util.Stack;

public class ColaConPila<E> implements Queue<E> {
	//atributos de instancia 
	private Stack<E> Pila;
	/*una pila el primer el elemento en entrar es 
	 * es el ultimo en salir 
	 */
	//constructor
	public ColaConPila() {
		Pila=new Stack<E>();
	}
	
	//metodos
	public void enqueue(E element) {
		Pila.push(element);
	}
	private void invertir() {
		Stack<E> mem=new Stack<E>();
		int size=Pila.size();
		for(int i=0;i<size;i++)
			mem.push(Pila.pop());
		
		Pila=mem;
	}
	//consultas
	public int size() {
		return Pila.size();
	}
	public boolean isEmpty() {
		return Pila.isEmpty();
	}
	public E front(){
		/*para sacar el primer elemento que ingreso tengo
		 *que sacar todos los elementos
		 *por lo tanto creo un mentodo invertir
		 *para que ese ultimo elemento pase ahora a ser el primero y despues
		 *lo devuelvo a su estado
		 */
		if(isEmpty())
			throw new Exceptions.EmptyQueueException("la cola esta vacia");
		invertir();
		E toReturn=Pila.peek();
		invertir();
		return toReturn;
	}
	public E dequeue(){
		if(isEmpty())
			throw new Exceptions.EmptyQueueException("la cola esta vacia");
		invertir();
		E toReturn=Pila.pop();
		invertir();
		return toReturn;
	}
	
	
}
