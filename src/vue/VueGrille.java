package vue;

import modele.*;

import javax.swing.*;

import bateauFactories.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

/**
 * On distingue 3 repères pour nos transformations d'images :
 * Un repère 'modèle' pour les indices des bateaux dans la grille (compris entre 0 et 10).
 * Un repère 'virtuel' pour les coordonnées qui s'appuient sur la taille des textures.
 * Un repère 'écran' pour les coordonnées réelles (affichées) des objets.
 */
@SuppressWarnings("serial")
public abstract class VueGrille extends JPanel implements Observer {
	
	/**
	 * Borne supérieure des indices des grilles du modèle.
	 */
    protected final static int SUP = 10;
       		
    protected Modele modele;
    protected Plateau plateau;

    
    protected VueGrille(Modele m) {
        super();
        modele = m;
        modele.addObserver(this);
        plateau = m.getJoueurs(0).getPlateau();
    }
    
    /**
     * Conversion du repère 'modèle' vers le repère 'écran'.
     * Seulement pour les éléments à dessiner dans une case de la grille.
     * @param modelX : indice case
     * @return realX
     */
    protected int modelToRealX(int modelX) {
    	assert modelX >= 0;
    	assert modelX < SUP;
    	return (modelX + 1) * getWidth() / (SUP + 1);
    }
    
    /**
     * Conversion du repère 'modèle' vers le repère 'écran'.
     * Seulement pour les éléments à dessiner dans une case de la grille.
     * @param modelY : indice case
     * @return realY
     */
    protected int modelToRealY(int modelY) {
    	assert modelY >= 0;
    	assert modelY < SUP;
    	return (modelY + 1) * getHeight() / (SUP + 1);
    }
    
    /**
     * Conversion du repère 'écran' vers le repère 'virtuel'.
     * @param realX : coordonnée réelle
     * @return virtualX
     */
    protected int realToVirtualX(int realX) {
    	assert realX >= 0;
    	assert realX < getWidth();
    	return realX / getWidth() * TextureFactory.getInstance().getGridWidth();
    }
    
    /**
     * Conversion du repère 'écran' vers le repère 'virtuel'.
     * @param realY : coordonnée réelle
     * @return virtualY
     */
    protected int realToVirtualY(int realY) {
    	assert realY >= 0;
    	assert realY < getHeight();
    	return realY / getHeight() * TextureFactory.getInstance().getGridHeight();
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère 'écran'.
     * @param virtualX : coordonnée du monde 'virtuel'
     * @return realX
     */
    protected int virtualToRealX(int virtualX) {
    	assert virtualX >= 0;
    	assert virtualX <= TextureFactory.getInstance().getGridWidth();
    	return virtualX / TextureFactory.getInstance().getGridWidth() * getWidth(); 
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère 'écran'.
     * @param virtualY : coordonnée du monde 'virtuel'
     * @return realY
     */
    protected int virtualToRealY(int virtualY) {
    	assert virtualY >= 0;
    	assert virtualY <= TextureFactory.getInstance().getGridHeight();
    	return virtualY / TextureFactory.getInstance().getGridHeight() * getHeight(); 
    }

    
    /**
     * Récupérer un itérateur sur les bateaux du plateau.
     * @return
     */
    protected Iterator<Bateau> shipCollection() {
    	return modele.shipCollection(plateau);
    }
    
    /**
     * Dessin de la grille.
     * @param g
     */
    public void drawGrid(Graphics g) {
    	g.drawImage(TextureFactory.getInstance().getGridTexture(),
    			    0, 
    			    0, 
    			    virtualToRealX(TextureFactory.getInstance().getGridWidth()),
    			    virtualToRealY(TextureFactory.getInstance().getGridHeight()),
    			    0, 
    			    0, 
    			    TextureFactory.getInstance().getGridWidth(), 
    			    TextureFactory.getInstance().getGridHeight(), 
    			    null
        );
    }
    
    /**
     * Dessin de toutes les cases de la grille.
     * @param g
     */
    public abstract void drawSquares(Graphics g);
    
    /**
     * Dessin d'une case.
     * @param g
     * @param squareTexture
     * @param x
     * @param y
     */
    protected void drawSquare(Graphics g, BufferedImage squareTexture, int x, int y) {
    	g.drawImage(squareTexture,
		            modelToRealX(x),
		            modelToRealY(y),
		            modelToRealX(x + 1),
		            modelToRealY(y + 1), 
	                0, 
	                0, 
	                TextureFactory.getInstance().getAreaSide(), 
	                TextureFactory.getInstance().getAreaSide(),
	                null
	    );
    }
    
    /**
     * Détermine le bon type de case à dessiner et la dessine.
     * @param g
     * @param squares
     * @param x
     * @param y
     */
    protected void drawRightSquare(Graphics g, Square[][] squares, int x, int y) {
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
    
    /**
     * Dessin d'un bateau.
     * @param g
     * @param bateau
     */
    public void drawShip(Graphics graphicContext, Bateau bateau) {
    	int minX = bateau.getMinX();
    	int minY = bateau.getMinY();
    	int longueur = bateau.getLongueur();
    	
    	for (int i = 0; i < bateau.getLongueur(); i ++) {
    		if (bateau.isVertical()) {
    			// drawShipSection(g, texture, minX, minY + i);
    		}
    		else if (bateau.isHorizontal()) {
    			// drawShipSection(g, texture, minX + i, minY);
    		}
    	}  	
    }
    
    /**
     * Dessin des bateaux.
     * @param g
     */
    public void drawShips(Graphics g) {
    	Iterator<Bateau> shipCollection = shipCollection();
    	Bateau currentShip;
    	
    	while (shipCollection.hasNext()) {
    		try {
    			currentShip = shipCollection.next();
    			
    			if (!currentShip.isDestroyed()) {
    				drawShip(g, currentShip);
    			}    			
    		}
    		catch (NoSuchElementException noMoreElements) {}
    	}
    }
    
    /**
     * Dessin de la grille de jeu.
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawSquares(g);
        if (modele.getEtat() == EtatPartie.Winner)
            writeText(g, "Vous avez gagné");
        else if (modele.getEtat() == EtatPartie.GameOver)
            writeText(g, "Vous avez perdu");
    }

    private void writeText(Graphics crayon, String text){
        Font font = new Font("Serif", Font.PLAIN, 60);
        crayon.setFont(font);
        FontMetrics metrics = crayon.getFontMetrics(font);
        crayon.setColor(Color.RED);
        int y = HEIGHT / 2;
        int x = (WIDTH - metrics.stringWidth(text)) / 2;
        crayon.drawString(text, x, y);
    }

    @Override
    public void update(Observable observable, Object o) {
    	repaint();
    }

}
