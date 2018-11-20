package modele;

public class Bateau {
	
	private int posOriginX; /* {0,..,10} */
	private int posOriginY; /* {0,..,10} */
	private Orientation orientation; // orientation du "reste" du bateau à partir du point d'origine
	
	private int PV;
	private int longueur;	
	private int puissance;
	
	
	public Bateau(int vie, int lon, int pu) {
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
	
	public void setPosition(int posX, int posY, Orientation o) {
		orientation = o;
		posOriginX = posX;
		posOriginY = posY;
	}
	
	public void takeHit(int puissance) {
		PV = PV - puissance;
	}
	
	public boolean isDestroyed() {
		return PV <= 0;
	}
	
}
