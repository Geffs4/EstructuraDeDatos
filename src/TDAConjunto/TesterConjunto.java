package TDAConjunto;

public class TesterConjunto {
	public static void main(String[] args) {
		ConjuntoArreglo<Integer> conjuntoA=new ConjuntoArreglo<Integer>(5);
		conjuntoA.put(1);
		conjuntoA.put(2);
		conjuntoA.put(3);
		conjuntoA.put(4);
		
		System.out.println("conjuntoA:");
		
		for(int i=0;i<conjuntoA.size();i++)
			System.out.println(conjuntoA.get(i));
		
		ConjuntoArreglo<Integer> conjuntoB=new ConjuntoArreglo<Integer>(4);
		conjuntoB.put(3);
		conjuntoB.put(4);
		conjuntoB.put(0);
		
		System.out.println("conjuntoB:");
		
		for(int i=0;i<conjuntoB.size();i++)
			System.out.println(conjuntoB.get(i));
		
		Conjunto<Integer> conjuntoC=conjuntoA.intersección(conjuntoB);	
		
		System.out.println("conjuntoC:");
		for(int i=0;i<conjuntoC.size();i++)
			System.out.println(conjuntoC.get(i));
		
		
		//System.out.println(conjuntoA.posicion()+" "+conjuntoA.size());
	}

}
 