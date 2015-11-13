package def;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Main2{
	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		
		int cantNodos = t.nextInt();
		int contador = 1;
		
		while(cantNodos!=0){
			PriorityQueue<Arista> aVisitar = new PriorityQueue<Arista>();
			Nodo[] nodos = new Nodo[cantNodos+1];
			for(int k=1; k<=cantNodos;k++){
				nodos[k] = new Nodo(k);
			}
			for(int i=1; i<=cantNodos; i++){
				int cantVecinos = t.nextInt();
				for(int j=1; j<=cantVecinos; j++){
					int proxVecino = t.nextInt();
					int distProxVecino = t.nextInt();
					nodos[i].vecinos.add(new Arista(nodos[i],nodos[proxVecino],distProxVecino));				
				}
			}
			
			int nodoOrigen = t.nextInt();
			int nodoDestino = t.nextInt();
			
			aVisitar.addAll(nodos[nodoOrigen].vecinos);
			nodos[nodoOrigen].peso = 0;
			
			while(!aVisitar.isEmpty()){
				Arista arista = aVisitar.poll();
				
				/*if(arista.nodoDestino.visitado == true){
					continue;
				}*/
				arista.nodoOrigen.visitado = true;
				//arista.nodoDestino.visitado = true;
				
				if(arista.nodoOrigen.peso+arista.distancia<arista.nodoDestino.peso){
					arista.nodoDestino.peso = arista.nodoOrigen.peso+arista.distancia;
					arista.nodoDestino.anterior = arista.nodoOrigen;
				}
				
				for(Arista a : arista.nodoDestino.vecinos){
					if(a.nodoDestino.visitado==false){
						aVisitar.add(a);
					}
				}
			}
		
		LinkedList<Nodo> path = new LinkedList<Nodo>();
		
		Nodo backTracking = nodos[nodoDestino];
		path.addFirst(backTracking);
		
		while(backTracking.nombre != nodos[nodoOrigen].nombre){
			backTracking = backTracking.anterior;
			path.addFirst(backTracking);		
		}
		

		System.out.print("\nCase "+contador+": Path =");
		
		Iterator<Nodo> it = path.iterator();
		
		while(it.hasNext()){
			System.out.print(" "+it.next());
		}
		
		System.out.print("; "+nodos[nodoDestino].peso+" second delay");
		
		contador++;
		cantNodos = t.nextInt();
		}				
	}
}
class Nodo {
	public ArrayList<Arista> vecinos = new ArrayList<Arista>();
	public int nombre;
	public int peso;
	public Nodo anterior;
	public boolean visitado;
	public Nodo(int nombre){
		this.nombre = nombre;
		this.visitado = false;
		this.peso = Integer.MAX_VALUE;
	}
	public String toString(){
		return String.valueOf(nombre); 
	}
}

class Arista implements Comparable<Arista>{
	public Nodo nodoOrigen;
	public Nodo nodoDestino;
	public int distancia;
	public Arista(Nodo nodoOrigen, Nodo nodoDestino, int distancia){
		this.nodoDestino = nodoDestino;
		this.distancia = distancia;
		this.nodoOrigen = nodoOrigen;
	}
	@Override
	public int compareTo(Arista a) {
		if(a==null) return 1;
		if(this.distancia>a.distancia){
			return 1;
		}else{
			if(this.distancia<a.distancia){
				return -1;
			}else{
				return -1;
			}
		}
	}
}