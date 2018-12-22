package modele.joueurs;

import modele.Modele;
import modele.Option;
import modele.ordinateur.Strategie;

import java.awt.*;

public class JoueurMachine extends Joueur{

	private Strategie strategie;
	
	public JoueurMachine(Option o){
		super(o);
		human = false;
		strategie = o.getStrategie();
		strategie.placerBateau(this, o.getBateauxDisponibles());
	}
	
	@Override
	public void play(Modele m) {
		// TODO Auto-generated method stub
		Point p = strategie.jouer(this);
		m.shoot(m.getJoueurs(0), p.x, p.y);
	}
	
	public void setStrategie(Strategie s){
		strategie =s;
	}

}
