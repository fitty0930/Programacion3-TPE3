package ProgramacionIII.tp3.entregable;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Ciudad> camino;
    private int kms;

    public Solucion(){
        this.camino=new ArrayList<Ciudad>();
        this.kms=0;
    }

    //    O(1)
    public int getKms() {
        return kms;
    }
    //    O(1)
    public void setKms(int kms) {
        this.kms = kms;
    }
    //    O(1)
    public ArrayList<Ciudad> getCamino() {
        return camino;
    }
    //    O(1)
    public void setCamino(ArrayList<Ciudad> camino) {
        this.camino = camino;
    }
}
