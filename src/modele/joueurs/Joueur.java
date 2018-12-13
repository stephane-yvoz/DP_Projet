package modele.joueurs;

import modele.Option;
import modele.Plateau;

public abstract class Joueur {
	Plateau plateau;
	int[] bateauxDisponibles;
	
	public Joueur(Option o){
		plateau = new Plateau(o.getSize());
		bateauxDisponibles = o.getBateauxDisponibles();
	}

	public abstract void play();

	public Plateau getPlateau(){
		return plateau;
	}

	public boolean gotTouched(int x, int y){
		return (plateau.isTouched(x, y));
	}
	
	public int[] getBateauxDisponibles(){
		return bateauxDisponibles;
	}
}
