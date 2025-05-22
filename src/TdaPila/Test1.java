package TdaPila;

public class Test1 {

	public static void main(String[] args) {
		Stack<Character>p1=new PilaEnlazada<Character>();
		
		p1.push('a');
		p1.push('b');
		p1.push('a');
		p1.push('c');
		p1.push('d');
		
		System.out.println(p1+" "+eliminarDePila(p1,'a'));
	}
	public static Stack<Character> eliminarDePila(Stack<Character> p,Character elem){
		Stack<Character>aux = new PilaEnlazada<Character>();
		while(!p.isEmpty())
			if(p.top().equals(elem))
				p.pop();
			else 
				aux.push(p.pop());
		
		Stack<Character>exit=new PilaEnlazada<Character>();
		while(!aux.isEmpty())
			exit.push(aux.pop());
		
		return exit;
	}
}