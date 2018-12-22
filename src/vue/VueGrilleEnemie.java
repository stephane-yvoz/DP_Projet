package vue;

import modele.Modele;
import modele.Square;

import java.awt.Graphics;

import bateauFactories.BateauFactory;
import bateauFactories.TextureFactory;

import controller.ControllerClickGrille;

@SuppressWarnings("serial")
public class VueGrilleEnemie extends  VueGrille {
	
	private ControllerClickGrille listener;
	
    public VueGrilleEnemie(Modele modele) {
        super(modele);
        this.setEnabled(false);
        listener = new ControllerClickGrille(modele, this, "ViewShots");
		this.addMouseListener(listener);
    }
    
    @Override
	public void drawSquares(Graphics g) {
		Square[][] squares = plateau.getGrilleEnnemie();
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {
				drawRightSquare(g, squares, x, y);
			}
		}
	}

}
