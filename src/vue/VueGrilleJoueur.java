package vue;

import java.awt.Graphics;

import bateauFactories.TextureFactory;

import controller.ControllerClickGrille;

import modele.Modele;
import modele.Plateau;
import modele.Square;

public class VueGrilleJoueur extends VueGrille {

	private ControllerClickGrille listener;
	
    public VueGrilleJoueur(Modele modele) {
        super(modele);
        listener = new ControllerClickGrille(modele,this,"Main");
		this.addMouseListener(listener);
    }
     
    @Override
	public Plateau getPlateau() {
		return modele.getPlateau1();
	}
    
    @Override
	public void drawSquares(Graphics g) {
		Square[][] squares = modele.getPlayerGrid();
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {				
				switch (squares[x][y]) {
				    case SHIP:
				    	drawSquare(g, modele.getShipTexture(), x, y);
				    	break;
				    case HIT: 
				    	drawSquare(g, modele.getShipTexture(), x, y);
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
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
    }

}
