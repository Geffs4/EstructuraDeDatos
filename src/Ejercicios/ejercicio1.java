package Ejercicios;

import Auxiliar.Position;
import TdaLista.*;
import TdaLista.PositionList;

//ED pdf-8 
public class ejercicio1 {
	public static void main(String[]args) {
	} 
	public <E> int contarApariciones(E item,PositionList<E>lista) {
		int cant=0;
		if(!lista.isEmpty()) {
			Position<E> p=lista.first();
			while(p!=null) {
				if(p.element().equals(item))
					cant++;
				p=(p==lista.last()? null:lista.next(p));
				//asigna p=null si p es el ultimo de la lista sino asigna el siguiente de p;  
			}	
		}
		return cant;
	} 
	//ejercicio 2 de manera ineficiente
	public <E> boolean aparece(E elem,PositionList<E> lista) {
		return contarApariciones(elem, lista)>0;
	}
}
