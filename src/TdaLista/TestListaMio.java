package TdaLista;

public class TestListaMio {

	public static void main(String[] args) {
		ListaSimplementeEnlazada<Character> l=new ListaSimplementeEnlazada<Character>();
		
		l.addFirst('D');
		l.addFirst('C');
		l.addFirst('B');
		l.addFirst('A');
		
		Position<Character> p=l.first();
		
		while(p!=l.last()) {
			System.out.println(p.element());
			p=l.next(p);
		}
		System.out.println(p.element());
		
		Position<Character> aux=l.last();
		
		l.addBefore(aux,'P');
		
		System.out.println("----------------------------------");
		
		p=l.first();
		
		while(p!=l.last()) {
			System.out.println(p.element());
			p=l.next(p);
		}
		System.out.println(p.element());
		
	}
}
