package TdaGrafo;

import Auxiliar.Position;
import TdaLista.PositionList;
import TdaLista.ListaDoblementeEnlazada;

public class Vertice<V,E> implements Vertex<V>{
	//Atributos de instancia
	protected Position<Vertice<V,E>> PosicionEnNodos;
	protected V Rotulo;
	protected PositionList<Arco<V,E>>adyacente;//arco adyacente
	
	//constructor
	public Vertice(V element){
		Rotulo=element;
		adyacente=new ListaDoblementeEnlazada<Arco<V,E>>();
		PosicionEnNodos=null;
	}
	public V element(){return Rotulo;}
	
	public void insertRotulo(V element){Rotulo=element;}
	public PositionList<Arco<V,E>> getAdyacentes(){return adyacente;}
	public void setPosicionEnNodos(Position<Vertice<V,E>> p){PosicionEnNodos=p;}
	public Position<Vertice<V,E>> getPosicionEnNodos(){return PosicionEnNodos;}
}


