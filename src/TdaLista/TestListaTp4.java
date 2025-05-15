package TdaLista;

import java.util.Iterator;

import Auxiliar.Position;
import Auxiliar.PositionList;



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
	public static <E> PositionList<E> removeLists(PositionList<E>l1,PositionList<E> l2){
		PositionList<E>del=new ListaDoblementeEnlazada<E>();
		if(l1.isEmpty()&&l2.isEmpty())
			return del;
				
		Iterator<E>it=l1.iterator();
		Iterator<Position<E>>it2=l2.positions().iterator();
		E p1=null;
		
		while(it.hasNext()){
			p1=it.next();
			if(buscar(p1,l2))
				while(it2.hasNext()){
					Position<E>p2=it2.next();
						if(p2.element().equals(p1)){
							del.addLast(l2.remove(p2));
					}					
				}
		}
		return del;
	}
	
	private static <E> boolean buscar(E p,PositionList<E>list) {
		Iterator<Position<E>>it=list.positions().iterator();
		boolean encontre=false;
		while(it.hasNext()&&!encontre) {
			encontre=it.next().element().equals(p);
		}
		return encontre;
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
	public static PositionList<Integer> intercalarOrdenado(PositionList<E> l1,PositionList<E>l2){
		PositionList<Integer>ls2=removeLists(l1, l2);
		PositionList<Integer>exit=intercalar(l1,ls2);
		Position<Integer>p=exit.first();
		int aux;
		while(p!=null){
			if(p.element()>exit.next(p).element()){
				aux=exit.next(p).element();
				exit.set(exit.next(p),p.element());
				exit.set(p,aux);
				}
			p=(p!=exit.last() ? exit.next(p):null);
		}
		
		return exit;
	}
}

	