package TDA;

import java.util.Stack;

public class TdaPilasGen {
	public static void main(String[] args) {
		//crear las pilas
		Stack<Integer> Gen1= new Stack<Integer>();
		Stack<Integer> Gen2= new Stack<Integer>();
		
		for(int i=1;i<6;i++)
			Gen1.add(i);
	
		for(int i=6;i<11;i++) {
			Gen2.add(i);
		}	
		System.out.println(Gen1.toString()+"size:"+Gen1.size());
		System.out.println(Gen2.toString()+"size:"+Gen2.size());
		System.out.println(Intercalar(Gen1,Gen2).toString());
	}
	public static Stack<Integer> Intercalar(Stack<Integer>g1,Stack<Integer>g2) {
		int tamanio=Math.max(g1.size(),g2.size());
		
		//metodo intercarlar
		Stack<Integer>Inter=new Stack<Integer>();
		for(int i=0;i<tamanio;i++) {
			if(g1.size()>0)
				Inter.push(g1.pop());
			if(g2.size()>0)	
				Inter.push(g2.pop());	
		}
		return Inter;
	}
}