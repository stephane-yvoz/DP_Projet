package modele.joueurs;

import modele.Modele;
import modele.Option;
import modele.ordinateur.Strategie;

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
		//strategie.jouer(this);
		m.nextPlayer();
	}
	
	public void setStrategie(Strategie s){
		strategie =s;
	}

}
