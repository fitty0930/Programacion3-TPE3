package ProgramacionIII.tp3.entregable;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Ciudad> camino;
    private int kms;

    public Solucion(){
        this.camino=new ArrayList<Ciudad>();
        this.kms=0;
    }

    //    O(n)
    public int getKms() {
        return kms;
    }
    //    O(n)
    public void setKms(int kms) {
        this.kms = kms;
    }
    //    O(n)
    public ArrayList<Ciudad> getCamino() {
        return camino;
    }
    //    O(n)
    public void setCamino(ArrayList<Ciudad> camino) {
        this.camino = camino;
    }
}
