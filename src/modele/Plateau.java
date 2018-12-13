package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {
	
	private int[][] grilleJoueur; 
	private int[][] grilleEnnemie; // liste des coups portés (pas de coup, coup dans le vide, coup au but)
	
	private ArrayList<Bateau> bateaux;
	
	/**
	 * On considère qu'un plateau est carré
	 * @param taille
	 */
	public Plateau(int taille) {  
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
		int directionX = -1; 
		int directionY = -1; 
		
		if(xdir!=x && ydir==y){ // bateau en position horizontale
			directionX = (xdir-x)/Math.abs(xdir-x);  // 
			int i=0;
			while(Math.abs(i)<longueur && accept){
				if( x+i>=grilleJoueur.length ||x+i<0 ){ //on sort du terrain
					accept=false;
				}else if(grilleJoueur[x+i][y]!=0){ // si la case n'est pas libre
					accept=false;
				}
				i+=directionX;
			}
		}else if (ydir!=y && xdir==x){// bateau en position verticale
			directionY = (ydir-y)/Math.abs(ydir-y);
			int i=0;
			while(Math.abs(i)<longueur  && accept){
				if( y+i>=grilleJoueur.length ||y+i<0 ){ //on sort du terrain
					accept=false;
				}else if(grilleJoueur[x][y+i]!=0){ // si la case n'est pas libre
					accept=false;
				}
				i+=directionY;
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
		if(o !=null) {
			bateau.setPosition(x, y, o);
			bateaux.add(bateau);
			int direction = 0;
			if(xdir!=x && ydir==y){ // bateau en position horizontale
				direction = (xdir-x)/Math.abs(xdir-x);  // 
				int i=0;
				while(Math.abs(i)<longueur  ){
					grilleJoueur[x+i][y] = 1 ;//TODO changer avec Enumeration
					i+=direction;
				}
			}else if (ydir!=y && xdir==x){// bateau en position verticale
				direction = (ydir-y)/Math.abs(ydir-y);
				int i=0;
				while(Math.abs(i)<longueur ){
					grilleJoueur[x][y+i]=1;//TODO changer avec Enumeration
					i+=direction;
				}
			}
		}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append("Plateau joueur : \n");
		for (int i = 0; i != grilleJoueur.length; i++){
			sb.append("[ ");
			for (int j = 0; j != grilleJoueur[i].length; j++){
				sb.append(grilleJoueur[i][j]).append(" ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}

	public boolean isTouched(int x, int y){
		if (grilleJoueur[x][y] >= 0) {
			return true;
		}
		return false;
	}

	public void hit(int x, int y) {
		grilleJoueur[x][y] = -1;
	}

	public void setShot(int x, int y, int value) {
		grilleEnnemie[x][y] = value;
	}
}
