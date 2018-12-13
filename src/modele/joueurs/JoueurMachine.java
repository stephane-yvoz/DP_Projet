package modele.joueurs;

import modele.Option;
import modele.Plateau;

public class JoueurMachine extends Joueur{

	private Strategie strategie;
	
	public JoueurMachine(Option o){
		super(o);
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public void setStrategie(Strategie s){
		strategie =s;
	}

}
