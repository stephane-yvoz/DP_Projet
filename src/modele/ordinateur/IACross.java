package modele.ordinateur;

import java.awt.Point;

import modele.Orientation;
import modele.Square;
import modele.joueurs.Joueur;

public class IACross implements IAPlayer {
	
	private enum Phase  {SearchCross,SearchOrientation,SearchLongueur};
	
	private Phase enCours = Phase.SearchCross;
	private Point lastHitCross = null;
	private Point LastHitLongueur =null;
	
	private Orientation[] ordreCherche = {Orientation.EAST,Orientation.SOUTH,Orientation.NORTH,Orientation.WEST};
	private int orient = 0;
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
		}else if(enCours == Phase.SearchLongueur && etatTirs[LastHitLongueur.x][LastHitLongueur.y] == Square.MISSED){
			//si on a fini de frapper sur la longueur
			orient++;
			enCours =Phase.SearchOrientation;
		}//else if()
		
	}
	private Point searchCross(Square[][] etatTirs) {
		if (lastHitCross ==null){
			return new Point(0,0);
		}
		return null;
	}
	private Point searchLongueur(Square[][] etatTirs) {
		// TODO Auto-generated method stub
		return null;
	}

	private Point searchOrientation(Square[][] etatTirs) {
		// TODO Auto-generated method stub
		return null;
	}



}
