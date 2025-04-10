package TDA;

import TdaCola.*;;

public class TdaColaConArreglo {
	
public static void main(String[] args) {

	ColaConArreglo<Integer> q=new  ColaConArreglo<Integer>();

	for(int i=0;i<15;i++)
		q.enqueue(i);
	System.out.println(q+"tamaño:"+q.size());
	


	ColaConArreglo<Integer> q2=colaImpares(q);

	System.out.println(q2.toString()+"tamaño"+q.size());
}
	public static ColaConArreglo<Integer> colaImpares(ColaConArreglo<Integer> a){
		ColaConArreglo<Integer> Impares=new  ColaConArreglo<Integer>();
		int elem;
		int cant=a.size();	
		for(int i=0;i<cant;i++) {//tmb se puede hacer con un while q.empty()
			elem=a.dequeue();
			if(elem % 2!=0)
				Impares.enqueue(elem);
		
			}
			return Impares;
	}
}
