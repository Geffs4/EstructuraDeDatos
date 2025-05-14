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
		System.out.println(del.toString()+"+");

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
	
		Position<E>p1=l1.first();
		
		while(p1!=null) {
			if(buscar(p1,l2)) {
				del.addLast(l2.remove(p1));
			}
			p1=l1.next(p1);
		}
		return del;
	}	//no funciona
	
	private static <E> boolean buscar(Position<E>p,PositionList<E>list) {
		Iterator<E>it=list.iterator();
		boolean encontre=false;
		while(it.hasNext()&&!encontre);{
			System.out.println("ññ");
			encontre=p.element().equals(it.next());
		}
		
			
		return encontre;
	}
		}
	