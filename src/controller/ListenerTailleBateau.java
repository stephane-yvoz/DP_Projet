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
<<<<<<< HEAD
		if (modele.getCurrentPlayer().getBateauxDisponibles()[taille-1]>0){
			modele.getCurrentPlayer().setTailleBateauActuel(taille);
		}
		else {}
=======
			modele.getCurrentPlayer().setTailleBateauActuel(taille);
			boolean ret = modele.getCurrentPlayer().diminuerBateauRestants(taille - 1);
			if (ret)
				modele.setEtat(EtatPartie.Positioning);
>>>>>>> 46473278cbb19ed48208858ffbea001d6b024a8f
	}
}
