package modele;

import java.util.ArrayList;

public class Plateau {
	private int[][] grilleJoueur;    //
	private int[][] grilleEnnemie; //liste des coups portés 
	private ArrayList<Bateau> bateaux;
	
	public Plateau(int taille){  // on considére tous les plateaux carrés
		grilleJoueur = new int[taille][taille];
		grilleEnnemie = new int[taille][taille];
		bateaux = new ArrayList<Bateau>();
	}

}
