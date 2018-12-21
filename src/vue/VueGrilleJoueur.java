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
		String epoch = modele.getEpoque();
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {				
				switch (squares[x][y]) {
				    case SHIP:
				    	drawSquare(g, BateauFactory.getInstance(epoch).getShipTexture(), x, y);
				    	break;
				    case HIT: 
				    	drawSquare(g, BateauFactory.getInstance(epoch).getShipTexture(), x, y);
				    	drawSquare(g, TextureFactory.getInstance().getShotTexture(), x, y);
				    	break;
				    case SUNK:
				    	drawSquare(g, TextureFactory.getInstance().getSunkTexture(), x, y);
				    	break;
				    case MISSED:
				    	drawSquare(g, TextureFactory.getInstance().getSplashTexture(), x, y);
				    	break;
				    default: 
				    	break;
				}
			}
		}
	}

}
