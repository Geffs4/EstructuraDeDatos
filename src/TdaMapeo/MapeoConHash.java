package TdaMapeo;


import Auxiliar.Entry;
import Auxiliar.PositionList;
import TdaLista.ListaDoblementeEnlazada;
import Exceptions.*;

public class MapeoConHash<K,V> implements Map<K,V>{
	//atributos de instancia;
	Map<K,V> [] hash;
	int size;
	int N=11;
	
	@SuppressWarnings("unchecked")
	public MapeoConHash(){
		size=0;
		hash=(Map<K,V>[])new MapeoConLista[N];
		for(int i=0;i<N;i++)
			hash[i]= new MapeoConLista<K,V>();
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	private int hashcode(K key){
		if(key==null) {
			throw new InvalidKeyException("llave nula");
		}
		return Math.abs(key.hashCode())%N;
	}
	
	public V get(K key) {
		return hash[hashcode(key)].get(key);
	}

	@Override
	public V put(K key, V value) {
		V toReturn=hash[hashcode(key)].put(key, value);
		//incremento el size si el retorno del put delegado es null ya que significa que agrego un nuevo elemento 
		//si no cambia el valor y lo de vuelve
		if(toReturn==null)
			size++;
		
		return toReturn;
	}

	@Override
	public V remove(K key) {
		V toReturn=hash[hashcode(key)].remove(key);
		if(toReturn!=null)
			size--;
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K>keys=new ListaDoblementeEnlazada<K>();
		for(int i=0;i<N;i++)
			for(K clave:hash[i].keys()){
				keys.addLast(clave);
			}
		
		return keys;	
		}

	@Override
	public Iterable<V> values() {
		PositionList<V>values=new ListaDoblementeEnlazada<V>();
		for(int i=0;i<N;i++)
			for(V valor:hash[i].values())
				values.addLast(valor);
		
		return values;	
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>>entradas=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i=0;i<N;i++)
			for(Entry<K,V>entrada:hash[i].entries())
				entradas.addLast(entrada);
		
		return entradas;
	}
}
