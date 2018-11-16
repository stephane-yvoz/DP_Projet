package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Plateau {
	
	private int[][] grilleJoueur; //
	private int[][] grilleEnnemie; // liste des coups portés 
	
	private ArrayList<Bateau> bateaux;
	
	public Plateau(int taille) {  // on considère tous les plateaux carrés
		grilleJoueur = new int[taille][taille];
		grilleEnnemie = new int[taille][taille];
		bateaux = new ArrayList<Bateau>();
	}
 
	public int[][] getShots() {
		return grilleEnnemie;
	}
	
	public Iterator<Bateau> shipCollection() {
		return bateaux.iterator();
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
		boolean accept = true;
		int directionX = (x-xdir)/Math.abs(x-xdir); 
		int directionY = (y-ydir)/Math.abs(y-ydir); 
		if(xdir!=x){ // bateau en position horizontale
			for(int i = 0; Math.abs(i)<longueur;i+=directionX){ 
				if(grilleJoueur[x+i][y]!=0){ // si la case n'est pas libre
					accept=false;
				}
			}
		}else if (ydir!=y){// bateau en position verticale
			for(int i = 0; i<longueur;i+=directionY){
				if(grilleJoueur[x][y+i]!=0){ // si la case n'est pas libre
					accept=false;
				}
			}
		}else{
			accept=false;
		}
		return accept;	
	}
	
	/**
	 * Ne pas utiliser sans avoir testé auparavant avec canAddShip
	 * @param x
	 * @param y
	 * @param longueur
	 * @param xdir
	 * @param ydir
	 * @param bateau 
	 */
	public void AddShip(int x, int y, int longueur, int xdir, int ydir, Bateau bateau) {
		Orientation o =null;
		if(x<xdir && y==ydir){
			o=Orientation.EAST;
		}else if(x>xdir && y==ydir){
			o=Orientation.WEST;
		}else if(x==xdir && y<ydir){
			o=Orientation.SOUTH;
		}else if(x==xdir && y>ydir){
			o=Orientation.NORTH;
		}
		
		bateau.setPosition(x, y, o);	
	}
	
}
