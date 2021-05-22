package ProgramacionIII.tp3;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

	@Override // O(1)
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		super.agregarArco(verticeId1, verticeId2, etiqueta);
		super.agregarArco(verticeId2, verticeId1, etiqueta);
	}
	
	@Override // O(n+m) donde n son la cantidad de arcos salientes del v1 y m los salientes del v2
	public void borrarArco(int verticeId1, int verticeId2) {
		super.borrarArco(verticeId1, verticeId2);
		super.borrarArco(verticeId2, verticeId1);
	}

}
