package vue;

import modele.Modele;
import modele.Plateau;

import java.awt.Graphics;

public class VueGrilleJoueur2 extends VueGrille {
	
    public VueGrilleJoueur2(Modele modele) {
        super(modele);
        this.setEnabled(false);
    }

    @Override
    public Plateau getPlateau() {
    	return modele.getPlateau2();
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
    }
    
}
