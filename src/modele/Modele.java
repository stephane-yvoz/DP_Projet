package modele;

import java.util.Iterator;
import java.util.Observable;

import bateauFactories.BateauFactory;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurHumain;
import modele.joueurs.JoueurMachine;

public class Modele extends Observable {
	
	private Plateau plateau1;
	private Plateau plateau2;
	private int currentPlayer;
	private Joueur[] joueurs;
	private BateauFactory bateauFactory;

	public Modele(Option option) {
		currentPlayer = 0;
		joueurs = new Joueur[2];
		joueurs[0] = new JoueurHumain(option);
		joueurs[1] = new JoueurMachine(option);
		bateauFactory = BateauFactory.getInstance(option.getEpoque());

	}
	
	public Plateau getPlateau1() {
		return plateau1;
	}
	
	public Plateau getPlateau2() {
		return plateau2;
	}

	public int[][] getShots(Plateau plateau) {
		assert plateau == plateau1 || plateau == plateau2;
		return plateau.getShots();
	}
	
	public Iterator<Bateau> shipCollection(Plateau plateau) {
		assert plateau == plateau1 || plateau == plateau2;
		return plateau.shipCollection();
	}
	
    /**
     * 
     * @param x coordonne x de l'origine du bateau
     * @param y coordonne y de l'origine du bateau
     * @param longueur longueur du bateau
     * @param xdir coordonne x de la case vers laquelle on oriente le bateau
     * @param ydir coordonne y de la case vers laquelle on oriente le bateau
     * @return
     */
	public boolean canAddShip(int x, int y, int longueur, int xdir, int ydir){
		return plateau1.canAddShip(x, y, longueur, xdir, ydir);
	}
	
	public void addShip(int x, int y, int longueur, int xdir, int ydir){
	 	plateau1.AddShip(x, y, longueur, xdir, ydir,bateauFactory.creerBateau(longueur));
	 	update();
	}

	public void shoot(Joueur cible, int x, int y){
		boolean touched = cible.gotTouched(x, y);
		if (touched){
			;
		}
		else{
			;
		}
		update();
	}

	private void update() {
		setChanged();
		notifyObservers();
	}
}
