package ProgramacionIII.tp3.entregable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ProgramacionIII.tp3.Arco;
import ProgramacionIII.tp3.EncontrarCaminos;
import ProgramacionIII.tp3.Grafo;
import ProgramacionIII.tp3.GrafoNoDirigido;

public class Mapa {

    private Grafo<Integer> grafo; // sera un GND
    private HashMap<Integer, Ciudad> ciudades;
    private static final int MAX_BALANZAS = 1;

    public Mapa() {
        this.grafo = new GrafoNoDirigido<Integer>();
        this.ciudades = new HashMap<>();
    }

    public void addCiudad(Ciudad ciudad) {
        this.ciudades.put(ciudad.getId(), ciudad);
        this.grafo.agregarVertice(ciudad.getId());
    }

    public void borrarCiudad(Ciudad ciudad) {
        this.grafo.borrarVertice(ciudad.getId());
        this.ciudades.remove(ciudad.getId());
    }

    public void addRuta(Ciudad origen, Ciudad destino, int kilometros) {
        if (this.grafo.contieneVertice(origen.getId()) && this.grafo.contieneVertice(origen.getId())) {
            this.grafo.agregarArco(origen.getId(), destino.getId(), kilometros);
        }
    }

    public void borrarRuta(Ciudad origen, Ciudad destino) {
        this.grafo.borrarArco(origen.getId(), destino.getId());
    }

    /*
     * Esto es un solo un código de ejemplo de como se usarían las estructuras
     * La interfaz (retorno o parametros) de este método se puede cambiar,
     * y por supuesto se pueden crear mas métodos en esta clase en caso de ser necesario
     */
    public Solucion encontrarCamino(Ciudad origen, Ciudad destino) {
        ArrayList<Integer> retorno = new ArrayList<Integer>();
        PathFinder pathfinder = new PathFinder(this.grafo, origen.getId(), destino.getId(), ciudades, MAX_BALANZAS);

        return pathfinder.pathFind();
    }



    private void imprimirRetorno(ArrayList<Integer> retorno, int kms) {
        Iterator<Integer> iterador = retorno.iterator();
        String salida = "";
        while (iterador.hasNext()) {
            salida += " " + ciudades.get(iterador.next()).getNombre();
        }
        System.out.println("Ruta: " + salida + " de " + kms + " kms");
    }
}
