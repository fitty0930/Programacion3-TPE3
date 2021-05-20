package ProgramacionIII.tp3.entregable;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Ciudad> camino;
    private int kms;

    public Solucion(){
        this.camino=new ArrayList<Ciudad>();
        this.kms=0;
    }

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public ArrayList<Ciudad> getCamino() {
        return camino;
    }

    public void setCamino(ArrayList<Ciudad> camino) {
        this.camino = camino;
    }
}
