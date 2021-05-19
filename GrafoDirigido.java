package ProgramacionIII.tp3;

import ProgramacionIII.tp3.Arco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private HashMap<Integer,ArrayList<Arco<T>>> mapaDeVertices;
	
	// Tercera vuelta
	public GrafoDirigido(){
		this.mapaDeVertices = new HashMap<Integer,ArrayList<Arco<T>>>();
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		// TODO Auto-generated method stub}
		if(!this.mapaDeVertices.containsKey(verticeId))
		this.mapaDeVertices.put(verticeId, new ArrayList<Arco<T>>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		// TODO Auto-generated method stub
		if(this.mapaDeVertices.containsKey(verticeId))
			this.mapaDeVertices.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// TODO Auto-generated method stub
		if(this.mapaDeVertices.containsKey(verticeId1) && this.mapaDeVertices.containsKey(verticeId2)){
			Arco newArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId1);
			if(!arcos.contains(newArco)){
				arcos.add(newArco);
				this.mapaDeVertices.put(verticeId1, arcos);
			}
		}

	}

	@Override // O(n) donde n es la cantidad de arcos de todo mi grafo
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
		if(arco != null){
			this.mapaDeVertices.get(verticeId1).remove(arco);
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.mapaDeVertices.containsKey(verticeId);
	}

	@Override // O(n) donde n es la cantidad de arcos de todo mi grafo
	public boolean existeArco(int verticeId1, int verticeId2) { 
			return this.obtenerArco(verticeId1, verticeId2) != null;
	}

	@Override // O(n) donde n es la cantidad de arcos de todo mi grafo
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(this.mapaDeVertices.containsKey(verticeId1)){
			ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId1);
			Iterator<Arco<T>> it = arcos.iterator();
			Arco<T> arco = null;
			boolean found = false;
			while(it.hasNext() && found == false){
				arco = it.next();
				if(arco.getVerticeDestino() == verticeId2){
					found = true;
				}
			}
			if(found){
				return arco;
			}else{
				return null;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return this.mapaDeVertices.keySet().size();
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		Integer cantidadArcos = 0;
		Iterator<Integer> it = this.mapaDeVertices.keySet().iterator();
		while (it.hasNext()) {
			cantidadArcos += this.mapaDeVertices.get(it.next()).size();
		}
		return cantidadArcos;
	}

	@Override // O(1) es solo crear un iterador y devolverlo
	public Iterator<Integer> obtenerVertices() {
		return this.mapaDeVertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> adyacentes = new ArrayList<Integer>();
		if(this.mapaDeVertices.get(verticeId)!=null){
			Iterator<Arco<T>> it = this.mapaDeVertices.get(verticeId).iterator();
			while (it.hasNext()){
				adyacentes.add(it.next().getVerticeDestino());
			}
		}
		return adyacentes.iterator();
	}

	// O(A) arcos del grafo
	// O(a) arcos salientes de un vertice
	// O(V) vertices del grafo
	
	@Override // O(A) donde A es la cantidad total de arcos
	public Iterator<Arco<T>> obtenerArcos() {
		
		ArrayList<Arco<T>> todosLosArcos = new ArrayList<>();
		Iterator<Integer> it = this.mapaDeVertices.keySet().iterator();
		while (it.hasNext()){
			Iterator<Arco<T>> arcosIt = this.obtenerArcos(it.next());
			while(arcosIt.hasNext()){
				todosLosArcos.add(arcosIt.next());
			}
		}
		
		return todosLosArcos.iterator();
	}

	@Override
	// O(n) donde n es la cantidad de arcos de todo mi grafo
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
				
		// for hasta encontrar el objeto Vertice con el ID verticeId
		
		ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId);
		return arcos.iterator();
	}

}
