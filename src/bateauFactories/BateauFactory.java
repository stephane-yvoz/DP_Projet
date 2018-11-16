package bateauFactories;

import modele.Bateau;

public abstract class BateauFactory {
	
	public static BateauFactory getInstance(String epoque){
		switch (epoque) {
		    case "III":
			    return new IIIFactory();
		    case "XX":
			    return new XXFactory();
		    default:
			    return null;  // TODO créer une exception
		}
	}
	
	public abstract Bateau creerBateau(int taille);

}
