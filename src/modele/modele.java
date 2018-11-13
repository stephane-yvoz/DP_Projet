package modele;

import java.util.Observable;

import bateauFactories.BateauFactory;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurHumain;
import modele.joueurs.JoueurMachine;

public class modele extends Observable{
	private Plateau plateau1;
	private Plateau plateau2;
	private Joueur J1;
	private Joueur J2;
	private BateauFactory bateauFactory;
	
	public modele(int taille,String epoque ){
		plateau1=new Plateau(taille);
		plateau2=new Plateau(taille);
		J1 = new JoueurHumain();
		J2 = new JoueurMachine();
		bateauFactory= BateauFactory.getInstance(epoque);
	}
	

}
