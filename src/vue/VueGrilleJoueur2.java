package vue;

import modele.Modele;
import modele.Plateau;

import java.awt.Graphics;

import controller.ControllerClickGrille;

public class VueGrilleJoueur2 extends VueGrille {
	
	private ControllerClickGrille listener;
	
    public VueGrilleJoueur2(Modele modele) {
        super(modele);
        this.setEnabled(false);
        listener = new ControllerClickGrille(modele,this,"ViewShots");
		this.addMouseListener(listener);
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
