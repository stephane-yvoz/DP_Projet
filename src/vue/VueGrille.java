package vue;

import modele.Bateau;
import modele.Modele;
import modele.Plateau;

import javax.swing.*;

import bateauFactories.TextureFactory;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {
	
    public final static int WIDTH = 10;
    public final static int HEIGHT = 10;
       		
    protected Modele modele;
    
    protected VueGrille(Modele m) {
        super();
        modele = m;
        modele.addObserver(this);
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère écran
     * @param virtualX
     * @return
     */
    protected int realX(int virtualX) {
    	assert virtualX >= 0;
    	assert virtualX <= TextureFactory.getInstance().getGridWidth();
    	return virtualX * getWidth() / TextureFactory.getInstance().getGridWidth(); 
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère écran
     * @param coordY
     * @return
     */
    protected int realY(int virtualY) {
    	assert virtualY >= 0;
    	assert virtualY <= TextureFactory.getInstance().getGridHeight();
    	return virtualY * getHeight() / TextureFactory.getInstance().getGridHeight(); 
    }
    
    /**
     * Récupérer le plateau associé à la bonne vue
     * @return 
     */
    abstract protected Plateau getPlateau();
    
    /**
     * Récupérer un itérateur sur les bateaux du plateau
     * @return
     */
    protected Iterator<Bateau> shipCollection() {
    	return modele.shipCollection(getPlateau());
    }
    
    /**
     * Dessin de la grille
     * @param g
     */
    public void drawGrid(Graphics g) {
    	g.drawImage(TextureFactory.getInstance().getTexGrille(), 
    			    0, 
    			    0, 
    			    realX(TextureFactory.getInstance().getGridWidth()), 
    			    realY(TextureFactory.getInstance().getGridHeight()), 
    			    0, 
    			    0, 
    			    TextureFactory.getInstance().getGridWidth(), 
    			    TextureFactory.getInstance().getGridHeight(), 
    			    null
        );
    }
    
    /**
     * Dessin d'un bateau
     * @param g
     * @param bateau
     */
    public void drawShip(Graphics g, Bateau bateau) {}
    
    /**
     * Dessin des bateaux
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
     * Dessin d'un tir
     * @param g
     * @param x
     * @param y
     */
    public void drawShot(Graphics g, int x, int y) {}
    
    /**
     * Dessin des tirs
     * @param g
     */
    public void drawShots(Graphics g) {}
    
    /**
     * Dessin du fond
     * @param g
     * @Override
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
