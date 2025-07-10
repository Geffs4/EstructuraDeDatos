package TP8;

import TdaCola.*;
import TdaGrafo.*;
import TdaLista.PositionList;
import TdaMapeo.*;
import TdaLista.ListaDoblementeEnlazada;

public class Ejercicio5 {
	public static void main(String[] args) {
		Grafo<String, Integer> grafo = new Grafo<String, Integer>();
		Vertex<String> A = grafo.insertVertex("A");
		Vertex<String> B = grafo.insertVertex("B");
		Vertex<String> C = grafo.insertVertex("C");
		Vertex<String> D = grafo.insertVertex("D");
		Vertex<String> E = grafo.insertVertex("E");
		Vertex<String> F = grafo.insertVertex("F");
		Vertex<String> G = grafo.insertVertex("G");
		Vertex<String> H = grafo.insertVertex("H");
		Vertex<String> I = grafo.insertVertex("I");

		grafo.insertEdge(A, B, 1);
		grafo.insertEdge(A, C, 1);
		grafo.insertEdge(A, F, 1);
		grafo.insertEdge(B, E, 2);
		grafo.insertEdge(C, D, 2);
		grafo.insertEdge(B, G, 2);
		grafo.insertEdge(B, D, 2);
		grafo.insertEdge(E, H, 3);
		grafo.insertEdge(E, I, 3);

		System.out.println(hayCamino(grafo, A, G));

	}

	public static <V, E> String hayCamino(Grafo<V, E> grafo, Vertex<V> v1, Vertex<V> v2) {
		Map<Vertex<V>, Boolean> visitado = new MapeoConLista<Vertex<V>, Boolean>();
		Map<Vertex<V>, Vertex<V>> padre = new MapeoConLista<Vertex<V>, Vertex<V>>();
		for (Vertex<V> nodo : grafo.vertices()) {
			visitado.put(nodo, false);
			padre.put(nodo, null);
		}
		Queue<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		cola.enqueue(v1);
		visitado.put(v1, true);

		boolean encontre=false;
		while (!encontre && !cola.isEmpty()) {
			Vertex<V> actual = cola.dequeue();
			for (Edge<E> arco : grafo.incidentEdges(actual)) {
				Vertex<V> vecino = grafo.opposite(actual, arco);
				if (!visitado.get(vecino)){
					visitado.put(vecino,true);
					padre.put(vecino, actual);
					cola.enqueue(vecino);
				}
				if (vecino == v2) {
                    encontre = true;

				}
			}
		}
		PositionList<V>camino=new ListaDoblementeEnlazada<V>();
		 Vertex<V> actual = v2;
		    while (actual != null) {
		        camino.addFirst(actual.element());
		        actual = padre.get(actual);
		}
		return encontre+" "+camino.toString();
	}
}
