package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import bateauFactories.BateauFactory;

public class Plateau {
	
	private Square[][] grilleJoueur; 
	private Square[][] grilleEnnemie; 
	private ArrayList<Bateau> bateaux;
	private Bateau selected;
	private String epoque;
	
	/**
	 * On considère qu'un plateau est carré
	 * @param taille
	 */
	public Plateau(int taille, String e) {  
		grilleJoueur = new Square[taille][taille];
		grilleEnnemie = new Square[taille][taille];
		
		for(int x = 0; x < taille; x ++){
			for(int y = 0; y < taille; y ++){
				grilleJoueur[x][y] = Square.SEA;
				grilleEnnemie[x][y] = Square.SEA;
			}
		}
		
		bateaux = new ArrayList<Bateau>();
		selected = null;
		epoque = e;
	}
 
	public Square[][] getShots() {
		return grilleEnnemie;
	}
	
	public Iterator<Bateau> shipCollection() {
		return bateaux.iterator();
	}
	
	/**
	 * Le plateau a-t-il encore des bateaux?
	 * @return
	 */
	public boolean hasShips() {
		return bateaux.isEmpty();
	}
	
	/**
	 * 
	 * @param x coordonne x de l'origine du bateau
	 * @param y coordonne y de l'origine du bateau
	 * @param longueur longueur du bateau
	 * @return
	 */
	public boolean canAddShip(int x, int y, int longueur, Orientation o){
		boolean accept = true;
		int directionX = 0; 
		int directionY = 0; 
		
		if(o == Orientation.EAST ){ 
			directionX = 1;
		}else if(o == Orientation.WEST){
			directionX = -1;
		}else if(o == Orientation.SOUTH){
			directionY = 1;
		}else if(o == Orientation.NORTH){
			directionY = -1;
		}
		int i=0;
		while(Math.abs(i)<longueur && accept){
			if( x+(i*directionX)>=grilleJoueur.length ||x+(i*directionX)<0 ||y+(i*directionY)>=grilleJoueur.length ||y+(i*directionY)<0 ){ //on sort du terrain
				accept=false;
			}else if(grilleJoueur[x+(i*directionX)][y+(i*directionY)]!= Square.SEA){ // si la case n'est pas libre
				accept=false;
			}
			i++;
		}
		/*}else if (ydir!=y && xdir==x){// bateau en position verticale
			directionY = (ydir-y)/Math.abs(ydir-y);
			int i=0;
			while(Math.abs(i)<longueur  && accept){
				if( y+i>=grilleJoueur.length ||y+i<0 ){ //on sort du terrain
					accept=false;
				}else if(grilleJoueur[x][y+i]!= Square.SEA){ // si la case n'est pas libre
					accept=false;
				}
				i+=directionY;
			}
		}else{
			accept=false;
		}*/
		return accept;	
	}
	
	/**
	 * Ne pas utiliser sans avoir testé auparavant avec canAddShip
	 * @param x
	 * @param y
	 * @param longueur
	 * @param o
	 */
	public void addShip(int x, int y,int longueur,Orientation o) {
		if(o != null) {
			Bateau b = BateauFactory.getInstance(epoque).creerBateau(longueur);
			
			b.setPosition(x, y, o);
			bateaux.add(b);
			
			for(Point p : b.getOccupiedPositions()){
				grilleJoueur[p.x][p.y] = Square.SHIP;
			}			
		}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder(40);
		sb.append("Plateau joueur : \n");
		for (int i = 0; i != grilleJoueur.length; i++){
			sb.append("[ ");
			for (int j = 0; j != grilleJoueur[i].length; j++){
				sb.append(grilleJoueur[i][j]).append(" ");
			}
			sb.append("]\n");
		}
		sb.append("Plateau ennemie : \n");
		for (int i = 0; i != grilleEnnemie.length; i++){
			sb.append("[ ");
			for (int j = 0; j != grilleEnnemie[i].length; j++){
				sb.append(grilleEnnemie[i][j]).append(" ");
			}
			sb.append("]\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public boolean isTouched(int x, int y){
		return grilleJoueur[x][y] == Square.SHIP;
	}

	public void hit(int x, int y) {
		grilleJoueur[x][y] = Square.HIT;
	}

	public void setShot(int x, int y, Square value) {
		grilleEnnemie[x][y] = value;
	}

	public Square[][] getGrilleEnnemie() {
		return grilleEnnemie;
	}

	public Square[][] getGrilleJoueur() {
		return grilleJoueur;
	}
	
	public void receiveShot(int x, int y, int puissance) {
		for(Bateau b : bateaux){
			for(Point p : b.getOccupiedPositions()){
				if(p.x==x && p.y==y){
					b.takeHit(puissance);
				}
			}
			if(b.isDestroyed()){
				for(Point p : b.getOccupiedPositions()){
					grilleJoueur[p.x][p.y]=Square.SUNK;
				}
			}
		}
	}
	
	/**
	 * Les coordonnées sont-elles celles d'un bateau ?
	 * @param x
	 * @param y
	 * @return vrai si les coordonnées sont sur un bateau
	 */
	public boolean hasShip(int x, int y) {
		boolean has = false;
		for(Bateau b : bateaux){
			for(Point p : b.getOccupiedPositions()){
				if(p.x==x && p.y==y){
					has = true;
				}
			}
		}
		return has;
	}
	
	/**
	 * Choisir le bateau avec lequel on va tirer.
	 * @param x
	 * @param y
	 */
	public void setSelectedShip(int x, int y) {
		for(Bateau b : bateaux){
			for(Point p : b.getOccupiedPositions()){
				if(p.x==x && p.y==y){
					selected = b;
				}
			}
		}
	}
	
	/**
	 * @return bateau sélectionné
	 */
	public Bateau getSelectedShip() {
		return selected;
	}
	
	
}
