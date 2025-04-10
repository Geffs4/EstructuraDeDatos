package TDAConjunto;

public interface Conjunto<E>{
	public int size();
	//Retorna la cantidad de elementos del conjunto.
	public int capacity();
	//Retorna la capacidad máxima del conjunto.
	public boolean isEmpty(); 
	//Retorna verdadero si y sólo si el conjunto está vacío.
	public E get(int i);
	//retorna el i-ésimo elemento del conjunto. Requiere que la posición sea válida.
	public void put(E elem);
	//Agrega un elemento al conjunto. Requiere que el conjunto no esté lleno y que el elemento no se
	//encuentre en el conjunto. La comparación se realiza por equivalencia.
	public boolean pertenece(E elem);
	// Retorna verdadero si y sólo si el elemento elem se encuentra en el conjunto. La comparación
	//se realiza por equivalencia.
	public  Conjunto<E> intersección(Conjunto<E> c);
	// Retorna un nuevo conjunto producto de intersectar los elementos del conjunto que recibe el
	//mensaje y el pasado por parámetro.
}