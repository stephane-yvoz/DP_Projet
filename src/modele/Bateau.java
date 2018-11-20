package modele;

/**
 * La position d'un bateau correspond aux indices sur la grille.
 * Les indices de la grille sont dans l'ensemble {0,..,9}.
 * 
 * @author gen
 */
public class Bateau {
	
	public final static int SUP = 10;
	
	private int posOriginX; 
	private int posOriginY; 
	private Orientation orientation; // orientation du "reste" du bateau à partir du point d'origine
	
	private int PV;
	private int longueur;	
	private int puissance;
	
	
	public Bateau(int vie, int lon, int pu) {
		assert vie > 0;
		assert longueur > 0;
		assert puissance > 0;
		
		PV = vie;
		longueur = lon;
		puissance = pu;
	}

	public int getOriginX() {
		return posOriginX;
	}
	
	public int getOriginY() {
		return posOriginY;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public int getPuissance() {
		return puissance;
	}
	
	public void setPosition(int posX, int posY, Orientation o) {
		assert posX < SUP;
		assert posY < SUP;
		
		orientation = o;
		posOriginX = posX;
		posOriginY = posY;
	}
	
	public void takeHit(int puissance) {
		assert puissance > 0;
		PV = PV - puissance;
	}
	
	public boolean isDestroyed() {
		return PV <= 0;
	}
	
}
