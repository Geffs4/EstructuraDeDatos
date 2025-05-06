package TdaLista;

public class TestListaMio {

	public static void main(String[] args) {
		ListaDobleMenteEnlazada<String> l=new ListaDobleMenteEnlazada<String>();
		
		l.addLast("Primero");
		l.addLast("Medio");
		l.addLast("Ultimo");
	
		
		System.out.println(l.toString());
		PositionList<String>aux=l.dupper(l);
		System.out.println(aux.toString());
		}
}	