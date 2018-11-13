package bateauFactories;

import modele.Bateau;

public class IIIFactory extends BateauFactory {
	

	@Override
	public Bateau creerBateau(int taille) {
		switch (taille){
			case 1:
				return new Bateau(1,taille,1);
			case 2:
				return new Bateau(1,taille,1);
			case 3:
				return new Bateau(2,taille,1);
			case 4:
				return new Bateau(3,taille,2);
			default:
				return null;
		}
	}

}
