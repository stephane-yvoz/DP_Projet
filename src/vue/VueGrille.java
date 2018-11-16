package vue;

import modele.Modele;
import modele.Plateau;

import javax.swing.*;

import bateauFactories.TextureFactory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {
	
    public final static int WIDTH = 10;
    public final static int HEIGHT = 10;
       		
    protected Modele modele;
    
    protected VueGrille(Modele modele) {
        super();
        this.modele = modele;
        modele.addObserver(this);
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère écran
     * @param virtualX
     * @return
     */
    protected int realX(int virtualX) {
    	return virtualX * getWidth() / TextureFactory.getInstance().getGridWidth(); 
    }
    
    /**
     * Conversion du repère 'virtuel' vers le repère écran
     * @param coordY
     * @return
     */
    protected int realY(int virtualY) {
    	return virtualY * getHeight() / TextureFactory.getInstance().getGridHeight(); 
    }
    
    /**
     * Récupérer le plateau associé à la bonne vue
     * @return 
     */
    abstract protected Plateau getPlateau();
    
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
    			    null);
    }
    
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
