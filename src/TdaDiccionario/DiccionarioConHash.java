package TdaDiccionario;

import Auxiliar.Entry;
import Exceptions.*;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;

public class DiccionarioConHash<K,V> implements Dictionary<K,V>{
	//atributos de instancia 
	protected Dictionary<K,V> dic[];
	protected int size;
	protected int capacity=13;
	
	//constructor
	@SuppressWarnings("unchecked")
	public DiccionarioConHash(){
		dic=(Dictionary<K,V>[])new DiccionarioConLista[capacity];
		for(int i=0;i<capacity;i++)
			dic[i]=new DiccionarioConLista<K,V>();
		
	}
	@Override
	public int size() {
			return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	private int hashcode(K key){
		return Math.abs(key.hashCode())%capacity;
	}
	@Override
	public Entry<K, V> find(K key) {
		if(key==null){
			throw new InvalidKeyException("key nula en find()");
		}
		return dic[hashcode(key)].find(key);
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) {
		if(key==null){
			throw new InvalidKeyException("key nula en findAll()");
		}
		return dic[hashcode(key)].findAll(key);
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		if(key==null){
			throw new InvalidKeyException("key nula en insert()");
		}
		Entry<K,V> toReturn=dic[hashcode(key)].insert(key,value);
		/*si exite un retorno significa que se pudo insertar un elemento
		 * si no devuelve null*/
		if(toReturn!=null){
			size++;
		}
		
		return toReturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) {
		if(e==null){
			throw new InvalidEntryException("entrada nula en remove()");
		}
		Entry<K,V>toReturn=dic[hashcode(e.getKey())].remove(e);
		/*si exite un retorno significa que se pudo remover un elemento
		 * si no devuelve null*/
		if(toReturn!=null){
			size--;
		}
		else{
			throw new InvalidEntryException("Entrada no valida en remove()");
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>>exit=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i=0;i<capacity;i++)
			for(Entry<K,V> entrada:dic[i].entries())
				exit.addLast(entrada);
		
		return exit;
	}
	public String toString(){
		String exit="[";
		for(int i=0;i<capacity;i++){
			exit=exit+dic[i].toString();
			if (i<capacity-1)
				exit += ", ";
		}
		exit=exit+"]";
		return exit;
	}
}