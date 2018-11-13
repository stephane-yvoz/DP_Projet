package vue;

import modele.Modele;

import java.util.Observable;

public class VueGrilleJoueur2 extends VueGrille{
    public VueGrilleJoueur2(Modele modele) {
        super(modele);
        this.setEnabled(false);
    }

    @Override
    public void update(Observable observable, Object o) {
        ;
    }

}
