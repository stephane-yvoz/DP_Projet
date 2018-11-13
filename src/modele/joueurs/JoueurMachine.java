package modele.joueurs;

public class JoueurMachine extends Joueur{

	private Strategie strategie;
	
	public JoueurMachine(){
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public void setStrategie(Strategie s){
		strategie =s;
	}

}
