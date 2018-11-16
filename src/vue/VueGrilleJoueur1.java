package vue;

import java.awt.Graphics;

import modele.Modele;
import modele.Plateau;

public class VueGrilleJoueur1 extends VueGrille {

    public VueGrilleJoueur1(Modele modele) {
        super(modele);
    }
     
    @Override
	public Plateau getPlateau() {
		return modele.getPlateau1();
	}
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
    }

}
