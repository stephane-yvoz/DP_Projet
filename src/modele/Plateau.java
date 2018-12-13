package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {
	
	private Square[][] grilleJoueur; 
	private Square[][] grilleEnnemie; 
	
	private ArrayList<Bateau> bateaux;
	
	/**
	 * On considère qu'un plateau est carré
	 * @param taille
	 */
	public Plateau(int taille) {  
		grilleJoueur = new Square[taille][taille];
		grilleEnnemie = new Square[taille][taille];
		for(int i =0;i <taille;i++){
			for(int j =0;j <taille;j++){
				grilleJoueur[i][j] = Square.SEA;
				grilleEnnemie[i][j] = Square.SEA;
			}
		}
		bateaux = new ArrayList<Bateau>();
	}
 
	public Square[][] getShots() {
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
				}else if(grilleJoueur[x+i][y]!= Square.SEA){ // si la case n'est pas libre
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
				}else if(grilleJoueur[x][y+i]!= Square.SEA){ // si la case n'est pas libre
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
			for(Point p : bateau.getOccupiedPositions()){
				grilleJoueur[p.x][p.y]=Square.SHIP;
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
		return grilleEnnemie[x][y] == Square.HIT;
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
	
	public void receiveShot(int x, int y, int puissance){
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
}
