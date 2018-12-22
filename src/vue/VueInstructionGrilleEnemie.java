package vue;

import modele.EtatPartie;
import modele.Modele;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueInstructionGrilleEnemie extends JToolBar implements Observer {
    private JLabel text;
    public VueInstructionGrilleEnemie(Modele m){
        text = new JLabel("En attente de selection d'un bateau");
        this.add(text);
        m.addObserver(this);
        setFloatable( false);
    }

    @Override
    public void update(Observable o, Object arg) {
        Modele m = (Modele) o;
        if (m.getEtat() == EtatPartie.ShipShoot)
            text.setText("Selectionnez une case où tirer");
        else
            text.setText("En attente de selection d'un bateau");
    }
}
