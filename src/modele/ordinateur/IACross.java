package modele.ordinateur;

import java.awt.Point;

import modele.Orientation;
import modele.Square;
import modele.joueurs.Joueur;

public class IACross extends Strategie {
	
	private enum Phase  {SearchCross,SearchOrientation,SearchLongueur};
	
	private Phase enCours = Phase.SearchCross;
	private Point lastHitCross = null;
	private Point lastHitLongueur =null;
	
	private Orientation[] ordreCherche = {Orientation.EAST,Orientation.SOUTH,Orientation.NORTH,Orientation.WEST};
	private int orient = 0;

	public IACross(){
		super();
	}

	@Override
	public Point jouer(Joueur player) {
		Square[][] etatTirs= player.getPlateau().getGrilleEnnemie();
		Point choix =null;
		detectionPhase(etatTirs);
		switch(enCours){
		case SearchCross:
			choix = searchCross(etatTirs);
			break;
		case SearchOrientation:
			choix = searchOrientation(etatTirs);
			break;
		case SearchLongueur:
			choix = searchLongueur(etatTirs);
			break;
		}
		return choix;
	}

	private void detectionPhase(Square[][] etatTirs) {
		if(enCours == Phase.SearchCross && etatTirs[lastHitCross.x][lastHitCross.y] == Square.HIT){
			//on vient de trouver un des bateaux enemis
			enCours= Phase.SearchOrientation;
		}else if(enCours == Phase.SearchOrientation && orient==ordreCherche.length){
			// si on vient de finir les recherches autour d'un point
			orient =0;
			enCours =Phase.SearchCross;
		}else if(enCours == Phase.SearchLongueur && etatTirs[lastHitLongueur.x][lastHitLongueur.y] == Square.MISSED){
			//si on a fini de frapper sur la longueur
			orient++;
			enCours =Phase.SearchOrientation;
		}//else if()
		
	}
	private Point searchCross(Square[][] etatTirs) {
		if (lastHitCross ==null){
			lastHitCross.setLocation(0, 0);
			return new Point(0,0);
		}
		int taille = etatTirs.length;
		int newX = lastHitCross.x+2;
		int newY = lastHitCross.y;
		if(newX>=taille) {
			newX = newX-(taille-1)-(2*(newX/(taille+1)));
			newY++;
		}
		if(newY>=taille) {
			newY= 0;
			newX=1;
		}
		lastHitCross.setLocation(newX, newY);
		if(etatTirs[lastHitCross.x][lastHitCross.y] != Square.SEA) {// si on a déja tiré sur la case cible, on réitére
			return searchCross(etatTirs);
		}
		return new Point(newX,newY);
	}
	private Point searchLongueur(Square[][] etatTirs) {
		Point p = getPointOrient(lastHitLongueur);
		if(checkPointOutside(p,etatTirs.length) ) {
			enCours =Phase.SearchOrientation;
			return searchOrientation(etatTirs);
		}else if (etatTirs[p.x][p.y] != Square.SEA) {
			lastHitLongueur.setLocation(p);
			return searchLongueur(etatTirs);
		}else {
			return p;
		}

	}

	private Point searchOrientation(Square[][] etatTirs) {
		Point p = getPointOrient();
		if(!checkPointOutside(p,etatTirs.length) && etatTirs[p.x][p.y] == Square.SEA) {
			lastHitLongueur.setLocation(p);
			enCours =Phase.SearchLongueur;
			return p;
		}else if(orient == 3) { //toutes les orientations testées
			orient = 0;
			enCours =Phase.SearchCross;
			return searchCross(etatTirs);
		}else {
			orient ++;
			return searchOrientation(etatTirs);
		}
	}
	
	private Point getPointOrient() {
		return getPointOrient(lastHitCross);
	}
	private Point getPointOrient(Point p) {
		switch(ordreCherche[orient]) {
		case EAST:
			return new Point(p.x+1,p.y);
		case WEST:
			return new Point(p.x-1,p.y);
		case NORTH:
			return new Point(p.x,p.y-1);
		case SOUTH:
			return new Point(p.x,p.y+1);
		default:
			return new Point(-1,-1);
		}
	}
	
	private boolean checkPointOutside(Point p,int taille){
		return p.x<0 || p.y<0 || p.x >=taille || p.y>=taille;
	}

	public String toString(){
		return ("IACross");
	}

}
