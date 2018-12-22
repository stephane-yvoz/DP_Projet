package modele;

import java.util.Iterator;
import java.util.Observable;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurHumain;
import modele.joueurs.JoueurMachine;

public class Modele extends Observable {

	private int currentPlayer;
	private final int nombrePlayer;
	private Joueur[] joueurs;
	private EtatPartie etatPartie;
	private Option option;

	public Modele(Option option) {
		this.option = option;
		currentPlayer = 0;
		joueurs = new Joueur[2];
		joueurs[0] = new JoueurHumain(option);
		joueurs[1] = new JoueurMachine(option);
		joueurs[0].setPlayerTurn(true);
		etatPartie = EtatPartie.Positioning;
		nombrePlayer = option.getNombrePlayer();		
	}
	
	public EtatPartie getEtat() {
		return etatPartie;
	}
	
	public void setEtat(EtatPartie e) {
		etatPartie = e;
		update();
	}
	
	public Square[][] getShots(Plateau plateau) {
		return plateau.getShots();
	}
	
	public Iterator<Bateau> shipCollection(Plateau plateau) {
		return plateau.shipCollection();
	}
	
    /**
     * @param x coordonne x de l'origine du bateau
     * @param y coordonne y de l'origine du bateau
     * @param longueur longueur du bateau
     * @return
     */
	public boolean canAddShip(int x, int y, int longueur, Orientation o){
		return getCurrentPlayer().getPlateau().canAddShip(x, y, longueur, o);
	}
	
	public void addShip(int y, int x, int longueur, Orientation o){
		getCurrentPlayer().diminuerBateauRestants(longueur - 1);
		getCurrentPlayer().getPlateau().addShip(y, x,longueur,o);
	 	update();
	}

	public void shoot(Joueur cible, int x, int y) {
		if (getCurrentPlayer().hasFired(x, y))
			return;
		Bateau selected = getCurrentPlayer().getSelectedShip();
		Square value = Square.MISSED;
		getCurrentPlayer().shotEnemie(cible, selected, x, y, value);
		checkWinner(cible);
		nextPlayer();
		update();
	}

	private void checkWinner(Joueur cible) {
		if (cible.getNombreBateau() == 0){
			if (cible.isHuman()){
				setEtat(EtatPartie.GameOver);
			}
			else
				setEtat(EtatPartie.Winner);
		}
	}

	public Joueur getCurrentPlayer(){
		return joueurs[currentPlayer];
	}

	private void update() {
		setChanged();
		notifyObservers();
	}

	public Joueur getJoueurs(int i) {
		if (i < nombrePlayer)
			return joueurs[i];
		return null;
	}

	public void nextPlayer(){
		setEtat(EtatPartie.ShipSelection);
		getCurrentPlayer().setPlayerTurn(false);
		currentPlayer += 1;
		if (currentPlayer == nombrePlayer) {
			currentPlayer = 0;
		}
		Joueur p = getCurrentPlayer();
		p.setPlayerTurn(true);
		if (!p.isHuman()) {
			p.play(this);
		}
	}
	
	public String getEpoque() {
		return option.getEpoque();
	}
}
