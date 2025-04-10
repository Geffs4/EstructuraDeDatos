package TDA;

import TdaCola.*;

public class TdaColaConArregloInter {
		public static void main(String args[]) {
			ColaConArreglo<Integer> q=new ColaConArreglo<Integer>(5);
			
			q.enqueue(34);
			q.enqueue(45);
			q.enqueue(24);
			q.enqueue(32);
			q.enqueue(70);
			
			System.out.println(q.toString()+"size:"+q.size());
			System.out.println("mayor:"+mayor(q));
			System.out.println(q.toString()+"size:"+q.size());
		}
		public static int mayor(Queue<Integer> a){
			int max=0;int aux;int size=a.size();

			for(int i=0;i<size;i++) {
				aux=a.dequeue();
				a.enqueue(aux);
				max=Math.max(aux, max);
			}	
			return max;
		}
}
