package TDA;
import TdaPila.*;

public class TdaPilasConArregloGen {
	public static void main(String[] args) {
		//crear las pilas
		PilaConArreglo<Integer>Gen1=new PilaConArreglo<Integer>(5);
		PilaConArreglo<Integer>Gen2=new PilaConArreglo<Integer>(5);
		
		for(int i=1;i<6;i++)
			Gen1.push(i);
	
		for(int i=6;i<11;i++) {
			Gen2.push(i);
		}	
		System.out.println(Gen1.toString()+"size:"+Gen1.size());
		System.out.println(Gen2.toString()+"size:"+Gen2.size());
		System.out.println(Intercalar(Gen1,Gen2).toString());
	}
	public static PilaConArreglo<Integer> Intercalar(PilaConArreglo<Integer>g1,PilaConArreglo<Integer>g2) {
		int tamanio=Math.max(g1.size(),g2.size());
		
		//metodo intercarlar
		PilaConArreglo<Integer>Inter=new PilaConArreglo<Integer>();
		for(int i=0;i<tamanio;i++) {
			if(g1.size()>0)
				Inter.push(g1.pop());
			if(g2.size()>0)	
				Inter.push(g2.pop());	
		}
		return Inter;
	}
}

