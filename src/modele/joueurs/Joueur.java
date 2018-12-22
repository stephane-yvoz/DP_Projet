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
	int tailleBateauActuel =2;
	protected int tailleMax;
	protected int[] bateauxRestants; // les bateaux à placer
	
	public Joueur(Option o){
		plateau = new Plateau(o.getSize(),o.getEpoque());
		bateauxDisponibles = o.getBateauxDisponibles();
		tailleMax = 0;
		for (int i = 0; i != bateauxDisponibles.length; i++){
			if (bateauxDisponibles[i] > tailleMax)
				tailleMax = bateauxDisponibles[i];
		}
		makeBateauRestants();
		isPlayerTurn = false;
		human = true;
	}

	private void makeBateauRestants() {
		bateauxRestants = new int[tailleMax];
		for (int i = 0; i != tailleMax; i++){
			bateauxRestants[i] = 0;
		}
		for (int i = 0; i != bateauxDisponibles.length; i++)
			System.out.println(bateauxDisponibles[i] + " ");
		for (int i = 0; i != bateauxDisponibles.length; i++){
			int numero = bateauxDisponibles[i];
			bateauxRestants[numero - 1] += 1;
		}
	}

	public int[] getBateauxRestants(){
		return bateauxRestants;
	}

	public int getTailleMax(){
		return tailleMax;
	}

	public void diminuerBateauRestants(int index){
		if (index < tailleMax && bateauxRestants[index] > 0){
			bateauxRestants[index] -= 1;
		}
	}

	public abstract void play(Modele m);

	public Plateau getPlateau(){
		return plateau;
	}

	public boolean gotTouched(int x, int y){
		return (plateau.isTouched(x, y));
	}

	public boolean hasFired(int x, int y) {
		if (plateau.getGrilleEnnemie()[x][y] != Square.SEA)
			return true;
		return false;
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

	public boolean hasShip(int xCaseClic, int yCaseClic) {
		return plateau.hasShip(xCaseClic, yCaseClic);
	}

	public void setSelectedShip(int x, int y) {
		plateau.setSelectedShip(x, y);
	}
	
	public void setTailleBateauActuel(int t){
		tailleBateauActuel = t;
		diminuerBateauRestants(t - 1);
	}
	
	public int getTailleBateauActuel(){
		return tailleBateauActuel ;
	}
}
