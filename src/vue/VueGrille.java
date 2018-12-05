package vue;

import modele.Bateau;
import modele.Modele;
import modele.Plateau;

import javax.swing.*;

import bateauFactories.TextureFactory;

import java.awt.Graphics;
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
public abstract class VueGrille extends JPanel implements Observer {
	
	/**
	 * Borne supérieure des indices des grilles du modèle.
	 */
    public final static int SUP = 10;
       		
    protected Modele modele;
    
    
    protected VueGrille(Modele m) {
        super();
        modele = m;
        modele.addObserver(this);
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
    	return (modelX + 1) * virtualToRealX(TextureFactory.getInstance().getAreaSide());
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
    	return (modelY + 1) * virtualToRealY(TextureFactory.getInstance().getAreaSide());
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
     * Récupérer le plateau associé à la bonne vue.
     * @return 
     */
    abstract protected Plateau getPlateau();
    
    /**
     * Récupérer un itérateur sur les bateaux du plateau.
     * @return
     */
    protected Iterator<Bateau> shipCollection() {
    	return modele.shipCollection(getPlateau());
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
     * Dessin d'un bateau.
     * @param g
     * @param bateau
     */
    public void drawShip(Graphics g, Bateau bateau) {
    	BufferedImage texture = null;
    	
    	if (bateau.isDestroyed()) {
    		texture = TextureFactory.getInstance().getSunkTexture();
    	}
    	else {
    		texture = TextureFactory.getInstance().getShipTexture();
    	}
    	
    	assert texture != null;
    	
    	int posOX = bateau.getOriginX();
    	int posOY = bateau.getOriginY();
    	
    	/* TO COMPLETE */
    	for (int i = 0; i < bateau.getLongueur(); i ++) {
    		drawShipSection(g, texture, posOX, posOY);
    	}  	
    }
    
    /**
     * Dessin d'une section de bateau (pour une seule case).
     * @param g
     * @param texture
     * @param x
     * @param y
     */
    private void drawShipSection(Graphics g, BufferedImage texture, int x, int y) {
    	g.drawImage(texture,
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
     * Dessin des bateaux.
     * @param g
     */
    public void drawShips(Graphics g) {
    	Iterator<Bateau> shipCollection = shipCollection();
    	
    	while (shipCollection.hasNext()) {
    		try {
    			drawShip(g, shipCollection.next());
    		}
    		catch (NoSuchElementException noMoreElements) {}
    	}
    }
    
    /**
     * Dessin d'un tir.
     * @param g
     * @param x
     * @param y
     */
    public void drawShot(Graphics g, int x, int y) {}
    
    /**
     * Dessin des tirs.
     * @param g
     */
    public void drawShots(Graphics g) {}
    
    /**
     * Dessin du fond.
     * @param g
     * @override
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        drawGrid(g);
    }
    
    @Override
    public void update(Observable observable, Object o) {
        repaint();
    }
    
}
