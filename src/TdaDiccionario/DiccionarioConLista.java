package TdaDiccionario;

import Exceptions.*;

import java.util.Iterator;

import Auxiliar.*;
import TdaLista.*;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	//atributos de instancia 	
	protected ListaDoblementeEnlazada<Entrada<K,V>>list;
	
	//Constructor
	public DiccionarioConLista() {
		list=new ListaDoblementeEnlazada<Entrada<K,V>>();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Entry<K, V> find(K key) {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida en find()");
		for(Position<Entrada<K,V>>p:list.positions()){
			if(p.element().getKey().equals(key))
				return p.element();
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) {
		if(key==null) {
			throw new InvalidKeyException("la clave es invalida en findAll()");
		}
		PositionList<Entry<K,V>>exit=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entry<K,V>Input:list) {
			if(Input.getKey()==key)
				exit.addLast(Input);
		}
		return exit;
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		//si la clave es nula lanzo una excepcion 
		if(key==null) {throw new InvalidKeyException("La clave es invalida en insert()");}
		
		boolean encontre=false;
		//esto lo hago para verificar que la siguiente entrada no existe dentro de la ED
		Iterator<Entrada<K,V>>it=list.iterator();
		//genero un iterador con los elementos de la lista
		Entrada<K,V>toReturn=null;
		while(it.hasNext()&&!encontre){
			//voy a buscar si la entrada ya se encuentra dentro de la lista
			toReturn=it.next();
			encontre=toReturn.getKey()==key&&toReturn.getValue()==value;		
		}
		if(!encontre)
			//si no se encuentra la retorno y la agrego a la lista del diccionario
			toReturn=new Entrada<K,V>(key,value);
			list.addLast(toReturn);
			
		return toReturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) {
		// si la entrada es nula lanzo una excepcion
		if(e==null) {throw new Invali}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}


}
