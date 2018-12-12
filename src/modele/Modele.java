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
	
	private Joueur J1;
	private Joueur J2;
	
	private BateauFactory bateauFactory;

	public Modele(Option option) {
		plateau1 = new Plateau(option.getSize());
		plateau2 = new Plateau(option.getSize());
		J1 = new JoueurHumain();
		J2 = new JoueurMachine();
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
	 	setChanged();
	 	notifyObservers();
	}
	
}
