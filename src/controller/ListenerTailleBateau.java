package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

public class ListenerTailleBateau implements ActionListener {
	private Modele modele;
	private int taille;
	
	public ListenerTailleBateau(Modele m,int t){
		modele = m;
		taille = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
