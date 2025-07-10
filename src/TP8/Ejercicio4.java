package TP8;

import TdaCola.ColaEnlazada;
import TdaCola.Queue;
import TdaGrafo.*;
import TdaMapeo.Map;
import TdaMapeo.MapeoConLista;

public class Ejercicio4 {
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

		System.out.println(grafo);
		System.out.println(caminoMasCorto(grafo, A, G));
	}

	private static <V, E> int caminoMasCorto(Grafo<V, E> grafo, Vertex<V> v, Vertex<V> w) {
		// nodos visitados este es su control
		Map<Vertex<V>,Boolean>map=new MapeoConLista<Vertex<V>, Boolean>();
		Map<Vertex<V>,Integer>mapDistancia=new MapeoConLista<Vertex<V>, Integer>();
		for(Vertex<V>temp:grafo.vertices()) {
			map.put(temp,false);
			mapDistancia.put(temp,-1);
		}
		Queue<Vertex<V>>cola=new ColaEnlazada<Vertex<V>>();
		cola.enqueue(v);
		mapDistancia.put(v,0);
		map.put(v, true);
		Vertex<V>actual=null;
		boolean encontre=false;
		int distancia=-1;
		while(!cola.isEmpty()&&!encontre) {
			actual=cola.dequeue();
			for(Edge<E>arco:grafo.incidentEdges(actual)){
			Vertex<V>opuesto=grafo.opposite(actual, arco);
				if(!map.get(opuesto)){
					map.put(opuesto,true);
					mapDistancia.put(opuesto,mapDistancia.get(actual)+1);
					cola.enqueue(opuesto);
					if(opuesto==w) {
						distancia=mapDistancia.get(opuesto);
						encontre=true;
					}
				}
			}
		}
		System.out.println(map);
		System.out.println(mapDistancia);
		return distancia; 
	}
}
