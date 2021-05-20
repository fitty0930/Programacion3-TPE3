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
    public List<?> encontrarCamino(Ciudad origen, Ciudad destino) {
        ArrayList<Integer> retorno = new ArrayList<Integer>();
        HashMap<Integer, Ciudad> ciudadescopia = ciudades;
        if (origen != null && destino != null) {
            PathFinder pathfinder = new PathFinder(this.grafo, origen.getId(), destino.getId(), ciudadescopia, MAX_BALANZAS);
            ArrayList<ArrayList<Integer>> caminosencontrados = pathfinder.pathFind();
            Iterator<ArrayList<Integer>> iterador = caminosencontrados.iterator();
            int kms = 0;
            int kmsant = 1000000;
            System.out.println(caminosencontrados);
            while (iterador.hasNext()) {
                kms = 0;
                ArrayList<Integer> aux = iterador.next();
                for (int i = 0; i < aux.size() - 1; i++) {
                    Arco<Integer> arco = grafo.obtenerArco(aux.get(i), aux.get(i + 1));
                    kms += arco.getEtiqueta();
                }
                if (kmsant > kms) {
                    retorno = aux;
                    imprimirRetorno(retorno, kms); // solo con fines visuales
                }
                kmsant = kms;
            }
        }

        return retorno;
    }


    public List<?> encontrarCaminoDeprecado(Ciudad origen, Ciudad destino) {
        EncontrarCaminos encontrarcam = new EncontrarCaminos(this.grafo, origen.getId(), destino.getId());
        ArrayList<ArrayList<Integer>> caminosencontrados = encontrarcam.encontrarCaminos();
        Iterator<ArrayList<Integer>> iterador = caminosencontrados.iterator();

        ArrayList<Integer> retorno = new ArrayList<Integer>();
        int kms = 0;
        int kmsant = 1000000; // un numero grandisimo
        int cantBalanzas = 0;
        int cantBalanzasAnt = 1000000; // un numero grandisimo
        while (iterador.hasNext()) {
            cantBalanzas = 0;
            kms = 0;
            ArrayList<Integer> aux = iterador.next();
            for (int i = 0; i < aux.size() - 1; i++) {
                if (ciudades.get(aux.get(i)).isTieneBalanza()) {
                    if (i == 0) {
                        cantBalanzas += 0; // parche nazi
                    } else {
                        cantBalanzas += 1;
                    }
                }
                if (ciudades.get(aux.get(i + 1)).isTieneBalanza()) {
                    if (i == aux.size() - 1) {
                        cantBalanzas += 0;
                    } else {
                        cantBalanzas += 1;
                    }

                }
                Arco<Integer> arco = grafo.obtenerArco(aux.get(i), aux.get(i + 1));
                kms += arco.getEtiqueta();
            }
            cantBalanzas = cantBalanzas / 2;
            if (cantBalanzas <= cantBalanzasAnt && cantBalanzas <= MAX_BALANZAS) {
                retorno = aux;
            }
            if (kmsant > kms && cantBalanzas <= MAX_BALANZAS) {
                retorno = aux;
            }
//            System.out.println(kms + " kilometros"); // solo con fines visuales
//            System.out.println(cantBalanzas + " numero de balanzas"); // solo con fines visuales

            kmsant = kms;
            cantBalanzasAnt = cantBalanzas;
        }

        imprimirRetorno(retorno, kms); // solo con fines visuales
        System.out.println(retorno);
        return retorno;
    }


    private void imprimirRetorno(ArrayList<Integer> retorno, int kms) {
        Iterator<Integer> iterador = retorno.iterator();
        String salida = "";
        while (iterador.hasNext()) {
            salida += " " + ciudades.get(iterador.next()).getNombre();
        }
        System.out.println("Ruta: " + salida + " de " + kms + " kms");
    }


    // TODO Aca hacer la logica que corresponda

    // Obtengo todas las rutas salientes de mi ciudad origen
		/*Iterator<Arco<Integer>> iterador = this.grafo.obtenerArcos(origen.getId());

		while (iterador.hasNext()) {
			Arco<Integer> arco = iterador.next(); // Arco que representa la ruta
			int kilometros = arco.getEtiqueta(); // Kilometros de la ruta
			int idDestino = arco.getVerticeDestino(); // ID de ciudad destino
			Ciudad ciudadAdyacente = this.ciudades.get(idDestino); // Objeto de ciudad destino
		}*/
}
