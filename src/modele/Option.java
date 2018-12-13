package modele;

import java.util.Observable;

public class Option extends Observable {
	
    private int size;
    private String epoque;

    public Option(){
        size = 10;
        epoque = "III";
    }

    public int getSize() {
        return size;
    }

    public String getEpoque() {
        return epoque;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
        setChanged();
        notifyObservers();
    }
    
}
