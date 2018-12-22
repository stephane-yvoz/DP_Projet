package modele;

import modele.ordinateur.IACross;
import modele.ordinateur.IARandom;
import modele.ordinateur.Strategie;

import java.util.Observable;

public class Option extends Observable {
	
    private int size;
    private int nombrePlayer;
    private String epoque;
    private String labelStrategie;
    private int[] bateauxDisponibles;
    private boolean displayOption;
    private Strategie strategie;
    private Strategie croix;
    private Strategie aleatoire;

    public Option(){
        setDefault();
        displayOption = true;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
        croix = new IACross();
        aleatoire = new IARandom();
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
        bateauxDisponibles = new int[]{ 2,3,3,4};
        nombrePlayer = 2;
        strategie = new IARandom();
        labelStrategie = "aléatoire";
        update();
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

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(String strategie) {
        switch (strategie){
            case "croix":
                this.strategie = croix;
                break;
            case "aléatoire":
                this.strategie = aleatoire;
                break;
        }
        labelStrategie = strategie;
        update();
    }

    public String getLabelStrategie() {
        return labelStrategie;
    }
}
