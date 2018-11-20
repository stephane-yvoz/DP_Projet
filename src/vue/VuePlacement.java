package vue;

import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import bateauFactories.TextureFactory;

import modele.Modele;
import modele.Plateau;

public class VuePlacement extends VueGrille implements Observer{
	
	private Modele modele;
	private JMenuBar menu;
	
	public VuePlacement(Modele m){
		super(m);
		this.addMouseListener(new VuePlacement.LocalListener(modele));
		menu = new JMenuBar();
		JMenu menuTaille = new JMenu("taille du prochain bateau");
		JMenuItem jItem;
		for (int i = 1;i<5;i++){
			jItem = new JMenuItem(" "+i);
			jItem.addActionListener(null);  //actionListener qui va set la taille du prochain bateau, si stocks pas épuisé(grisé sinon)
			menuTaille.add(jItem);
		}
	}

	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		this.paintTempShip(g);
	}
	
	public void paintTempShip(Graphics g){
		
	}
	
	public Plateau getPlateau(){
		return null;
	}

	
	
	class LocalListener implements MouseListener {
		
		private static final int tailleFenetreX = 500;
		private static final int tailleFenetreY = 500;
		
		private int longueur = 2;  // Creer un menu permettant de selectionner une longueur pour le bateau à placer
		
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
			if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){ // on inore les clics sur bordure grille
				if(xCaseClic1<0){ // si on a pas encore cliqué
					xCaseClic1 = x/(tailleFenetreX/11);  
					yCaseClic1 = y/(tailleFenetreY/11);
				}else{
					int xCaseClic2= x/(tailleFenetreX/11);
					int yCaseClic2 = y/(tailleFenetreY/11);
					if(xCaseClic1 != xCaseClic2 || yCaseClic1 != yCaseClic2){  //on ne peut pas cliquer 2 fois sur même case
						if(modele.canAddShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2)){
							modele.addShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2);
						}
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
