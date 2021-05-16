package ProgramacionIII.tp3;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertice<T> {

	private int id;
	private ArrayList<Arco<T>> arcos; // arcos salientes, me tienen como origen
	
	public Vertice(int id) {
		this.id = id;
		this.arcos = new ArrayList<>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void addArco(Arco<T> arco) {
		this.arcos.add(arco);
	}
	
	public Iterator<Arco<T>> getArcos() {
		return this.arcos.iterator();
	}
	
}
