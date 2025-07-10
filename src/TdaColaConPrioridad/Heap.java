package TdaColaConPrioridad;

import java.util.Comparator;

import Auxiliar.Entrada;
import Auxiliar.Entry;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

public class Heap<K, V> implements PriorityQueue<K, V> {
	// Atributos de instancia
	protected Entrada<K, V>[] elems;
	protected Comparator<K> comp;
	protected int size;

	@SuppressWarnings("unchecked")
	public Heap(int maxElems, Comparator<K> comp) {
		elems = (Entrada<K, V>[]) new Entrada[maxElems + 1]; // Ahora incluye posición 1
		this.comp = comp;
		size = 0;
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("La cola con prioridad está vacía");
		}
		return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		if (key == null) {
			throw new InvalidKeyException("Clave nula no permitida");
		}

		if (size + 1 >= elems.length) {
			resize(); // Para evitar desbordamiento
		}

		Entrada<K, V> entrada = new Entrada<>(key, value);
		elems[++size] = entrada;

		int i = size;
		while (i > 1 && comp.compare(elems[i].getKey(), elems[i / 2].getKey()) < 0) {
			swap(i, i / 2);
			i /= 2;
		}

		return entrada;
	}

	@Override
	public Entry<K, V> removeMin() {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("La cola con prioridad está vacía");
		}

		Entry<K, V> min = elems[1];
		elems[1] = elems[size];
		elems[size] = null;
		size--;

		heapifyDown(1);

		return min;
	}

	// Método auxiliar para heapify hacia abajo
	private void heapifyDown(int i) {
		while (true) {
			int hi = 2 * i;
			int hd = 2 * i + 1;
			int menor = i;

			if (hi <= size && comp.compare(elems[hi].getKey(), elems[menor].getKey()) < 0) {
				menor = hi;
			}
			if (hd <= size && comp.compare(elems[hd].getKey(), elems[menor].getKey()) < 0) {
				menor = hd;
			}

			if (menor == i)
				break;

			swap(i, menor);
			i = menor;
		}
	}

	// Método auxiliar para intercambiar dos entradas
	private void swap(int i, int j) {
		Entrada<K, V> temp = elems[i];
		elems[i] = elems[j];
		elems[j] = temp;
	}

	// Método opcional para duplicar el tamaño del array si se llena
	@SuppressWarnings("unchecked")
	private void resize() {
		Entrada<K, V>[] nuevo = (Entrada<K, V>[]) new Entrada[elems.length * 2];
		for (int i = 1; i <= size; i++) {
			nuevo[i] = elems[i];
		}
		elems = nuevo;
	}
}
