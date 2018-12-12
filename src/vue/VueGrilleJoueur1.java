package vue;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.ControllerClickGrille;


import modele.Modele;
import modele.Plateau;

public class VueGrilleJoueur1 extends VueGrille {

	private ControllerClickGrille listener;
	
    public VueGrilleJoueur1(Modele modele) {
        super(modele);
        listener = new ControllerClickGrille(modele,this,"Main");
		this.addMouseListener(listener);
    }
     
    @Override
	public Plateau getPlateau() {
		return modele.getPlateau1();
	}
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
    }

}
