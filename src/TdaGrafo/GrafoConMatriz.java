package TdaGrafo;
import Auxiliar.Position;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidVertexException;
import TdaLista.*;

public class GrafoConMatriz<V,E> implements Graph<V,E>{
	//atributos de instancia
	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>>arcos;
	protected Edge<E>[][] matriz;
	protected int cantidadVertices;
	
	//constructor
	@SuppressWarnings("unchecked")
	public GrafoConMatriz(int n) {
		vertices= new ListaDoblementeEnlazada<Vertex<V>>();
		arcos=new ListaDoblementeEnlazada<Edge<E>>();
		matriz=(Edge<E>[][])new Arco[n][n];
		cantidadVertices=0;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				matriz[i][j]=null;
	} 
	private Vertice<V> checkPositionVertice(Vertex<V> vert){
		if(vert==null) {throw new InvalidVertexException("vertice nulo");}
		if(vert.element()==null) {throw new InvalidVertexException("vertice eliminado previamente");}
		try {
			return (Vertice<V>)vert;
		}catch(ClassCastException e) {throw new InvalidVertexException("vertice no pertene al Grafo");}
	}
	private Arco<V,E> checkPositionEdge(Edge<E> edge){
		if(edge==null) {throw new InvalidEdgeException("Edge nulo");}
		if(edge.element()==null) {throw new InvalidEdgeException("Edge eliminado previamente");}
		try {
			return (Arco<V,E>)edge;
		}catch(ClassCastException e) {throw new InvalidEdgeException("Edge no pertene al Grafo");}
	}
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>>list=new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V>temp:vertices)
			list.addLast(temp);
		return list;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>>list=new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E>temp:arcos)
			list.addLast(temp);
		return list;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
		Vertice<V>Vert=checkPositionVertice(v);
		int i=Vert.getIndice();
		PositionList<Edge<E>>list=new ListaDoblementeEnlazada<Edge<E>>();
		for(int j=0;j<cantidadVertices;j++)
			if(matriz[i][j]!=null)
				list.addLast(matriz[i][j]);
		return list;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
			Vertice<V>vert=checkPositionVertice(v);
			Arco<V,E>edge=checkPositionEdge(e);
			Vertice<V>temp=null;
			if(edge.getV1()!=vert&&edge.getV2()!=vert){throw new InvalidVertexException("el vertice no pertenece al arco");}
			if(edge.getV1()==vert){temp=edge.getV2();}
			else if (edge.getV2()==vert) {temp=edge.getV1();}
					
		return temp;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) {
		@SuppressWarnings("unchecked")
		Vertice<V>[]vert=(Vertice<V>[])new Vertice[2];
		Arco<V,E>edge=checkPositionEdge(e);
		vert[0]=edge.getV1();
		vert[1]=edge.getV2();
		return vert;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		Vertice<V>VertV=checkPositionVertice(v);
		Vertice<V>VertW=checkPositionVertice(w);
		int i=VertV.getIndice();
		int j=VertW.getIndice();
		return matriz[i][j]!=null;
	}

	@Override
	public V replace(Vertex<V> v, V x) {
		Vertice<V>vert=checkPositionVertice(v);
		V temp=vert.element();
		vert.setElemento(x);
		return temp;
	}

	@Override
	public E replace(Edge<E> e, E x) {
		Arco<V,E>edge=checkPositionEdge(e);
		E temp=edge.element();
		edge.setElemento(x);
		return temp;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
			Vertice<V>vert=new Vertice<V>(x, cantidadVertices++);
			vertices.addLast(vert);
			vert.setPosicionEnVertices(vertices.last());
			return vert;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
		Vertice<V>VertV=checkPositionVertice(v);
		Vertice<V>VertW=checkPositionVertice(w);
		int fila=VertV.getIndice();
		int col=VertW.getIndice();
		Arco<V,E>arco=new Arco<V,E>(e,VertV,VertW);
		
		matriz[fila][col]=arco;
		
		arcos.addLast(arco);
		arco.setPosicionEnArcos(arcos.last());
		return arco;
}

	@Override
	public V removeVertex(Vertex<V> v) {
		Vertice<V>vert=checkPositionVertice(v);
		int fila=vert.getIndice();
		
		for(int j=0;j<cantidadVertices;j++)
			if(matriz[fila][j]!=null){
				Arco<V,E>temp=checkPositionEdge(matriz[fila][j]);
				arcos.remove(temp.getPosicionEnArcos());
				matriz[fila][j]=null;
				matriz[j][fila]=null;
			}
		vertices.remove(vert.getPosicionEnVertices());
		V toReturn=vert.element();
		vert.setElemento(null);
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) {
		Arco<V,E>edge=checkPositionEdge(e);
		int fila=edge.getV1().getIndice();
		int col=edge.getV2().getIndice();
		matriz[fila][col]=null;
		matriz[col][fila]=null;
		arcos.remove(edge.getPosicionEnArcos());
		return edge.element();
	}
	
	private class Vertice<V> implements Vertex<V>{
		//atributos de instancia
		private Position<Vertex<V>>PosicionEnVertices;
		private V rotulo;
		private int indice;
		
		//constructor
		public Vertice(V rotulo, int indice	){
			PosicionEnVertices=null;
			this.rotulo=rotulo;
			this.indice=indice;
		}
	
		//Setter-Getters
		public void setPosicionEnVertices(Position<Vertex<V>> Vertex){PosicionEnVertices=Vertex;}
		public Position<Vertex<V>> getPosicionEnVertices(){return PosicionEnVertices;}
		public void setElemento(V rotulo){this.rotulo=rotulo;}
		public int getIndice() {return indice;}
		public V element() {return rotulo;}
	}
	private class Arco<V,E> implements Edge<E>{
		//Atributos de instancia 
		private Position<Edge<E>> PosicionEnArcos;
		private Vertice<V>v1,v2;
		private E rotulo;
		
		//constructor
		public Arco(E element,Vertice<V>v1,Vertice<V>v2){
			rotulo=element;
			this.v1=v1;
			this.v2=v2;
			PosicionEnArcos=null;
		}
		//setters-Getters
		public E element() {return rotulo;}
		public void setPosicionEnArcos(Position<Edge<E>> p){
			PosicionEnArcos=p;
		}
		public Position<Edge<E>> getPosicionEnArcos(){return PosicionEnArcos;}
		public Vertice<V> getV1(){return v1;}
		public Vertice<V> getV2(){return v2;}
		public void setElemento(E elemento) {
			rotulo=elemento;
		}
	}
}


