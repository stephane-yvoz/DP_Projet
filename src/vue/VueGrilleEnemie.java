package vue;

import modele.Modele;
import modele.Plateau;
import modele.Square;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import bateauFactories.TextureFactory;

import controller.ControllerClickGrille;

public class VueGrilleEnemie extends VueGrille {
	
	private ControllerClickGrille listener;
	
    public VueGrilleEnemie(Modele modele) {
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
	public void drawSquares(Graphics g) {
		Square[][] squares = modele.getEnemyGrid();
		
		for (int x = 0; x < SUP; x ++) {
			for (int y = 0; y < SUP; y ++) {				
				switch (squares[x][y]) {
				    case HIT: 
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
