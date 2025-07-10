package TdaMapeo;

import TdaLista.*;
import TdaLista.ListaDoblementeEnlazada;

public class TestMappeoMioII {
	public static void main(String[] args) {
		PositionList<String>p=new ListaDoblementeEnlazada<String>();
		
		p.addLast("a");
		p.addLast("b");
		p.addLast("a");
		p.addLast("c");
		p.addLast("a");
		p.addLast("d");
		p.addLast("b");
		p.addLast("c");
		p.addLast("a");
		p.addLast("b");
		
		System.out.println(p);
		System.out.println(cantElementosMio(p));
	}
	public static <E> Map<E,Integer> cantElementosMio(PositionList<E>l){
		Map<E,Integer>cant=new MapeoConLista<E,Integer>();
		for(E elemento:l){
			Integer contador=cant.get(elemento);
			//el get devuelve o null o un valor asignado como(1,2,3,...)
			if(contador==null) {
				//significa que estamos en un nuevo elemento
				cant.put(elemento,1);
			}
			else {
				//sino es asi lo asigno al mapa con un su sucesor de valor
				cant.put(elemento,contador+1);
			}
		}
		return cant;
	}
}
