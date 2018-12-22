package vue;

import java.awt.Graphics;

import bateauFactories.BateauFactory;
import bateauFactories.TextureFactory;

import controller.ControllerClickGrille;

import modele.Modele;
import modele.Square;

@SuppressWarnings("serial")
public class VueGrilleJoueur extends VueGrille {

	private ControllerClickGrille listener;
	
    public VueGrilleJoueur(Modele modele) {
        super(modele);
        listener = new ControllerClickGrille(modele, this, "Main");
		this.addMouseListener(listener);
    }
    
    @Override
	public void drawSquares(Graphics g) {
		Square[][] squares = plateau.getGrilleJoueur();
		
		drawShips(g);
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {				
				drawRightSquare(g, squares, x, y);
			}
		}
	}

}
