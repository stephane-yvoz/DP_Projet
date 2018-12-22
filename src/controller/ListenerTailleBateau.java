package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.EtatPartie;
import modele.Modele;

public class ListenerTailleBateau implements ActionListener {
	
	private Modele modele;
	private int taille;
	
	public ListenerTailleBateau(Modele m, int t) {
		modele = m;
		taille = t;
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (modele.getEtat() == EtatPartie.Selectionning)
		    modele.setEtat(EtatPartie.Positioning);
		modele.getCurrentPlayer().setTailleBateauActuel(taille);
	}
}
