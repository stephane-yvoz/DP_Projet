package bateauFactories;

import java.awt.image.BufferedImage;

import modele.Bateau;

public abstract class BateauFactory {
	
	protected BufferedImage ship;
	
	public static BateauFactory getInstance(String epoque) {
		switch (epoque) {
		    case "III":
			    return new IIIFactory();
		    case "XX":
			    return new XXFactory();
		    default:
			    throw new IllegalArgumentException("'" + epoque + "' n'existe pas.");
		}
	}
	
	public abstract Bateau creerBateau(int taille);

	public BufferedImage getShipTexture() {
		return ship;
	}
	
}
