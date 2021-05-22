package ProgramacionIII.tp3.entregable;


import java.util.ArrayList;
import java.util.HashMap;
import ProgramacionIII.tp3.Grafo;
import ProgramacionIII.tp3.GrafoNoDirigido;

public class Mapa {
    // SE ASUME QUE LAS CIUDADES ORIGEN Y DESTINO NO SE CUENTAN A LA HORA DE CONTABILIZAR LAS BALANZAS

    private Grafo<Integer> grafo; // sera un GND
    private HashMap<Integer, Ciudad> ciudades;
    private int maximodebalanzas=1;

    // O(1)
    public Mapa() {
        this.grafo = new GrafoNoDirigido<Integer>();
        this.ciudades = new HashMap<>();
    }

    // O(1)
    public void addCiudad(Ciudad ciudad) {
        this.ciudades.put(ciudad.getId(), ciudad);
        this.grafo.agregarVertice(ciudad.getId());
    }

    // O(1)
    public void borrarCiudad(Ciudad ciudad) {
        this.grafo.borrarVertice(ciudad.getId());
        this.ciudades.remove(ciudad.getId());
    }
    // O(1)
    public void addRuta(Ciudad origen, Ciudad destino, int kilometros) {
        if (this.grafo.contieneVertice(origen.getId()) && this.grafo.contieneVertice(origen.getId())) {
            this.grafo.agregarArco(origen.getId(), destino.getId(), kilometros);
        }
    }
    // O(n) donde n es la cantidad de arcos salientes de la ciudad origen
    public void borrarRuta(Ciudad origen, Ciudad destino) {
        this.grafo.borrarArco(origen.getId(), destino.getId());
    }

    // O(n!) donde n es funcion de la cantidad de ciudades del mapa
    public Solucion encontrarCamino(Ciudad origen, Ciudad destino) {
        ArrayList<Integer> retorno = new ArrayList<Integer>();
        PathFinder pathfinder = new PathFinder(this.grafo, origen.getId(), destino.getId(), ciudades, maximodebalanzas);

        return pathfinder.pathFind();
    }

    // O(1)
    // esto esta implementado porque supongo que si la empresa no encuentra un camino acorde a los requisitos
    // de balanza, deberia cambiar el requisito sumando 1 balanza mas en lugar de suspender el viaje
    public void setMaximodebalanzas(int nuevomaxbalanzas){
        this.maximodebalanzas=nuevomaxbalanzas;
    }

    // O(1)
    public int getMaximodebalanzas(){
        return this.maximodebalanzas;
    }

}
