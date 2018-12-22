package bateauFactories;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureFactory {
	
	public final static String LOADING_ERROR = "Impossible de charger la texture: ";
	public final static String WRONG_SHIP_SIZE = "Impossible de récupérer un bateau de cette taille.";
	
	public final static String GRID_PATH = "grid.png";
	public final static String EXPLOSION_PATH = "explosion.png";
	public final static String FUEL_PATH = "fuel.png";
	public final static String SPLASH_PATH = "splash.png";
	
	private static BufferedImage grid;
	private static BufferedImage ship;
	private static BufferedImage explosion;
	private static BufferedImage fuel;
	private static BufferedImage splash;	
	
	private static BufferedImage ships[];
	
	private static int MAX_SHIP_SIZE = 5;
	
	private static TextureFactory instance = null;
	 
	
	private TextureFactory() {
		grid = loadTexture(GRID_PATH);
		explosion = loadTexture(EXPLOSION_PATH);
		fuel = loadTexture(FUEL_PATH);
		splash = loadTexture(SPLASH_PATH);
		
		buildShipTexture();
		loadShips();
		
		assert grid != null;
		assert explosion != null;
		assert fuel != null;
		assert splash != null;
	}
	
	private void buildShipTexture() {
		buildShipTexture(new Color(255, 255, 255, 128));
	}
	
	private void loadShips() {
		ships = new BufferedImage[MAX_SHIP_SIZE];
		
		for (int i = 1; i <= MAX_SHIP_SIZE; i ++) {
			ships[i - 1] = loadTexture("ship" + i + ".png");
		}
	}
	
	public void buildShipTexture(Color shipColor) {
		ship = new BufferedImage(getAreaSide(), 
                                 getAreaSide(), 
                                 BufferedImage.TYPE_INT_ARGB
        );
		
		Graphics2D g2d = ship.createGraphics();
		
		g2d.setColor(shipColor);
	    g2d.fillRect(0, 0, getAreaSide(), getAreaSide());
	    g2d.dispose();
	}
	
	public static TextureFactory getInstance() {
		if (instance == null) {
			instance = new TextureFactory();
		}
		return instance;
	}
	
	public BufferedImage loadTexture(String path) {
		BufferedImage texture = null;
		ClassLoader classLoader = getClass().getClassLoader();
		
		try {
	         texture = ImageIO.read(classLoader.getResource(path));
		} 
		catch (IllegalArgumentException | IOException loadingError) {
			System.err.println(LOADING_ERROR + path);
			System.exit(1);
		}
		
		return texture;
	}
	
	public BufferedImage getShipTexture() {
		return ship;
	}
	
	public void setShipTexture(BufferedImage b) {
		ship = b;
	}
	
	/**
	 * Récupérer la texture pour un bateau de certaine taille.
	 * @param size
	 * @return
	 */
	public BufferedImage getShipTexture(int size) {
		if (size < 1 && size > MAX_SHIP_SIZE) {
			throw new IllegalArgumentException(WRONG_SHIP_SIZE);
		}
		
		return ships[size - 1];
	}
	
	public BufferedImage getShotTexture() {
		return explosion;
	}
	
	public BufferedImage getExplosionTexture() {
		return explosion;
	}
	
	public BufferedImage getSplashTexture() {
		return splash;
	}
	
	public BufferedImage getSunkTexture() {
		return fuel;
	}
	
	public BufferedImage getGridTexture() {
		return grid;
	}

	/**
	 * Côté d'une case de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getAreaSide() {
		return grid.getWidth() / 11;
	}

	public int getShipArea() {
		return getAreaSide();
	}
	
	public int getShotArea() {
		return getAreaSide();
	}
	
	/**
	 * Largeur de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getGridWidth() {
		return grid.getWidth();
	}
	
	/**
	 * Hauteur de la texture grille (repère 'virtuel')
	 * @return
	 */
	public int getGridHeight() {
		return grid.getHeight();
	}
	
}
