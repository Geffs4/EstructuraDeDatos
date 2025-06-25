package TdaGrafo;

import java.util.Iterator;
import Auxiliar.Position;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidVertexException;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class DiGrafo<V, E> implements Graph<V, E> {
	//Atributos de instancia 
	private PositionList<Vertice<V,E>> nodos;
	private PositionList<Arco<V,E>> arcos;
	
	//constructor
	public DiGrafo(){
		nodos=new ListaDoblementeEnlazada<Vertice<V,E>>();
		arcos=new ListaDoblementeEnlazada<Arco<V,E>>();
	}
	
	//Setters-Getters
	@SuppressWarnings("unchecked")
	public Vertice<V,E> checkVertice(Vertex<V> v){
		if(v==null) {throw new InvalidVertexException("Vertice nulo");}
		if(v.element()==null) {throw new InvalidVertexException("vertice eliminado previamente");}
		try {return (Vertice<V,E>)v;}catch(ClassCastException e) {throw new InvalidVertexException("Vertice No perteneciente al grafo");}
	}
	@SuppressWarnings("unchecked")
	public Arco<V, E> checkArco(Edge<E> edge){
		if(edge==null){throw new InvalidEdgeException("Arco nulo");}
		if(edge.element()==null) {throw new InvalidEdgeException("Arco eliminado previamente");}
		try{
			return (Arco<V,E>)edge;
			}catch(ClassCastException e){throw new InvalidEdgeException("Arco no perteneciente al grafo");}
	}
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>>list=new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V>temp:nodos)
			list.addLast(temp);
		return list;
	}

	@Override
	public Iterable<Edge<E>> edges(){
		PositionList<Edge<E>>list=new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E>temp:arcos)
			list.addLast(temp);
		return list;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
		Vertice<V, E>vert=checkVertice(v);
		PositionList<Edge<E>>list=new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E>temp:vert.getIncidentes())
			list.addLast(temp);
		return list;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
		Arco<V,E>edge=checkArco(e);
		Vertice<V,E>vert=checkVertice(v);
		if(edge.getV1()!=vert&&edge.getV2()!=vert){
			throw new InvalidEdgeException("el arco no es valido para opposite");
		}
		Vertex<V>temp=null;
		if(edge.getV1()==vert) {temp=edge.getV2();}
		else if (edge.getV2()==vert){temp=edge.getV2();}
		return temp;
	}

	@SuppressWarnings("unchecked")
	public Vertex<V>[] endvertices(Edge<E> e) {
		Arco<V,E>edge=checkArco(e);
		Vertex<V>[]array=(Vertex<V>[]) new Vertex[2];
		array[0]=edge.getV1();
		array[1]=edge.getV2();
		return array;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		Vertice<V,E>VertV=checkVertice(v);		
		Vertice<V,E>VertW=checkVertice(w);
		boolean adjacent=false;
		Iterator<Arco<V,E>>ItEmergentes=VertV.getEmergentes().iterator();
		while(ItEmergentes.hasNext()&&!adjacent){
			Arco<V, E> Cursor = ItEmergentes.next();
			adjacent=Cursor.getV1()==VertW && Cursor.getV2()==VertW;
		}
		Iterator<Arco<V,E>>ItIncident=VertV.getIncidentes().iterator();
		while(ItIncident.hasNext()&&!adjacent) {
			Arco<V, E> Cursor = ItIncident.next();
			adjacent=Cursor.getV1()==VertW && Cursor.getV2()==VertW;
		}
		return adjacent;
	}

	@Override
	public V replace(Vertex<V> v, V x) {
		Vertice<V,E>Vert=checkVertice(v);
		V exit=Vert.element();
		Vert.setElemento(x);
		return exit;
	}

	@Override
	public E replace(Edge<E> e, E x) {
		Arco<V,E>edge=checkArco(e);
		E exit=edge.element();
		edge.setElemento(x);
		return exit;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E>vert=new Vertice<V,E>(x);
		nodos.addLast(vert);
		vert.setPosicion(nodos.last());
		return vert;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
		Vertice<V,E>VertV=checkVertice(v);
		Vertice<V,E>VertW=checkVertice(w);
		Arco<V,E>edge= new Arco<V,E>(e, VertV, VertW);
		arcos.addLast(edge);
		edge.setPosicionEnListaArco(arcos.last());
		return edge;
	}

	@Override
	public V removeVertex(Vertex<V> v) {
		Vertice<V,E>Vert=checkVertice(v);
		//Elinar todos los arcos 
		for(Arco<V, E> temp:Vert.getEmergentes()) {
			if(temp.getV1()==Vert)
				temp.getV2().getIncidentes().remove(temp.getPosEnIncidentes());
			else if(temp.getV2()==Vert)
				temp.getV1().getIncidentes().remove(temp.getPosEnIncidentes());
			arcos.remove(temp.posicionEnListaArco);
		}
		for(Arco<V,E>edge:Vert.getIncidentes()){
			if(edge.getV1()==Vert)
				edge.getV2().getEmergentes().remove(edge.getPosEnEmergentes());
			else if (edge.getV2()==Vert)
				edge.getV1().getEmergentes().remove(edge.getPosEnEmergentes());
			arcos.remove(edge.posicionEnListaArco);
		}	
		return nodos.remove(Vert.getPosicion()).element();
	}

	@Override
	public E removeEdge(Edge<E> e) {
		Arco<V,E>edge=checkArco(e);
		edge.getV1().getEmergentes().remove(edge.getPosEnEmergentes());
		edge.getV2().getIncidentes().remove(edge.getPosEnIncidentes());
		return arcos.remove(edge.getPosicionEnListaArco()).element();
	}
	@SuppressWarnings("hiding")
	private class Vertice<V,E> implements Vertex<V>{
		//atributos de instancia
		protected V rotulo;
		protected Position<Vertice<V,E>> PosicionEnListaVertices;
		protected PositionList<Arco<V,E>> Emergentes,Incidentes;
		
		//constructor
		public Vertice(V rotulo){
			this.rotulo=rotulo;
			Emergentes=new ListaDoblementeEnlazada<Arco<V,E>>();
			Incidentes=new ListaDoblementeEnlazada<Arco<V,E>>();
			PosicionEnListaVertices=null;
		}
		public void setElemento(V elemento) {rotulo=elemento;}
		public V element() {
			return rotulo;
		}
		public PositionList<Arco<V,E>> getEmergentes() {return Emergentes;}
		public PositionList<Arco<V,E>> getIncidentes() {return Incidentes;}
		public void setPosicion(Position<Vertice<V,E>> vert){PosicionEnListaVertices=vert;}
		public Position<Vertice<V,E>> getPosicion(){return PosicionEnListaVertices;}
	}
	@SuppressWarnings("hiding")
	private class Arco<V,E> implements Edge<E>{
		//atributos de instancia 
		protected Position<Arco<V,E>> posicionEnListaArco;
		protected Vertice<V,E> v1,v2;
		protected E rotulo;
		protected Position<Arco<V,E>> posicionEnEmergentes,posicionEnIncidentes;
		
		//Constructor
		public Arco(E rotulo,Vertice<V,E> v1,Vertice<V,E> v2){
			this.rotulo=rotulo;
			this.v1=v1;
			this.v2=v2;
			v1.getEmergentes().addLast(this);
			posicionEnEmergentes=v1.getEmergentes().last();
			v2.getIncidentes().addLast(this);
			posicionEnIncidentes=v2.getEmergentes().last();
			posicionEnListaArco=null;
		}
		public Vertice<V,E> getV1(){return v1;}
		public Vertice<V,E> getV2(){return v2;}
		public void setPosicionEnListaArco(Position<Arco<V,E>> pos){
			posicionEnListaArco=pos;
		}
		public E element() {return rotulo;}
		public Position<Arco<V,E>> getPosEnEmergentes(){return posicionEnEmergentes;}
		public Position<Arco<V,E>> getPosEnIncidentes(){return posicionEnIncidentes;}
		public void setElemento(E elemento) {rotulo=elemento;}
		public Position<Arco<V,E>> getPosicionEnListaArco(){return posicionEnListaArco;}
	}
	
}
