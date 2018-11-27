package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VuePlacement;

public class ControllerChoixTailleBateauPlacement implements ActionListener{

	private int taille;
	private VuePlacement vue;
	public ControllerChoixTailleBateauPlacement(VuePlacement v, int t) {
		vue=v;
		taille=t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		vue.setLongueur(taille);
	}
	
}
