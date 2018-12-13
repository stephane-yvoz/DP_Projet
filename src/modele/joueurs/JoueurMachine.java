package modele.joueurs;

import modele.Modele;
import modele.Option;
import modele.Plateau;

public class JoueurMachine extends Joueur{

	private Strategie strategie;
	
	public JoueurMachine(Option o){
		super(o);
		human = false;
	}
	
	@Override
	public void play(Modele m) {
		// TODO Auto-generated method stub
		//System.out.println(m.getJoueurs(0).getPlateau());
		m.nextPlayer();
		return ;
	}
	
	public void setStrategie(Strategie s){
		strategie =s;
	}

}
