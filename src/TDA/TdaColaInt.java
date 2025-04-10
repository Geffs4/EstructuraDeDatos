package TDA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TdaColaInt {
	public static void main(String args[]) {
		Queue<Integer>q =new LinkedList<Integer>();
		Stack<Integer>aux=new Stack<Integer>();
		
		q.add(34);
		q.add(45);
		q.add(24);
		q.add(32);
		q.add(40);
		
		System.out.println(q.toString()+"size:"+q.size());
		int size=q.size();
		int mayor=-555555;
		int val;
		//extraer elementos de la cola y guardarlos en una pila
		for(int i=0;i<size;i++) {
			val=q.remove();
			aux.add(val);
			mayor=Math.max(val,mayor);
		}
		System.out.println(mayor);
		//System.out.println(aux.toString());
		
		for(int i=0;i<size;i++) {
			val=aux.pop();
			q.add(val);
		}
		for(int i=0;i<size;i++) {
			val=q.remove();
			aux.add(val);
		}
		for(int i=0;i<size;i++) {
			val=aux.pop();
			q.add(val);
		}
		System.out.println(q.toString()+"size:"+q.size());
	}	
}