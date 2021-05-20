package ProgramacionIII.tp3.entregable;


import ProgramacionIII.tp3.Arco;
import ProgramacionIII.tp3.Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PathFinder {

    private Grafo<?> grafo;
    private HashMap<Integer, String> colores;
    private int origen;
    private int destino;
    private HashMap<Integer, Ciudad> ciudades;
    private int maxbalanzas;
    private int kilometros = 10000;

    public PathFinder(Grafo<?> grafo, int origen, int destino, HashMap<Integer, Ciudad> ciudades, int maxbalanzas) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
        this.origen = origen;
        this.destino = destino;
        this.ciudades = ciudades;
        this.maxbalanzas = maxbalanzas;
    }

    public Solucion pathFind() {
        int contadorBalanzas = 0;
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            int verticeId = it.next();
            colores.put(verticeId, "blanco");
        }
        this.kilometros = 10000;

        ArrayList<Ciudad> resultado = encontrarCamino(this.origen, contadorBalanzas, 0);

        Solucion solucion=new Solucion();
        if (!resultado.isEmpty()) {
            solucion.setKms(kilometros);
            solucion.setCamino(resultado);
        }

        return solucion;
    }

    private ArrayList<Ciudad> encontrarCamino(int vertice, int contadorBalanzas, int kmsPasados) {
        // le paso contador balanzas
        // cada vez que itero y lo reviso DENTRO de la función
        int contador = 0;
        contador += contadorBalanzas;
        int kms = 0;
        kms += kmsPasados;

        colores.put(vertice, "amarillo");

        ArrayList<Ciudad> resultado = new ArrayList<Ciudad>();

        if (vertice == this.destino) {
            if (contador <= maxbalanzas && kms < this.kilometros) {
                resultado.add(ciudades.get(vertice)); // agregar la ciudad
                this.kilometros = kms;
            }
        } else {
            if (vertice != this.origen) {
                if (ciudades.get(vertice).isTieneBalanza()) {
                    contador++;
                }
            }
            if (contador <= maxbalanzas) {
                Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
                while (it.hasNext() && kilometros > kms) {
                    int adyacente = it.next();
                    Arco<Integer> arco = (Arco<Integer>) grafo.obtenerArco(vertice, adyacente);
                    kms += arco.getEtiqueta();

                    if (colores.get(adyacente) != null) {
                        if (colores.get(adyacente).equals("blanco")) {
                            ArrayList<Ciudad> caminoParcial = encontrarCamino(adyacente, contador, kms);
                            if (!caminoParcial.isEmpty()) {
                                resultado.add(ciudades.get(vertice));
                                resultado.addAll(caminoParcial);
                            }
                        }
                    }
                    kms=kms-arco.getEtiqueta(); // es porque me estoy quedando los caminos que ya recorri

                }
            }

        }

        colores.put(vertice, "negro");

        return resultado;

    }


}

