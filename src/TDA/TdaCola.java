package TDA;

import java.util.LinkedList;
import java.util.Queue;

public class TdaCola<E>{		
	public static void main(String[] args) {
	
		Queue<Integer> q=new LinkedList<Integer>();
	
		for(int i=0;i<11;i++)
			q.add(i);
		System.out.println(q.toString()+"tamaño:"+q.size());
		
	
	
	Queue<Integer> q2=colaImpares(q);

	System.out.println(q2.toString());
}
	public static Queue<Integer> colaImpares(Queue<Integer> a){
		Queue<Integer> Impares=new LinkedList<Integer>();
		int elem;
		int cant=a.size();	
		for(int i=0;i<cant;i++) {//tmb se puede hacer con un while q.empty()
			elem=a.remove();
			if(elem % 2!=0)
				Impares.add(elem);
			
		}
		return Impares;
	}
}