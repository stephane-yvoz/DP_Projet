package modele.joueurs;

import modele.*;

public abstract class Joueur {

	Plateau plateau;
	private boolean isPlayerTurn;
	protected boolean human;
	int tailleBateauActuel = 0;
	protected int tailleMax;
	protected int[] bateauxRestants; // les bateaux à placer
	
	public Joueur(Option o){
		plateau = new Plateau(o.getSize(),o.getEpoque());
		tailleMax = 0;
		int[] bateauxDisponibles = o.getBateauxDisponibles();
		for (int i = 0; i != bateauxDisponibles.length; i++){
			if (bateauxDisponibles[i] > tailleMax)
				tailleMax = bateauxDisponibles[i];
		}
		makeBateauRestants(bateauxDisponibles);
		isPlayerTurn = false;
		human = true;
	}

	private void makeBateauRestants(int[] bateauxDisponibles) {

		bateauxRestants = new int[tailleMax];
		for (int i = 0; i != tailleMax; i++){
			bateauxRestants[i] = 0;
		}
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

	public boolean isDefeated() {
		return plateau.hasShips();
	}
	
	public void diminuerBateauRestants(int index){
		if (index < tailleMax && bateauxRestants[index] > 0){
			bateauxRestants[index] -= 1;
		}
	}

	public boolean checkBateauRestant(){
		boolean tmp = true;
		for (int i = 0; i != tailleMax; i++){
			if (bateauxRestants[i] > 0)
				tmp = false;
		}
		return tmp;
	}

	public abstract void play(Modele m);

	public Plateau getPlateau(){
		return plateau;
	}

	public boolean hasFired(int x, int y) {
		if (plateau.getGrilleEnnemie()[x][y] != Square.SEA)
			return true;
		return false;
	}

	public void hit(int x, int y){ plateau.hit(x, y); };


	public void shotEnemie(Joueur cible, Bateau selected, int x, int y, Square value) {
		boolean b = cible.hasShip(x, y);
		if (b){
			cible.getPlateau().receiveShot(this, x, y, selected.getPuissance());
		}
		else {
			cible.getPlateau().changeGrilleJoueur(x, y, Square.MISSED);
			plateau.changeGrilleEnnemie(x, y, Square.MISSED);
		}
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

	public void setSelectedShip(Bateau b) {
		plateau.setSelectedShip(b);
	}

	public Bateau getSelectedShip(){
		return plateau.getSelectedShip();
	}
	
	public void setTailleBateauActuel(int t){
		tailleBateauActuel = t;
	}
	
	public int getTailleBateauActuel(){
		return tailleBateauActuel ;
	}

	public int getNombreBateau(){
		return plateau.getNbBateau();
	}

}
