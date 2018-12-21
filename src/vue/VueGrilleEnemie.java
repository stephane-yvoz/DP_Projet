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
		String epoch = modele.getEpoque();
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {
				switch (squares[x][y]) {
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
