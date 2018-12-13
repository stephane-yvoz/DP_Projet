package vue;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modele.Bateau;
import modele.Modele;
import modele.Orientation;
import modele.Plateau;

public class VuePlacement /*extends VueGrille implements Observer*/{
	
	/*private static final long serialVersionUID = 1L;
	//private Modele modele;
	private JMenuBar menu;
	private LocalListener listener;
	private int longueur = 3;
	
	
	public VuePlacement(Modele m){
		super(m);
		
		
		listener = new VuePlacement.LocalListener(modele,this);
		this.addMouseListener(listener);
		menu = new JMenuBar();
		JMenu menuTaille = new JMenu("taille du prochain bateau");
		JMenuItem jItem;
		for (int i = 1;i<5;i++){
			jItem = new JMenuItem(" "+i);
			jItem.addActionListener(new ControllerChoixTailleBateauPlacement(this,i));  //actionListener qui va set la taille du prochain bateau, si stocks pas épuisé(grisé sinon)
			menuTaille.add(jItem);
		}

		this.add(menu);
	}
	

	public void setLongueur(int l) {
		if(bateauxDisponible[l-1]>0){
			longueur = l;
		}else{
			JOptionPane.showMessageDialog(this, "Vous n'avez plus de bateaux de taille "+l+" disponibles");
		}
	}
	
	private int calculPosSourisX(){
		return MouseInfo.getPointerInfo().getLocation().x/11;
	}
	private int calculPosSourisY(){
		return MouseInfo.getPointerInfo().getLocation().y/11;
	}
	private Orientation CalculOrientation(int x1,int y1,int x2,int y2){
		Orientation o =null;
		if(x1<x2){
			o=Orientation.EAST;
		}else if(x1>x2){
			o=Orientation.WEST;
		}else if (y1<y2){
			o=Orientation.SOUTH;
		}else{
			o=Orientation.NORTH;
		}
		return o;
	}
	
	public int getLongueur() {
		return longueur;
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(listener.isOriginSet()){
			Bateau b =new Bateau(1, longueur, 1);
			b.setPosition(listener.getXCaseClic1(), listener.getYCaseClic1(),  // affichage du bateau en cours de positionnement
					CalculOrientation(listener.getXCaseClic1(),listener.getYCaseClic1(),calculPosSourisX(),calculPosSourisY()));
			
			//TODO ? : affichage rouge si modele.canAddShip(b) == false ?
			super.drawShip(g, b);
		}
	}
	
	
	public Plateau getPlateau(){
		return null;
	}

	
	
	class LocalListener implements MouseListener {
		
		private Modele modele;
		private VuePlacement vue;
		private int xCaseClic1 = -1;
		private int yCaseClic1 = -1;
		
		public LocalListener(Modele m,VuePlacement v) {
			modele = m;
			vue = v;
		}
		public int getXCaseClic1() {
			return xCaseClic1;
		}
		public int getYCaseClic1() {
			return yCaseClic1;
		}
		public boolean isOriginSet(){
			return xCaseClic1>=0;
		}
		

		@Override
		public void mouseClicked(MouseEvent e) {
			int tailleFenetreX = vue.getWidth();
		    int tailleFenetreY = vue.getHeight();
			if(e.getButton()== MouseEvent.BUTTON1){ //clic gauche
				int x = e.getX();
				int y = e.getY();
				if(x>(tailleFenetreX/11) &&x<tailleFenetreX && y>(tailleFenetreY/11) && y<tailleFenetreY){ // on ignore les clics sur bordure grille
					if(!isOriginSet()){ // si on a pas encore cliqué
						xCaseClic1 = x/(tailleFenetreX/11)-1;  
						yCaseClic1 = y/(tailleFenetreY/11)-1;
						System.out.println("Case1 :"+xCaseClic1+"  "+yCaseClic1 );
					}else{
						int xCaseClic2= x/(tailleFenetreX/11)-1;
						int yCaseClic2 = y/(tailleFenetreY/11)-1;
						System.out.println("Case2 :"+xCaseClic2+"  "+yCaseClic2 );
						if(xCaseClic1 != xCaseClic2 || yCaseClic1 != yCaseClic2){  //on ne peut pas cliquer 2 fois sur même case
							if(bateauxDisponible[longueur-1]>0 && modele.canAddShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2) ){
								System.out.println("ajout");
								modele.addShip(xCaseClic1, yCaseClic1, longueur, xCaseClic2, yCaseClic2);
								xCaseClic1 = -1;
								yCaseClic1 = -1;
								bateauxDisponible[longueur-1]--;
							}
							xCaseClic1 = -1;
							yCaseClic1 = -1;
						}
					}
				}
			}else{ // si pas bouton gauche on annule
				xCaseClic1 = -1;
				yCaseClic1 = -1;
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

*/

}
