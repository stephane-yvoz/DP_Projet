package bateauFactories;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Bateau;

public abstract class BateauFactory {
	
	public final static String LOADING_ERROR = "Impossible de charger la texture: ";
	public final static String WRONG_SHIP_SIZE = "Impossible de récupérer un bateau de cette taille.";
	
	protected static int MAX_SHIP_SIZE = 5;
	
	protected static String horizontal_path;
	protected static String vertical_path;
	
	protected static BufferedImage horizontalShips[];
	protected static BufferedImage verticalShips[];
	
	
	public static BateauFactory getInstance(String epoque) {
		switch (epoque) {
		    case "III":
			    return IIIFactory.getInstance();
		    case "XX":
			    return XXFactory.getInstance();
		    default:
			    throw new IllegalArgumentException("'" + epoque + "' n'existe pas.");
		}
	}
	
	protected void loadShips() {
		horizontalShips = new BufferedImage[MAX_SHIP_SIZE];
		verticalShips = new BufferedImage[MAX_SHIP_SIZE];
		
		for (int i = 1; i <= MAX_SHIP_SIZE; i ++) {
			horizontalShips[i - 1] = loadTexture(horizontal_path + i + ".png");
			verticalShips[i - 1] = loadTexture(vertical_path + i + ".png");
		}
	}
	
	/**
	 * Récupérer la texture d'un bateau horizontal d'une certaine taille.
	 * @param size
	 * @return texture
	 */
	public BufferedImage getHorizontalShipTexture(int size) {
		if (size < 1 && size > MAX_SHIP_SIZE) {
			throw new IllegalArgumentException(WRONG_SHIP_SIZE);
		}
		
		return horizontalShips[size - 1];
	}
	
	/**
	 * Récupérer la texture d'un bateau vertical d'une certaine taille.
	 * @param size
	 * @return texture
	 */
	public BufferedImage getVerticalShipTexture(int size) {
		if (size < 1 && size > MAX_SHIP_SIZE) {
			throw new IllegalArgumentException(WRONG_SHIP_SIZE);
		}
		
		return verticalShips[size - 1];
	}
	
	private BufferedImage loadTexture(String path) {
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
	
	public abstract Bateau creerBateau(int taille);
	
}
