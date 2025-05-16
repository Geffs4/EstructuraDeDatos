package TdaMapeo;

import TdaLista.*;

import java.util.Iterator;

import Auxiliar.*;


public class TestMappeoMio {
	public static void main(String args[]){
		
		MapeoConLista<Integer,Integer>uns=new MapeoConLista<Integer,Integer>();
		MapeoConLista<Integer,Integer>unicen=new MapeoConLista<Integer,Integer>();
		
		unicen.put(134291,9);
		unicen.put(147703,6);
		
		uns.put(147703,5);
		uns.put(134291,10);
		
		System.out.println(uns+" "+unicen);
		System.out.println("L:"+PerteneMM(unicen,uns));
		
		MapeoConLista<String,Integer>test1=new MapeoConLista<String,Integer>();
		MapeoConLista<String,Integer>test2=new MapeoConLista<String,Integer>();;
		
		for(int i=1;i<6;i++){
			test1.put(i+""+i+1,i*2+1);
			test2.put(i+""+i+1,i*2);
		}
		System.out.println(test1+" "+test2);
		System.out.println("Pertenece:"+M1perteneM2(test1,test2));
		
	}
	//ejerciocio Tp5-1a
	public static PositionList<Entry<Integer,Integer>> PerteneMM(Map<Integer,Integer> m1,Map<Integer,Integer> m2){
		PositionList<Entry<Integer,Integer>>L=new ListaDoblementeEnlazada<Entry<Integer,Integer>>();
		Iterator<Entry<Integer, Integer>>It1=m1.entries().iterator();
		Iterator<Entry<Integer,Integer>>It2;
		
		Entry<Integer,Integer>E1;
		Entry<Integer,Integer>E2;
		
		while(It1.hasNext()){
			E1=It1.next();
			It2=m2.entries().iterator();
			while(It2.hasNext()){
				E2=It2.next();
				if(E1.getKey().equals(E2.getKey())){
					L.addLast(E1);
					L.addLast(E2);
				}
			}	
		}
		return L;
	}
	//ejerciocio Tp5-1b
	public static <K,V> boolean M1perteneM2(Map<K,V>M1,Map<K,V>M2){
		int pertenece=0;boolean encontre=false;
		Iterator<Entry<K,V>>it1=M1.entries().iterator();
		while(it1.hasNext()){
			encontre=false;
			Entry<K,V>E1=it1.next();
			Iterator<Entry<K,V>>it2=M2.entries().iterator();
			while(it2.hasNext()&&!encontre){
				Entry<K,V>E2=it2.next();
				if(E1.getKey().equals(E2.getKey())){
					pertenece++;
					encontre=true;
				}
				
			}
		}
		return pertenece==M1.size();
	}
}
	