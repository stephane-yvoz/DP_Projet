package bateauFactories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TextureFactory {
	
	public final static String GRID_PATH = "grille.png";
	
	private static BufferedImage grille;
	private static TextureFactory instance = null;
	 
	
	private TextureFactory() {
		try {
			grille = ImageIO.read(new File(GRID_PATH));
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static TextureFactory getInstance() {
		if (instance == null) {
			instance = new TextureFactory();
		}
		return instance;
	}
	
	public BufferedImage getTexGrille() {
		return grille;
	}

	public int getArea() {
		return grille.getWidth() / 11;
	}
	
	public int getGridWidth() {
		return grille.getWidth();
	}
	
	public int getGridHeight() {
		return grille.getHeight();
	}
	
}
