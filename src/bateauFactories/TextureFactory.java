package bateauFactories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TextureFactory {
	private static BufferedImage grille;
	private static TextureFactory instance = null;
	 
	
	private TextureFactory(){
		try {
			grille =  ImageIO.read(new File("grille.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TextureFactory getInstance(){
		if(instance==null){
			instance = new TextureFactory();
		}
		return instance;
	}
	
	public BufferedImage getTexGrille(){
		return grille;
	}

}
