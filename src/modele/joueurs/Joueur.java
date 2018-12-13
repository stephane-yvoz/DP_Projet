package modele.joueurs;

import modele.Modele;
import modele.Option;
import modele.Plateau;
import modele.Square;

public abstract class Joueur {

	Plateau plateau;
	private boolean isPlayerTurn;
	protected boolean human;

	int[] bateauxDisponibles;
	
	public Joueur(Option o){
		plateau = new Plateau(o.getSize());
		bateauxDisponibles = o.getBateauxDisponibles();
		isPlayerTurn = false;
		human = true;
	}

	public abstract void play(Modele m);

	public Plateau getPlateau(){
		return plateau;
	}

	public boolean gotTouched(int x, int y){
		return (plateau.isTouched(x, y));
	}
	
	public int[] getBateauxDisponibles(){
		return bateauxDisponibles;
	}

	public void hit(int x, int y){ plateau.hit(x, y); };

	public void shotEnemie(int x, int y, Square value) {
		plateau.setShot(x, y, value);
	}

	public void setPlayerTurn(boolean b){
		isPlayerTurn = b;
	}

	public boolean isPlayerTurn() {
		return isPlayerTurn;
	}

	public boolean isHuman() {
		return human;
	}
}
