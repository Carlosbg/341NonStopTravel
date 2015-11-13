package def;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Main{
	public static void main(String[] args) {
		LinkedList<String> respuestas = new LinkedList<String>();
		Scanner t = new Scanner(System.in);
		
		int cantNodos = t.nextInt();
		int contador = 1;
		
		while(cantNodos!=0){
			PriorityQueue<Nodo> aVisitar = new PriorityQueue<Nodo>();
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
			
			aVisitar.add(nodos[nodoOrigen]);
			nodos[nodoOrigen].peso = 0;
	
			
			while(!aVisitar.isEmpty()){
				Nodo nodoVisitado = aVisitar.poll();
				
				if(nodoVisitado.visitado == true){
					continue;
				}
				nodoVisitado.visitado = true;
				
				for(Arista a : nodoVisitado.vecinos){
					if(nodoVisitado.peso+a.distancia<a.nodoDestino.peso){
						a.nodoDestino.peso = nodoVisitado.peso+a.distancia;
						a.nodoDestino.anterior = nodoVisitado;
					}
					if(a.nodoDestino.visitado==false){
						aVisitar.add(a.nodoDestino);
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
		
		if(path.size()==1){
			path.addFirst(backTracking);
		}
		cantNodos = t.nextInt();
		
		StringBuilder respuesta = new StringBuilder("Case "+contador+": Path =");
		
		Iterator<Nodo> it = path.iterator();
		
		while(it.hasNext()){
			respuesta.append(" "+it.next());
		}
		
		respuesta.append("; "+nodos[nodoDestino].peso+" second delay");
		
		String respuestaString = respuesta.substring(0,respuesta.length());
		
		respuestas.addLast(respuestaString);
		
		contador++;
		
		}				
	
		for(String resp : respuestas){
			System.out.println(resp);
		}
	
	}
}
class Nodo implements Comparable<Nodo>{
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
	@Override
	public int compareTo(Nodo nodo) {
		if(nodo == null) return 1;
		if(nodo.peso<this.peso)
			return 1;
		else
			return -1;
	}
}

class Arista{
	public Nodo nodoDestino;
	public int distancia;
	public Arista(Nodo nodoOrigen, Nodo nodoDestino, int distancia){
		this.nodoDestino = nodoDestino;
		this.distancia = distancia;
	}
}