package ProgramacionIII.tp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
	/*REACOMODAR CON
			A = ARCOS TOTALES
			a = ARCOS SALIENTES
			v = CANTIDAD DE VERTICES*/
	protected int cantidadArcos=0;
	private HashMap<Integer,ArrayList<Arco<T>>> mapaDeVertices;

	// O(1)
	public GrafoDirigido(){
		this.mapaDeVertices = new HashMap<Integer,ArrayList<Arco<T>>>();
	}
	
	@Override // O(1)
	public void agregarVertice(int verticeId) {
		// TODO Auto-generated method stub}
		if(!this.mapaDeVertices.containsKey(verticeId))
		this.mapaDeVertices.put(verticeId, new ArrayList<Arco<T>>());
	}

	@Override // O(v+a) en el peor de los casos todos los vertices tienen 1 arco con destino verticeid
	// + el existe arco que es de costo O(a)
	public void borrarVertice(int verticeId) {
		// TODO Auto-generated method stub
		if(this.mapaDeVertices.containsKey(verticeId)){
			Set<Integer> keys=this.mapaDeVertices.keySet();
			for ( Integer key : keys ) {
					borrarArco(key, verticeId);
			}
			this.mapaDeVertices.remove(verticeId);
		}

	}

	@Override // O(a) donde a son los arcos salientes del vertice 1
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// TODO Auto-generated method stub
		if(this.mapaDeVertices.containsKey(verticeId1) && this.mapaDeVertices.containsKey(verticeId2)){
			Arco newArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId1);
			if(!arcos.contains(newArco)){
				arcos.add(newArco);
				this.mapaDeVertices.put(verticeId1, arcos);
				this.cantidadArcos++;
			}
		}

	}

	@Override // O(a) donde a es la cantidad de arcos salientes del vertice1, ver metodo obtenerArco()
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		Arco<Integer>arcoBorrar= new Arco<Integer>( verticeId1, verticeId2, 0);
		ArrayList<Arco<T>> arcos= this.mapaDeVertices.get(verticeId1);
		this.mapaDeVertices.get(verticeId1).remove(arcoBorrar);
		this.cantidadArcos--;
	}

	@Override // O(1)
	public boolean contieneVertice(int verticeId) {
		return this.mapaDeVertices.containsKey(verticeId);
	}

	@Override // O(a) es la cantidad de arcos salientes del vertice1, ver metodo obtenerArco()
	public boolean existeArco(int verticeId1, int verticeId2) { 
			return this.obtenerArco(verticeId1, verticeId2) != null;
	}

	@Override // O(a)
	// a lo sumo tengo que iterarlos a todos
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(this.mapaDeVertices.containsKey(verticeId1)){
			ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId1);
			Iterator<Arco<T>> it = arcos.iterator();
			Arco<T> arco = null;
			boolean found = false;
			while(it.hasNext() && !found){
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

	@Override // O(1)
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return this.mapaDeVertices.keySet().size();
	}

	@Override // O(1)
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return this.cantidadArcos;
	}

	@Override // O(1)
	public Iterator<Integer> obtenerVertices() {
		return this.mapaDeVertices.keySet().iterator();
	}

	@Override // O(a)
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
	
	@Override // O(v*a)=O(A) donde n es la cantidad total de arcos
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
	// O(1)
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> arcos = this.mapaDeVertices.get(verticeId);
		return arcos.iterator();
	}

}
