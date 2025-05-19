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
			if(Input.getKey().equals(key))
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
			if(toReturn.getKey().equals(key)&&toReturn.getValue().equals(value))
				encontre=true;
		}
		if(!encontre) {
			//si no se encuentra la retorno y la agrego a la lista del diccionario
			toReturn=new Entrada<K,V>(key,value);
			list.addLast(toReturn);
		}	
		if(encontre)
			toReturn=null;
	
		return toReturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) {
		// si la entrada es nula lanzo una excepcion
		if(e==null) {throw new InvalidEntryException("la entrada es invalida en remove()");}
			boolean encontre=false;
			Entry<K,V>toReturn=null;
			//Creo un iterador con las posiciones de las entradas para poder compararlas y porque la necesitamos para borrarla
			Iterator<Position<Entrada<K,V>>>it=list.positions().iterator();
			
			while(it.hasNext()&&!encontre) {
				Position<Entrada<K,V>>pos=it.next();
				if(pos.element().getKey()==e.getKey()&&pos.element().getValue()==e.getValue()){
					//le asigno al toReturn esto porque el remove devuelve el elemento que borro
					toReturn=list.remove(pos);
					encontre=true;
				}
			}
			//si la entrada no se encuentra dentro del diccionario lanzo una excepcion
			if(!encontre)
				throw new InvalidEntryException("la entrada no se encuentra en el diccionario");
				
		return toReturn;
	}
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>>exit=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entrada<K,V>input:list)
			exit.addLast(input);
		
		return exit;
	}
	public String toString(){
		String s="{";
		Iterator<Entrada<K,V>>it=list.iterator();
		while(it.hasNext()){
			s=s+it.next();
			if(it.hasNext()){
				s+=",";
			}
		}
		s+="}";
	return s	;
	}
}
