package vue;

import modele.Modele;

import javax.swing.*;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {
    public final static int WIDTH = 10;
    public final static int HEIGHT = 10;
    protected Modele modele;

    protected VueGrille(Modele modele){
        super();
        this.modele = modele;
        modele.addObserver(this);
    }
}
