package TdaLista;

import java.util.Iterator;


import Auxiliar.Position;

public class TestListaTp4 {
	public static void main(String[] args) {
		ListaDoblementeEnlazada<Integer> l=new ListaDoblementeEnlazada<Integer>();
		
		for(int i=1;i<5;i++)
			l.addLast(i);
	
		System.out.println(l.toString());

//chequeo que belong funcione
		boolean pertenece=true;
		for(int i=1;i<5&&pertenece;i++) {
			pertenece=belong(i,l);
		}
		String mensaje= (pertenece ? "Funciona":"no funciona");
		System.out.println("belong:"+mensaje);	
//---------------------------metodo: CantElements()------------------------------------------
		ListaDoblementeEnlazada<String> A=new ListaDoblementeEnlazada<String>();
		for(int i=1;i<10;i++)
			A.addLast("a");
		System.out.println(A.toString());
		int cantE=cantElements("a",A);
		mensaje=(cantE==9 ? "Funciona":"no funciona");
		System.out.println("cantElements:"+mensaje);

//---------------------------metodo cantBelong
		mensaje=(!cantBelong(A,"a",3)? "Funciona":"no funciona");
		System.out.println("cantBelong:"+mensaje);
//---------------------------metodo: dupper
		System.out.println(dupper(l).toString());
	
//---------------------------
		ListaDoblementeEnlazada<String>B=new ListaDoblementeEnlazada<String>();
		
		B.addLast("a");
		B.addLast("B");
		B.addLast("a");
		B.addLast("a");
		B.addLast("a");
		
		ListaDoblementeEnlazada<String>del=(ListaDoblementeEnlazada<String>) removeLists(A,B);
		System.out.println(A.toString()+" "+B.toString());
		System.out.println("Elementos eliminados"+del.toString());
//-------------------------------Intercarlar-----------------------------------------------
		ListaDoblementeEnlazada<Integer>list1= new ListaDoblementeEnlazada<Integer>();
		ListaDoblementeEnlazada<Integer>list2= new ListaDoblementeEnlazada<Integer>();
		for(int i=0;i<=5;i++) {
			list1.addLast((i)-1);
			list2.addLast(i);
		}
		System.out.println("l1:"+list1.toString());
		System.out.println("l2:"+list2.toString());
		
		System.out.println("intercalado:"+intercalar(list1,list2));
		System.out.println("ordenado:"+intercalarOrdenado(list1,list2));
//-------------------------------elminarM--------------------------------------------------
		ListaDoblementeEnlazada<Integer>list3=new ListaDoblementeEnlazada<Integer>();
		
		list3.addLast(1);
		list3.addLast(2);
		list3.addLast(3);
		list3.addLast(4);
		list3.addLast(5);
		
		System.out.println(list3+" "+list1);
		eliminarM(list1,list3);
		System.out.println(list1.toString());
	
	}	
	public static <E>boolean belong(E e1,PositionList<E>list) {
		Iterator<E>it=list.iterator();
		boolean encontre=false;
		
		while(it.hasNext()&&!encontre)
			encontre=it.next().equals(e1);
		
		return encontre;
	}
	public static <E> int cantElements(E e1,PositionList<E>list) {
		int Cont=0;
		Iterator<E>it=list.iterator();
		while(it.hasNext())
			if(it.next().equals(e1))
				Cont++;
		
		return Cont;
	}
	public static <E> boolean cantBelong(PositionList<E> list,E x,int n) {
		int cant=0;
		for(E elem:list)
			if(elem.equals(x))
				cant++;
		
		return cant==n;			
	}
	//tp4-4
	public static <E> PositionList<E> dupper(PositionList<E> list){
		PositionList<E>exit=new ListaDoblementeEnlazada<E>();
		for(E aux:list) {
			exit.addLast(aux);
			exit.addLast(aux);
			}
		return exit;
	}
	public static <E> PositionList<E> removeList(PositionList<E>l1,PositionList<E>l2){
		PositionList<E>eliminado=new ListaDoblementeEnlazada<E>();
		if(l1.isEmpty()&&l2.isEmpty())
			return eliminado;
		
		Iterator<E>it=l1.iterator();
		while(it.hasNext()){
			E p1=it.next();
			for(Position<E>p2:l2.positions()){
				if(p1.equals(p2.element()))
					eliminado.addLast(l2.remove(p2));
			}
		}
		return eliminado;
		
	}
	
	public static <E> PositionList<E> removeLists(PositionList<E>l1,PositionList<E> l2){
		PositionList<E>eliminado=new ListaDoblementeEnlazada<E>();
		if(l1.isEmpty()&&l2.isEmpty())
			return eliminado;

		Position<E> cursor1=l1.first();
		
		for(Position<E>p:l2.positions()){
			if(cursor1.equals(p.element()))
					eliminado.addLast(l2.remove(p));
		}
		return eliminado;
	}
	//preguntar por el comportamiento de removeLists
	public static <E> PositionList<E> intercalar(PositionList<E> l1,PositionList<E> l2){
		PositionList<E>list=new ListaDoblementeEnlazada<E>();
		Iterator<E>it1=l1.iterator();
		Iterator<E>it2=l2.iterator();
		
		while(it1.hasNext()||it2.hasNext()) {
				if(it1.hasNext()) {
					list.addLast(it1.next());
				}
				
				if(it2.hasNext()) {
					list.addLast(it2.next());
				}
		}
		return list;
	}
	public static PositionList<Integer> intercalarOrdenado(PositionList<Integer> l1,PositionList<Integer>l2){
		removeList(l1, l2);
		PositionList<Integer>exit=new ListaDoblementeEnlazada<Integer>();
		Position<Integer> cursor1=l1.first();
		Position<Integer> cursor2=l2.first();
		while(cursor1!=null && cursor2!=null) {
			if(cursor1.element()<cursor2.element()){
				exit.addLast(cursor1.element());
				cursor1=cursor1!=l1.last()? l1.next(cursor1):null;
			}
			else {
				exit.addLast(cursor2.element());
				cursor2=cursor2!=l2.last()? l2.next(cursor2):null;
			}
		}
		while(cursor1!=null) {
			exit.addLast(cursor1.element());
			cursor1=cursor1!=l1.last()? l1.next(cursor1):null;
		}			
		while(cursor2!=null){
			exit.addLast(cursor2.element());
			cursor2=cursor2!=l2.last()? l2.next(cursor2):null;
		}
		return exit;
	}
	public static <E> void eliminarM(PositionList<E>l1,PositionList<E>l2){
		removeList(l2,l1);
		Position<E>p=l2.last();
		while(p!=null){
			l1.addLast(p.element());
			if(p!=l2.first()&&l2.prev(p)!=l2.first()){
				p=l2.prev(p);
			}
			else
				p=null;
		}
	}
}