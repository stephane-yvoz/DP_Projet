package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.EtatPartie;
import modele.Modele;
import modele.joueurs.Joueur;
import vue.VueGrille;

public class ControllerClickGrille implements MouseListener{
	private Modele modele;
	private VueGrille vue;
	int tailleFenetreX = 400;
    int tailleFenetreY = 400;
	private int longueur = 3;
	private int xCaseClic = -1;
	private int yCaseClic = -1;
	int xCaseClic2;
	int yCaseClic2;
	private String typeVue ="";
	
	public ControllerClickGrille(Modele m,VueGrille v, String type) {
		modele = m;
		vue = v;
		typeVue=type;
	}
	public int getXCaseClic() {
		return xCaseClic;
	}
	public int getYCaseClic() {
		return yCaseClic;
	}
	public boolean isCaseSet(){
		return xCaseClic>=0;
	}
	public void setLongueur(int l){
		longueur = l;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		tailleFenetreX = vue.getWidth();
	    tailleFenetreY = vue.getHeight();
		switch(modele.getEtat()){
		case Positioning :
			if(typeVue == "Main"){
				placerBateau(e);
			}
			break;
		case ShipSelection :
			if(typeVue == "ViewShots"){
				selection(e);
			}
			break;
		case ShipShoot :
			if(typeVue == "ViewShots"){
				tirer(e);
			}
			break;
		}
		
	}
	private void selection(MouseEvent e) {
		Joueur j = modele.getCurrentPlayer();
		if(e.getButton()== MouseEvent.BUTTON1){ //clic gauche
			int x = e.getX();
			int y = e.getY();
			if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){ // on ignore les clics sur bordure grille
				xCaseClic = getNumCase(x);  
				yCaseClic = getNumCase(y);
				if(modele.getCurrentPlayer().hasShip(xCaseClic,yCaseClic)){
					modele.getCurrentPlayer().setSelectedShip(xCaseClic,yCaseClic);
					xCaseClic = -1;
					yCaseClic = -1;
					modele.setEtat(EtatPartie.ShipShoot);
				}
			}
		}
	}
	
	
	private void tirer(MouseEvent e){
		Joueur j = modele.getJoueurs(0);
		if (j == null || !j.isPlayerTurn())
			return ;
		if(e.getButton()== MouseEvent.BUTTON1){ //clic gauche
			int x = e.getX();
			int y = e.getY();
			if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){ // on ignore les clics sur bordure grille
				xCaseClic = getNumCase(x);  
				yCaseClic = getNumCase(y);
				modele.shoot(modele.getJoueurs(1), yCaseClic, xCaseClic);
				xCaseClic = -1;
				yCaseClic = -1;
			}
		}
	}
	
	public void placerBateau(MouseEvent e){
		if(e.getButton()== MouseEvent.BUTTON1){ //clic gauche
			int x = e.getX();
			int y = e.getY();
			if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){ // on ignore les clics sur bordure grille
				if(!isCaseSet()){ // si on a pas encore cliqué
					xCaseClic = getNumCase(x);  
					yCaseClic = getNumCase(y);
					System.out.println("Case1 :"+xCaseClic+"  "+yCaseClic );
				}else{
					xCaseClic2= getNumCase(x);
					yCaseClic2 = getNumCase(y);
					System.out.println("Case2 :"+xCaseClic2+"  "+yCaseClic2 );
					if(xCaseClic != xCaseClic2 || yCaseClic != yCaseClic2){  //on ne peut pas cliquer 2 fois sur même case
						if(modele.getCurrentPlayer().getBateauxDisponibles()[longueur-1]>0 && modele.canAddShip(xCaseClic, yCaseClic, longueur, xCaseClic2, yCaseClic2) ){
							System.out.println("ajout");
							modele.addShip(xCaseClic, yCaseClic, longueur, xCaseClic2, yCaseClic2);
							xCaseClic = -1;
							yCaseClic = -1;
							modele.getCurrentPlayer().getBateauxDisponibles()[longueur-1]--;
						}
						xCaseClic = -1;
						yCaseClic = -1;
					}
				}
			}
		}else{ // si pas bouton gauche on annule
			xCaseClic = -1;
			yCaseClic = -1;
		}
	}
	
	private int getNumCase(int x){
		int temp = Math.min(x/(tailleFenetreX/11)-1,10);
		return temp;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}


