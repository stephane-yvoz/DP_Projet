package bateauFactories;

import modele.Bateau;

public class IIIFactory extends BateauFactory {
	
	private static IIIFactory fact = new IIIFactory();
	
	private IIIFactory() {
		horizontal_path = "ship/III/horizontal/";
		vertical_path = "ship/III/vertical/";
		loadShips();
	}
	
	public static IIIFactory getInstance() {
		return fact;
	}
	
	@Override
	public Bateau creerBateau(int taille) {
		switch (taille) {
			case 1:
				return new Bateau(1,taille,1);
			case 2:
				return new Bateau(2,taille,1);
			case 3:
				return new Bateau(3,taille,1);
			case 4:
				return new Bateau(4,taille,1);
			default:
				return null;
		}
	}

}
