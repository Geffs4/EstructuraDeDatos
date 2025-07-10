package TP8;

import TdaCola.ColaEnlazada;
import TdaCola.Queue;
import TdaGrafo.Edge;
import TdaGrafo.Grafo;
import TdaGrafo.Vertex;
import TdaLista.PositionList;
import TdaMapeo.*;
import TdaLista.ListaDoblementeEnlazada;

public class Ejercicio6 {
	public static void main (String []args){
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
		grafo.insertEdge(E, I, 4);

		System.out.println(caminoMasCorto(grafo, A, I));
	}
	public static <V> String caminoMasCorto(Grafo<V,Integer> grafo,Vertex<V> raiz,Vertex<V> destino){
		Map<Vertex<V>,Boolean>NodoVisitado=new MapeoConLista<Vertex<V>,Boolean>();
		Map<Vertex<V>,Integer>NodoPeso=new MapeoConLista<Vertex<V>, Integer>();
		Map<Vertex<V>,Vertex<V>>Recorrido=new MapeoConLista<Vertex<V>, Vertex<V>>();
		for(Vertex<V>nodo:grafo.vertices()){
			NodoVisitado.put(nodo,false);
			NodoPeso.put(nodo,-1);
			Recorrido.put(nodo,null);
			//peso negativo no fue 
		}
		int Distancia=0;
		Queue<Vertex<V>>cola=new ColaEnlazada<Vertex<V>>();
		cola.enqueue(raiz);
		NodoVisitado.put(raiz,true);
		NodoPeso.put(raiz,0);
		Vertex<V>actual=null;
		boolean encontre=false;	
		while(!encontre && !cola.isEmpty()){
			actual=cola.dequeue();
			for(Edge<Integer> arco:grafo.incidentEdges(actual)) {
				Vertex<V>opuesto=grafo.opposite(actual,arco);
				if(!NodoVisitado.get(opuesto)){
					NodoVisitado.put(opuesto, true);
					NodoPeso.put(opuesto,NodoPeso.get(actual)+arco.element());
					Recorrido.put(opuesto, actual);
					cola.enqueue(opuesto);
				}
				if(opuesto==destino) {
					Distancia=NodoPeso.get(opuesto);
					encontre=true;
				}
			}
		}
		PositionList<V>Camino=new ListaDoblementeEnlazada<V>();
		actual=destino;
		while(actual!=null){
			Camino.addFirst(actual.element());
			actual=Recorrido.get(actual);
		}
		return Distancia+" "+Camino.toString();
	}
}
