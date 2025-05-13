package TdaMapeo;

import Exceptions.*;
import TdaLista.*;

public class TdaMapeoConLista<K,V> implements Map<K,V> {
	//atributos de instancia
	protected ListaDoblementeEnlazada<Entrada<K,V>> S;
	
	//constructor
	public TdaMapeoConLista(){
		S=new ListaDoblementeEnlazada<Entrada<K,V>>();	}
	@Override
	public int size() {
		return S.size();
		//consultar
	}
	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	public V get(Object key) {		
		if(key == null) {throw new InvalidKeyException("La clave del get es nula");}
		else{
			for(Position<Entrada<K,V>>p:S.positions()) {
				if(p.element().getKey().equals(key)){
					return p.element().getValue();
				}
			}
			return null;
		}
	}
	@Override
	public V put(K key, V value) {
		if(key == null) {throw new InvalidKeyException("La clave del put es nula");}
			for(Position<Entrada<K,V>>p:S.positions()) 
				if(p.element().getKey().equals(key)){
					V exit=p.element().getValue();
					p.element().setValue(value);
					return exit;
				}
		//si sali del for each significa que no esta entoncens lo agrego al final
		S.addLast(new Entrada<K, V>(key,value));
		return null;
	}

	@Override
	public V remove(K key) {
		if(key==null)
			throw new InvalidKeyException("La clave del remove es nula");
		
		V exit=null;
		for (Position<Entrada<K,V>>p:S.positions())
			if(p.element().getKey().equals(key)) {
				exit=p.element().getValue();
				S.remove(p);
				return exit;
			}
		return exit;
	}

	public Iterable<K> keys() {
		PositionList<K>key=new ListaDoblementeEnlazada<K>();
		for(Entrada<K,V>input:S){
			key.addLast(input.getKey());
		}
		return key;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V>value=new ListaDoblementeEnlazada<V>();
		for(Entrada<K,V>input:S){
			value.addLast(input.getValue());
		}
		return value;
	}

	@Override
	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>>entrada=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entrada<K,V>input:S){
			entrada.addLast(input);
		}
		return entrada;
	}
}
