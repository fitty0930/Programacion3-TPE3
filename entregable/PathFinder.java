package ProgramacionIII.tp3.entregable;


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

    public PathFinder(Grafo<?> grafo, int origen, int destino,HashMap<Integer, Ciudad> ciudades, int maxbalanzas) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
        this.origen = origen;
        this.destino = destino;
        this.ciudades=ciudades;
        this.maxbalanzas=maxbalanzas;
    }

    public ArrayList<ArrayList<Integer>> pathFind() {
        int contadorBalanzas=0;
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            int verticeId = it.next();
            colores.put(verticeId, "blanco");
        }
        
        return encontrarCaminos(this.origen, contadorBalanzas);
    }

    private ArrayList<ArrayList<Integer>> encontrarCaminos(int vertice, int contadorBalanzas) { // le paso contador balanzas
        // cada vez que itero y lo reviso DENTRO de la función
        int contador=0;
        contador+=contadorBalanzas;
//        System.out.println(contador);

        colores.put(vertice, "amarillo");
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();

            if (vertice == this.destino) {
                ArrayList<Integer> unicoCamino = new ArrayList<>();
                if(contador<=maxbalanzas){ // si es el destino no deberia sumar
                    unicoCamino.add(vertice);
                    resultado.add(unicoCamino);
                }
            } else {
                if(vertice!=this.origen){ // para que no me cuente si es el origen
                    if(ciudades.get(vertice).isTieneBalanza()){
                        contador++;
                    }
                }

                Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
                while (it.hasNext()) {
                    int adyacente = it.next();
                    if(colores.get(adyacente)!=null){
                        if (colores.get(adyacente).equals("blanco")) {
                            ArrayList<ArrayList<Integer>> caminosParciales = encontrarCaminos(adyacente,contador);

                            for (ArrayList<Integer> caminoParcial: caminosParciales) {
                                ArrayList<Integer> caminoCompleto = new ArrayList<>();

                                if(contador<=maxbalanzas){
                                    caminoCompleto.add(vertice);
                                    caminoCompleto.addAll(caminoParcial);
                                    resultado.add(caminoCompleto);
                                }

                            }

                        }
                    }


                }

            }


        colores.put(vertice, "blanco");


        return resultado;
    }

}

