package modele;

import java.util.Observable;

public class Option extends Observable {
	
    private int size;
    private int nombrePlayer;
    private String epoque;
    private int[] bateauxDisponibles;
    private boolean displayOption;

    public Option(){
        setDefault();
        displayOption = true;
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

    public boolean isDisplayOption() {
        return displayOption;
    }

    public void stopDisplayOption() {
        this.displayOption = false;
        update();
    }

    public void setDefault() {
        size = 10;
        epoque = "III";
        bateauxDisponibles = new int[]{ 4,2,2,1};
        nombrePlayer = 2;
    }

    public boolean getDisplayOption(){
        return displayOption;
    }

    private void update() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Epoque : ").append(epoque).append("\n");
        sb.append("size : ").append(size).append("\n");
        sb.append("Nombre de joueur : ").append(nombrePlayer).append("\n");
        return sb.toString();
    }
}
