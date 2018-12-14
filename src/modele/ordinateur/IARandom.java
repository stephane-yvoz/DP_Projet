package modele.ordinateur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;
import modele.Square;
import modele.joueurs.Joueur;

public class IARandom implements Strategie {

	@Override
	public Point jouer(Joueur player) {
		Square[][] etatTirs= player.getPlateau().getGrilleEnnemie();
		
		List<Point> casesLibres = new ArrayList<Point>();
		//Creation d'une liste de toutes les coordonnées sur lesquelles il n'y a pas eu de tir
		for(int i=0;i<etatTirs.length;i++){
			for(int j=0; j<etatTirs[0].length;j++ ){
				if(etatTirs[i][j].equals(Square.SEA)){
					casesLibres.add(new Point(i,j));
				}
			}		
		}

		
		return casesLibres.get((int)Math.random()*casesLibres.size());
	}

	@Override
	public void placerBateau(Square[][] grilleJoueur) {
		;
	}

}
