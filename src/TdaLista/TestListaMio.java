package TdaLista;

public class TestListaMio {

	public static void main(String[] args) {
		ListaDobleMenteEnlazada<String> l=new ListaDobleMenteEnlazada<String>();
		
		l.addLast("Primero");
		l.addLast("Medio");
		l.addLast("Ultimo");
		
		
		l.addSA("+", "*");
		l.addSA("2","3");
		System.out.println(l.toString());
				
	}
}
