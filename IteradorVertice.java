package ProgramacionIII.tp3;

import java.util.Iterator;

public class IteradorVertice<T> implements Iterator<Integer> {

	private Iterator<Vertice<T>> iteradorInterno;
	
	public IteradorVertice(Iterator<Vertice<T>> iteradorInterno) {
		this.iteradorInterno = iteradorInterno;
	}
	
	@Override
	public boolean hasNext() {
		return this.iteradorInterno.hasNext();
	}

	@Override
	public Integer next() {
		Vertice<T> vertice = this.iteradorInterno.next();
		return vertice.getId();
	}

}
