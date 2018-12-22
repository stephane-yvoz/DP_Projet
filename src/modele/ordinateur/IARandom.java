package modele.ordinateur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;

import modele.Bateau;
import modele.Orientation;
import modele.Square;
import modele.joueurs.Joueur;

public class IARandom extends Strategie {

	public IARandom(){
		super();
	}

	@Override
	public Point jouer(Joueur player) {
		Random rand = new Random();
		Square[][] etatTirs= player.getPlateau().getGrilleEnnemie();
		Point ret = null;
		List<Point> casesLibres = new ArrayList<Point>();
		//Creation d'une liste de toutes les coordonnées sur lesquelles il n'y a pas eu de tir
		int i = 0;
		while (i != etatTirs.length && ret == null){
			for(int j=0; j<etatTirs[0].length;j++ ){
				if(etatTirs[i][j] == Square.HIT){
					ret = new Point(i, j);
				}
				if(etatTirs[i][j] == Square.SEA){
					casesLibres.add(new Point(i,j));
				}
			}
			i = i + 1;
		}
		if (ret == null)
			ret = casesLibres.get(rand.nextInt(casesLibres.size()));
		else{
			ret = findAdjacent(etatTirs, ret.x, ret.y, null);
		}
		return ret;
	}

	private Point findAdjacent(Square[][] etatTirs, int i, int j, Orientation o) {
		if (etatTirs[i][j] == Square.SEA){
			return new Point(i, j);
		}
		// on verifie les cases adjacente pour voir si on a pas déjà touché à coté. Si missed, on retourne en arriere.
		if (o == null || etatTirs[i][j] == Square.MISSED) {
			if (i > 0 && etatTirs[i - 1][j] == Square.HIT) {
				return (findAdjacent(etatTirs, i - 1, j, Orientation.NORTH));
			}
			if (i < 9 && etatTirs[i + 1][j] == Square.HIT) {
				return (findAdjacent(etatTirs, i + 1, j, Orientation.SOUTH));
			}
			if (j < 9 && etatTirs[i][j + 1] == Square.HIT) {
				return (findAdjacent(etatTirs, i, j + 1, Orientation.EAST));
			}
			if (j > 0 &&  etatTirs[i][j - 1] == Square.HIT) {
				return (findAdjacent(etatTirs, i, j - 1, Orientation.WEST));
			}
		}
		if (o != null){ // quand on a trouvé une direction vers où aller
			if (i > 0 && o == Orientation.NORTH)
				return (findAdjacent(etatTirs, i - 1, j, Orientation.NORTH));
			if (i < 9 && o == Orientation.SOUTH)
				return (findAdjacent(etatTirs, i + 1, j, Orientation.SOUTH));
			if (j < 9 && o == Orientation.EAST)
				return (findAdjacent(etatTirs, i, j + 1, Orientation.EAST));
			if (o == Orientation.WEST && j > 0)
				return (findAdjacent(etatTirs, i, j - 1, Orientation.WEST));
		}
		// Sinon, on selectionne une case adjacente vide
		if (i > 0 && etatTirs[i - 1][j] == Square.SEA) {
			return new Point(i - 1, j);
		}
		if (i < 9 && etatTirs[i + 1][j] == Square.SEA) {
			return new Point(i + 1, j);
		}
		if (j > 0 &&  etatTirs[i][j - 1] == Square.SEA) {
			return new Point(i, j - 1);
		}
		if (j < 9 && etatTirs[i][j + 1] == Square.SEA) {
			return new Point(i, j + 1);
		}
		return null;
	}

	public String toString(){
		return ("IARandom");
	}
}
