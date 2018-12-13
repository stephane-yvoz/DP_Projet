package modele;

import java.util.Observable;

public class Option extends Observable {
    private int size;
    private int nombrePlayer;
    private String epoque;
    private int[] bateauxDisponibles;

    public Option(){
        size = 10;
        epoque = "III";
        bateauxDisponibles = new int[]{ 4,2,2,1};
        nombrePlayer = 2;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
        setChanged();
        notifyObservers();
    }

    public void setNombrePlayer(int nb){
        nombrePlayer = nb;
    }


    public int getSize() {
        return size;
    }

    public String getEpoque() {
        return epoque;
    }


    public int[] getBateauxDisponibles(){
    	return bateauxDisponibles.clone();
    }

    public int getNombrePlayer() {
        return nombrePlayer;
    }
}
