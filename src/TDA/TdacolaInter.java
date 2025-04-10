package TDA;

import java.util.LinkedList;
import java.util.Queue;

public class TdacolaInter{
	public static void main(String args[]) {
		Queue<Integer>q =new LinkedList<Integer>();
		
		q.add(34);
		q.add(45);
		q.add(24);
		q.add(32);
		q.add(70);
		
		System.out.println(q.toString()+"size:"+q.size());
		System.out.println("mayor:"+mayor(q));
		System.out.println(q.toString()+"size:"+q.size());
	}
	public static int mayor(Queue<Integer> a){
		int max=0;int aux;int size=a.size();

		for(int i=0;i<size;i++) {
			aux=a.remove();
			a.add(aux);
			max=Math.max(aux, max);
		}	
		return max;
	}
}