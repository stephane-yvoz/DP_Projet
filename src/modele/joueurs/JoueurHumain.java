package modele.joueurs;

import modele.Option;
import modele.Plateau;

public class JoueurHumain extends Joueur {

	public JoueurHumain(Option o){
		plateau = new Plateau(o.getSize());
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

}
