package TP8;

import TdaGrafo.*;
import TdaMapeo.Map;
import TdaMapeo.MapeoConLista;

public class Ejercicio3 {
	public static void main(String[] args) {
		Grafo<String,Integer>grafo=new Grafo<String, Integer>();
		Vertex<String>A=grafo.insertVertex("A");
		Vertex<String>B=grafo.insertVertex("B");
		Vertex<String>C=grafo.insertVertex("C");
		Vertex<String>D=grafo.insertVertex("D");
		Vertex<String>E=grafo.insertVertex("E");
		
		grafo.insertEdge(A, B, 1);
		grafo.insertEdge(B, C,2);
		grafo.insertEdge(A, D,3);
		grafo.insertEdge(B, E, 4);
		grafo.insertEdge(A, B, 5);
		
		//Vertex<String>M=grafo.insertVertex("marco");
		//Vertex<String>F=grafo.insertVertex("franco");
		//grafo.insertEdge(M, F, 0);
		
		System.out.println(grafo);
		System.out.println(esConexo(grafo));
	}
	
	public static <V,E> boolean esConexo(Graph<V,E> g){
		Map<Vertex<V>,Boolean>map=new MapeoConLista<Vertex<V>,Boolean>();
		for(Vertex<V>v:g.vertices())
			map.put(v,false);
		int cont=0;
		Vertex<V>first=map.keys().iterator().next();
			if(!map.get(first)) {
				cont=Dfs(g,first,map,cont);
			}
			return cont==map.size();
		}

	private static <V,E> int  Dfs(Graph<V, E> Grafo, Vertex<V> Vert, Map<Vertex<V>, Boolean> map,int cont){
		map.put(Vert, true);//lo marco como visitado
		cont++;
		for(Edge<E>arco:Grafo.incidentEdges(Vert)){
			Vertex<V>opuesto=Grafo.opposite(Vert, arco);
			if(!map.get(opuesto)){
				cont=Dfs(Grafo,opuesto,map,cont);
				}
		}
		return cont;
	}
}
