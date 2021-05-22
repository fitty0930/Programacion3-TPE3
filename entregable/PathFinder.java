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

    // O(1)
    public PathFinder(Grafo<?> grafo, int origen, int destino, HashMap<Integer, Ciudad> ciudades, int maxbalanzas) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
        this.origen = origen;
        this.destino = destino;
        this.ciudades = ciudades;
        this.maxbalanzas = maxbalanzas;
    }

    // O(n!+m) = > O(n!)
    // donde n es la cantidad de ciudades del metodo encontrarCamino y m es la cantidad de ciudades
    // iteradas en el hashmap de colores
    public Solucion pathFind() {
        int contadorBalanzas = 0;
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            int verticeId = it.next();
            colores.put(verticeId, "blanco");
        }

        Solucion solucion = encontrarCamino(this.origen, contadorBalanzas, 0);
        if (solucion.getKms() == this.kilometros) {
            solucion.setKms(0);
        }
        return solucion;
    }

    // O(n!) donde n son la cantidad de ciudades (el peor caso es que el contador de balanzas sea un numero muy alto)
    private Solucion encontrarCamino(int vertice, int contadorBalanzas, int kmsPasados) {
        // le paso contador balanzas
        // cada vez que itero y lo reviso DENTRO de la función
        int contador = 0;
        contador += contadorBalanzas;
        int kms = 0;
        kms += kmsPasados;

        colores.put(vertice, "amarillo");

        ArrayList<Ciudad> resultado = new ArrayList<Ciudad>();
        Solucion solucion = new Solucion();
        solucion.setKms(this.kilometros);

        if (vertice == this.destino) {
            if (contador <= maxbalanzas && kms < solucion.getKms()) {
                resultado.add(ciudades.get(vertice)); // agregar la ciudad
                solucion.setCamino(resultado);
                solucion.setKms(kms);
            }
        } else {
            if (vertice != this.origen) {
                if (ciudades.get(vertice).isTieneBalanza()) {
                    contador++;
                }
            }
            if (contador <= maxbalanzas) {
                Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
                while (it.hasNext()) {
                    int adyacente = it.next();
                    Arco<Integer> arco = (Arco<Integer>) grafo.obtenerArco(vertice, adyacente);
                    kms += arco.getEtiqueta();

                    if (colores.get(adyacente) != null) {
                        if (colores.get(adyacente).equals("blanco")) {
                            Solucion caminoParcial = encontrarCamino(adyacente, contador, kms);
                            if (!caminoParcial.getCamino().isEmpty()) {
                                if (solucion.getKms() > caminoParcial.getKms()) {
                                    resultado.clear();
                                    resultado.add(ciudades.get(vertice));
                                    resultado.addAll(caminoParcial.getCamino());

                                    solucion.setCamino(resultado);
                                    solucion.setKms(caminoParcial.getKms());
                                }

                            }
                        }
                    }

                    kms = kms - arco.getEtiqueta(); // es porque me estoy quedando los caminos que ya recorri

                }
            }

        }

        colores.put(vertice, "blanco"); // queda blanco para que pueda reingresar al vertice

        return solucion;

    }


}

