package TdaCola;

public class ColaConArreglo <E>implements Queue<E>{
	//atributos de instancia
	private E []Cola;
	private int head;//es el primero en salir 
	private int tail;//es el utlimo en salri(funciona como el cant en una coleccion ordenada)
	private int size;
	//Constructor
	@SuppressWarnings("unchecked")
	public ColaConArreglo(int max) {
		Cola=(E[])new Object[max];
		head=0;
		tail=0;
		size=0;
	}
	public ColaConArreglo() {
		this(20);
	}
	
	//metodo 
	public void enqueue(E element) {
		if(size()==Cola.length) {
			incrementar(size()+10);
		}
			Cola[tail]=element;
			tail=(tail+1)%Cola.length;
			size++;
		}
	
	private void incrementar(int nuevaCapacidad) {
		@SuppressWarnings("unchecked")
		E[] Mem= (E[]) new Object[nuevaCapacidad];
		for (int i = 0; i < size(); i++) {
			Mem[i] = Cola[(head + i) % Cola.length];
			//esto es una manera de recorrelo hasta el tail y ordenarlo
		}
		Cola = Mem;
		head = 0;
		tail = size();
	}
	//consultas
	public int size(){
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public E front(){
		if(isEmpty()) {
			throw new Exceptions.EmptyQueueException("la cola esta vacia");
		}
		return Cola[head];
	}
	public E dequeue(){
		E toReturn=front();
		Cola[head]=null;
		head=(head+1)%Cola.length;
		size--;
		return toReturn;
	}
}
