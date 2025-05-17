package TdaDiccionario;

import Auxiliar.*;
import TdaMapeo.*;

public class TestDiccionarioMio {
	public static void main(String args[]){
		Dictionary<Integer,String>d1=new DiccionarioConLista<Integer,String>();
		
		d1.insert(1,"a");
		d1.insert(2,"b");
		d1.insert(3,"a");
		d1.insert(2,"c");
		d1.insert(1,"d");
		d1.insert(4,"b");
		
		System.out.println(d1);
		
		System.out.println(acomodar(d1));
	} 
	public static <K,V> Dictionary<K,V> acomodar(Dictionary<K,V> d){
		Map<K,V>aux = new MapeoConLista<K,V>(); 
		for(Entry<K,V>entrada:d.entries()){
			aux.put(entrada.getKey(),entrada.getValue());
		}
		Dictionary<K,V>dRes=new DiccionarioConLista<K,V>();
		for(Entry<K,V>unico:aux.entries()){
			dRes.insert(unico.getKey(),unico.getValue());
		}			
		return dRes;
		//segun chatGPT con un hash code el codigo es mas eficiente
	}
	
}
	