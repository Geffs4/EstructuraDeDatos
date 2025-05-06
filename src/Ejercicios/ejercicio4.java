package Ejercicios;

import TdaLista.*;

public class ejercicio4 {
	//buscar una lista dentro de otra
	
	public <E> boolean pertenece(PositionList<E> list,PositionList<E>lin) {
		if(list.isEmpty())
			return false;
		if(list.isEmpty()&&lin.isEmpty())
			return true;
		
		Position<E>p1=list.first();
		boolean encontreAdentro=false;
		while(p1!=null && !encontreAdentro) {
			encontreAdentro=mirarAdentro(p1,list,lin);
			if(!encontreAdentro)
				p1=(p1!=list.last() ? list.next(p1):null);	
		}
		return encontreAdentro;
	}
	private <E> boolean mirarAdentro(Position<E> p1,PositionList<E> list,PositionList<E>lin) {
		Position<E>pLin=lin.first();
		boolean exit=true;
		
		while (pLin!=null && p1!=null&& exit) {
			E eList=p1.element();
			E eLin=pLin.element();
			exit=eList.equals(eLin);
			if(!exit)
				pLin=(pLin==lin.last() ? null:lin.next(pLin));
				p1=(p1==list.last()? null:list.next(p1));
		}
		return exit;
	}
}
