package modele;

public class Bateau {
	
	private int PV;
	private int longueur;
	private Orientation orientation; //orientation du "reste" du bateau à partir du point d'origine
	private int posOriginX;
	private int posOriginY;
	private int puissance;
	
	public Bateau(int vie,int lon , int pu){
		PV= vie;
		longueur = lon;
		puissance = pu;
	}

	public void setPosition(int posX ,int posY ,Orientation o ){
		orientation = o;
		posOriginX=posX;
		posOriginY = posY;
	}
	
	public void takehit(int puissance){
		PV = PV -puissance;
	}
	
	public boolean isDestroyed(){
		return PV <= 0;
	}
	
	
}
