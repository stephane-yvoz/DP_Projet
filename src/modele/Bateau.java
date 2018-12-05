package modele;

/**
 * La position d'un bateau correspond aux indices sur la grille.
 * Les indices de la grille sont dans l'ensemble {0,..,9}.
 */
public class Bateau {
	
	public final static int SUP = 10;
	
	public final static String ILLEGAL_POSITIONS = "Un bateau doit être en position horizontale ou verticale.";
	
	private int posOriginX; 
	private int posOriginY; 
	
	private int posTailX;
	private int posTailY;
	
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
	
	public int getTailX() {
		return posTailX;
	}
	
	public int getTailY() {
		return posTailY;
	}
	
	public int getMinX() {
		return Math.min(posOriginX, posTailX);
	}
	
	public int getMinY() {
		return Math.min(posOriginY, posTailY);
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
	
	/**
	 * @param posOX
	 * @param posOY
	 * @param posTX
	 * @param posTY
	 */
	public void setPosition(int posOX, int posOY, int posTX, int posTY) {
		if (!areValidPositions(posOX, posOY, posTX, posTY)) {
			throw new IllegalArgumentException(ILLEGAL_POSITIONS);
		}
		
		posOriginX = posOX;
		posOriginY = posOY;
		posTailX = posTX;
		posTailY = posTY;
		
		updateLength();
	}
	
	private void updateLength() {
		if (isHorizontal()) {
			longueur = Math.abs(posOriginX - posTailX) + 1;
		}
		else if (isVertical()) {
			longueur = Math.abs(posOriginY - posTailY) + 1;
		}
	}
	
	public static boolean areValidPositions(int posOX, int posOY, int posTX, int posTY) {
		assert posOX < SUP;
		assert posOY < SUP;
		assert posTX < SUP;
		assert posTY < SUP;
		
		return isVertical(posOX, posTX) || isHorizontal(posOY, posTY);
	}
	
	public static boolean isVertical(int posOX, int posTX) {
		return posOX == posTX;
	}
	
	public static boolean isHorizontal(int posOY, int posTY) {
		return posOY == posTY;
	}
	
	public void takeHit(int puissance) {
		assert puissance > 0;
		PV = PV - puissance;
	}
	
	public boolean isDestroyed() {
		return PV <= 0;
	}
	
	public boolean isHorizontal() {
		return posOriginY == posTailY;
	}
	
	public boolean isVertical() {
		return posOriginX == posTailX;
	}
	
}
