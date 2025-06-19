package TdaGrafo;

import java.util.Iterator;

import Exceptions.*;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class Grafo<V, E> implements Graph<V,E>{
	//atributod de instancia
	protected PositionList<Vertice<V,E>>nodos;
	protected PositionList<Arco<V,E>>arcos;

	//constructor
	public Grafo(){
		nodos=new ListaDoblementeEnlazada<Vertice<V,E>>();
		arcos=new ListaDoblementeEnlazada<Arco<V,E>>();
	}
	//setters-getters
	@SuppressWarnings("unchecked")
	private Vertice<V,E> checkPositionVertex(Vertex<V> v){
		if(v==null) {throw new InvalidVertexException("Vertice nulo");}
		try{
			return (Vertice<V,E>) v;
			}catch(ClassCastException e){
				throw new InvalidVertexException("Vertice no perteciente al grafo");
		}
	}
	@SuppressWarnings("unchecked")
	private Arco<V,E> checkPositionEdge(Edge<E> ed){
		if(ed==null) {throw new InvalidEdgeException("Vertice nulo");}
		try{
			return (Arco<V,E>) ed;
			}catch(ClassCastException e){
				throw new InvalidEdgeException("Vertice no perteciente al grafo");
		}
	}
	
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>>list=new ListaDoblementeEnlazada<Vertex<V>>();
			for(Vertex<V>v: nodos)
				list.addLast(v);		
		return list;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>>list=new ListaDoblementeEnlazada<Edge<E>>();
			for(Edge<E>e:arcos)
				list.addLast(e);
		return list;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
		PositionList<Edge<E>>list = new ListaDoblementeEnlazada<Edge<E>>();
		Vertice<V,E>vert=checkPositionVertex(v);//hacer un checkPosition	
		for(Edge<E>edge:vert.getAdyacentes())
			list.addLast(edge);
		return list;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
		Vertice<V,E>vert=checkPositionVertex(v);
		Arco<V,E>edge=checkPositionEdge(e);
		if(vert!=edge.getV1()&&vert!=edge.getV2()){throw new InvalidEdgeException("el vertice no pertene al arco");}
		Vertex<V>temp=null;
		if(edge.getV1()==vert)
			temp=edge.getV2();
		else if(edge.getV2()==vert)
			temp=edge.getV1();
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) {
		Arco<V,E>edge=checkPositionEdge(e);
		Vertex<V>temp[]=(Vertex[]) new Vertex[2];
		temp[0]=edge.getV1();
		temp[1]=edge.getV2();
		return temp;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		Vertice<V,E>vertV=checkPositionVertex(v);
		Vertice<V,E>vertW=checkPositionVertex(w);
		boolean encontre=false;
		Iterator<Arco<V, E>>temp1=vertV.getAdyacentes().iterator();
		while(!encontre&&temp1.hasNext()) {
			Arco<V,E>T1=temp1.next();
			encontre=T1.getV1()==vertV&&T1.getV2()==vertW ||
					 T1.getV1()==vertW&&T1.getV2()==vertV;
		}
		return encontre;
	}

	@Override
	public V replace(Vertex<V> v, V x) {
		Vertice<V,E>vert=checkPositionVertex(v);
		V temp=vert.element();
		vert.insertRotulo(x);
		return temp;
	}

	@Override
	public E replace(Edge<E> e, E x) {
		Arco<V,E>edge=checkPositionEdge(e);
		E temp=edge.element();
		edge.setElement(x);
		return temp;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E>vert=new Vertice<V,E>(x);
		nodos.addLast(vert);
		vert.setPosicionEnNodos(nodos.last());
		return vert;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
		Vertice<V,E>VertV=checkPositionVertex(v);
		Vertice<V,E>VertW=checkPositionVertex(w);
		Arco<V,E>edge=new Arco<V,E>(e,VertV,VertW);
		VertV.getAdyacentes().addLast(edge);
		edge.insertPosV1(VertV.getAdyacentes().last());
		VertW.getAdyacentes().addLast(edge);
		edge.insertPosV2(VertW.getAdyacentes().last());
		arcos.addLast(edge);
		edge.insertPos(arcos.last());
		return edge;
	}

	@Override
	public V removeVertex(Vertex<V> v) {
		Vertice<V,E>vert=checkPositionVertex(v);
		PositionList<Arco<V,E>>temp=vert.getAdyacentes();
		for(Arco<V,E>edge:temp){
			if(edge.getV1()==vert)
					edge.getV2().getAdyacentes().remove(edge.getPosV2());
			else if(edge.getV2()==vert)
					edge.getV1().getAdyacentes().remove(edge.getPosV1());
			arcos.remove(edge.positionEdge());
		}
		return nodos.remove(vert.getPosicionEnNodos()).element();
	}
	@Override
	public E removeEdge(Edge<E> e) {
		Arco<V,E>edge=checkPositionEdge(e);
		Vertice<V,E>V1=edge.getV1();
		Vertice<V,E>V2=edge.getV2();
		
		V1.getAdyacentes().remove(edge.getPosV1());
		V2.getAdyacentes().remove(edge.getPosV2());
		
		return arcos.remove(edge.positionEdge()).element();
	}

}
