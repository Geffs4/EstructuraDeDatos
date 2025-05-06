package Ejercicios;

import java.util.Iterator;


import TdaLista.*;

public class ejercicio3 {
	//tengo dos listas hay que verificar que sean iguales
	
	public <E> boolean Iguales(PositionList <E> l1,PositionList<E> l2){
		if(l1.isEmpty()&&l2.isEmpty())
			return true;
		if(l1.size()!= l2.size())
			return false;
		//no estan vacias y miden lo mismo 
		Position<E> p1=l1.first();
		Position<E> p2=l2.first();
		
		boolean iguales=true;
		while(p1!=null&&p2!=null/*el de p2 no es necesario*/&&iguales) {
			iguales=p1.element().equals(p2.element());
		
			p1=(p1==l1.last() ? null:l1.next(p1));
			p2=(p2==l2.last()? null:l2.next(p2));
		}
		return iguales;
	}
	
	public <E> boolean IgualesI(PositionList<E> l1,PositionList<E> l2){
		if(l1.isEmpty()&&l2.isEmpty())
			return true;
		if(l1.size()!= l2.size())
			return false;
		
		boolean iguales=true;
		Iterator<E>i1=l1.iterator();
		Iterator<E>i2=l2.iterator();
		while(i1.hasNext()&&i2.hasNext()&&iguales) {
			iguales=i1.next().equals(i2.next());
		}
		return iguales;
	}
}
