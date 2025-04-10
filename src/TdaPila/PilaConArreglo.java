package TdaPila;

public class PilaConArreglo<E> implements Stack<E> {
	//atributos de instancia
	private E []Pila;
	private int cant;
	
	//constructor
	@SuppressWarnings("unchecked")
	public PilaConArreglo(int Max){
		Pila=(E[])new Object[Max];
	}
	public PilaConArreglo(){
		this(20);
	}
	
	public int size() {
		return cant;
	}
	public boolean isEmpty() {
		return cant==0;
	}
	public E top() {
		if(isEmpty())
			throw new Exceptions.EmptyStackException("la pila esta vacia");
		return Pila[cant-1];
		}
	private void Incrementar(int Max,int Incremento) {
		@SuppressWarnings("unchecked")
		E []Aux=(E[])new Object[Max+Incremento];
		for(int i=0;i<cant;i++)
			Aux[i]=Pila[i];
		Pila=Aux;
	}
	public void push(E element) {
		/*Verificar si la pila no esta llena 
		 * 	si esta llena crear una nuevo arreglo mas grande
		 * 	y copiar el anterior en el
		 * 		despues de eso agregar el elemento y aumentar el cant en 1
		 * 	sino
		 * 		agregar el elemento 
		 * 		y aumentar el cant en uno
		 */
		if(cant==Pila.length) {
			Incrementar(cant,10);
			Pila[cant]=element;
			cant++;
		}
		else {
			Pila[cant]=element;
			cant++;
		}
	}
	public E pop() {
		E toReturn=top();
		Pila[cant-1]=null;
		cant--;
		return toReturn;
	}
}
