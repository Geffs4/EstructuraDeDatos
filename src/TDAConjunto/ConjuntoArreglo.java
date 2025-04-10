package TDAConjunto;

public class ConjuntoArreglo<E> implements Conjunto<E>{
	//atributos de isntacia
	private int cant;
	private int pos=0;
	private E []array;
	//constructor
	@SuppressWarnings("unchecked")
	public ConjuntoArreglo(int max){
		array=(E[])new Object[max];
	}
	//metodos
	public void put(E elem) {
		if(cant!=array.length&&pertenece(elem)==false){
			array[cant]=elem;
			cant++;
		}			
	}
	
	//consultas 
	public E get(int i){
		E toReturn=null;
		if(i>=0&&i<array.length){
			toReturn=array[i];
		}
		return toReturn;
	}
	public int size() {
		return cant;
	}
	public int capacity() {
		return array.length;
	}
	public boolean isEmpty() {
		return cant==0;
	}
	/*public boolean pertenece(E elem) {
		boolean encontre=false;
		for(int i=0;i<cant&&!encontre;i++) {
			if(array[i].equals(elem))
				encontre=true;
		}
		return encontre;
	}*/
	public int posicion() {
		return pos;
	}
	public boolean pertenece(E elem){
	  	return perteneceP(elem,cant);
	 }
	 private boolean perteneceP(E elem,int cant) {
		 if(cant==0)
			 return false;
		 else
			 return elem.equals(array[cant-1])||perteneceP(elem,cant-1);
	}	
	public Conjunto<E> intersección(Conjunto<E> c) {
		//comparar tamaños de los
		int mayor=0;
		int menor=0;
		
		if(cant>=c.size()) {
			menor=(Integer) c.size();
			mayor=cant;
		}
		else {
			menor=cant;
			mayor=c.size();
		}
		//creo el arreglo a retornar 
		ConjuntoArreglo<E> inter= new ConjuntoArreglo<E>(mayor);
		//recorerlos y los que son iguales guardarlos dentro del nuevo arreglo
		for(int i=0;i<menor;i++) {
			if(this.pertenece(c.get(i)))
				inter.put(c.get(i));
		}
		//retornar el arreglo
		return inter;
	}	
}