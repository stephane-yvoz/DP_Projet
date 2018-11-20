package bateauFactories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureFactory {
	
	public final static String LOADING_ERROR = "Impossible de charger la texture: ";
	
	public final static String GRID_PATH = "grille.png";
	public final static String EXPLOSION_PATH = "explosion.png";
	public final static String SPLASH_PATH = "splash.png";
	
	private static BufferedImage grille;
	private static BufferedImage explosion;
	private static BufferedImage splash;
	
	private static TextureFactory instance = null;
	 
	
	private TextureFactory() {
		grille = loadTexture(GRID_PATH);
		explosion = loadTexture(EXPLOSION_PATH);
		splash = loadTexture(SPLASH_PATH);
		
		assert grille != null;
		assert explosion != null;
		assert splash != null;
	}
	
	public static TextureFactory getInstance() {
		if (instance == null) {
			instance = new TextureFactory();
		}
		return instance;
	}
	
	public BufferedImage loadTexture(String path) {
		BufferedImage texture = null;
		
		try {
			texture = ImageIO.read(new File(path));
		} 
		catch (IllegalArgumentException | IOException loadingError) {
			System.err.println(LOADING_ERROR + path);
			System.exit(1);
		}
		
		return texture;
	}
	
	public BufferedImage getShipTexture() {
		return new BufferedImage(getArea(), 
				                 getArea(), 
				                 BufferedImage.TYPE_INT_ARGB
		);
	}
	
	public BufferedImage getShotTexture() {
		return new BufferedImage(getArea(), 
                                 getArea(), 
                                 BufferedImage.TYPE_INT_ARGB
        ); 
	}
	
	public BufferedImage getExplosionTexture() {
		return null;
	}
	
	public BufferedImage getSplashTexture() {
		return null;
	}
	
	public BufferedImage getTexGrille() {
		return grille;
	}

	/**
	 * Côté d'une case de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getArea() {
		return grille.getWidth() / 11;
	}

	public int getShipArea() {
		return getArea();
	}
	
	public int getShotArea() {
		return getArea();
	}
	
	/**
	 * Largeur de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getGridWidth() {
		return grille.getWidth();
	}
	
	/**
	 * Hauteur de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getGridHeight() {
		return grille.getHeight();
	}
	
}
