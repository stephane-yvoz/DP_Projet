package vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bateauFactories.TextureFactory;

import modele.Modele;

public class VuePlacement extends JPanel implements Observer{
	
	private Modele modele;
	
	public VuePlacement(Modele m){
		modele=m;
		modele.addObserver(this);
		this.addMouseListener(new VuePlacement.LocalListener(modele));
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	class LocalListener implements MouseListener {
		
		private static final int tailleFenetreX = 500;
		private static final int tailleFenetreY = 500;
		
		private int longueur = 2;  // Creer un menu permettant de selectionner unr longueur
		
		private Modele modele;
		private int xCaseClic1 = -1;
		private int yCaseClic1 = -1;
		
		public LocalListener(Modele m) {
			modele = m;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){
				if(xCaseClic1<0){ // si on a pas encore cliqué
					xCaseClic1 = x/(tailleFenetreX/11);  
					yCaseClic1 = y/(tailleFenetreY/11);
				}else{
					int xCaseClic2= x/(tailleFenetreX/11);
					int yCaseClic2 = y/(tailleFenetreY/11);
					if(modele.canAddShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2)){
						modele.addShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2);
					}
				}
			}
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
	
}
