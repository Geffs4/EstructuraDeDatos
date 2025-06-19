package TdaGrafo;

import Auxiliar.Position;

public class Arco<V, E> implements Edge<E> {
	private E rotulo;
	private Vertice<V,E>v1,v2;
	private Position<Arco<V,E>> posicionEnArcos;
	private Position<Arco<V,E>>posicionEnLv1,posicionEnlv2;
	
	//constructor
	public Arco(E rotulo,Vertice<V,E> v1,Vertice<V,E>v2 ){
		this.rotulo=rotulo;
		this.v1=v1;
		this.v2=v2;
		posicionEnArcos=null;
		posicionEnLv1=null;
		posicionEnlv2=null;
	}
	
	public E element() {
		return rotulo;
	}
	public void setElement(E e) {rotulo=e;}
	public Vertice<V,E> insertV1(Vertice<V,E> V){
		Vertice<V,E> toReturn= v1;
		v1=V;
		return toReturn;
	}
	public Vertice<V,E> getV1(){return v1;}
	public Vertice<V,E> getV2(){return v2;}
	public Vertice<V,E> insertV2(Vertice<V,E> V){
		Vertice<V,E> toReturn= v2;
		v2=V;
		return toReturn;
	}
	public Position<Arco<V,E>> insertPos(Position<Arco<V,E>> pos){
		Position<Arco<V,E>>toReturn=posicionEnArcos;
		posicionEnArcos=pos;
		return toReturn;
	}
	public Position<Arco<V,E>> positionEdge(){
		return posicionEnArcos;
	}
	public Position<Arco<V,E>> insertPosV1(Position<Arco<V,E>> pos){
		Position<Arco<V,E>>toReturn=posicionEnLv1;
		posicionEnLv1=pos;
		return toReturn;
	}
	public Position<Arco<V,E>> insertPosV2(Position<Arco<V,E>> pos){
		Position<Arco<V,E>>toReturn=posicionEnLv1;
		posicionEnlv2=pos;
		return toReturn;
	}
	public Position<Arco<V,E>> getPosV1(){return posicionEnLv1;}
	public Position<Arco<V,E>> getPosV2(){return posicionEnlv2;}
}

