package Ejercicios;

import Auxiliar.Position;
import Exceptions.EmptyTreeException;
import TdaArbol.Tree;
import TdaArbolBinario.ArbolBinario;
import TdaArbolBinario.BinaryTree;
import TdaMapeo.*;


public class SegundoParcial24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArbolBinario<Character>arbol=new ArbolBinario<Character>();
		arbol.createRoot('/');
		Position<Character>mult=arbol.addLeft(arbol.root(),'*');
		Position<Character>mas=arbol.addRight(arbol.root(), '+');
		Position<Character>mas1=arbol.addLeft(mult, '+');
		arbol.addRight(mult,'3');
		arbol.addLeft(mas1,'3');
		arbol.addRight(mas1,'1');
		Position<Character>menos=arbol.addLeft(mas,'-');
		arbol.addLeft(menos,'9');
		arbol.addRight(menos, '5');
		arbol.addRight(mas,'2');
		
		System.out.println(arbol);
		System.out.println("");
		System.out.println("operadores:"+cantOperadores(arbol).toString());
	}

	private static Map<Character, Integer> cantOperadores(BinaryTree<Character> arbol) {
		Map<Character, Integer> mapeo = new MapeoConHash<Character, Integer>();
		if (arbol == null|| arbol.isEmpty()) {
			throw new EmptyTreeException("El árbol está vacío.");
		} else {
			postOrdenOperadores(arbol, arbol.root(), mapeo);
		}
		return mapeo;
	}

	public static <E> void postOrdenOperadores(BinaryTree<Character> arbol, Position<Character> operador, Map<Character, Integer> map) {
        for(Position<Character> child : arbol.children(operador)) {
            postOrdenOperadores(arbol, child, map);
        }

        Integer contador;
        Character actual = operador.element();

        if(actual.equals('+') || actual.equals('*') || actual.equals('-') || actual.equals('/')) {
            contador = map.get(actual);
            if(contador == null) {
                contador=0;
            }
            map.put(actual, contador+1);
        }
    }

}
