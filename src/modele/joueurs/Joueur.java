package modele.joueurs;

import modele.Plateau;

public abstract class Joueur {
	Plateau plateau;

	public abstract void play();

	public Plateau getPlateau(){
		return plateau;
	}

	public boolean gotTouched(int x, int y){
		return (plateau.isTouched(x, y));
	}
}
