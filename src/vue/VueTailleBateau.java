package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.ListenerTailleBateau;

import modele.EtatPartie;
import modele.Modele;

@SuppressWarnings("serial")
public class VueTailleBateau extends JToolBar implements Observer{

	private JButton[] boutonsTaille;
	private int[] bateauxRestants;
	private int tailleMax;
	private JLabel text;
	
	public VueTailleBateau(Modele modele){
		super();
		modele.addObserver(this);
		bateauxRestants = modele.getCurrentPlayer().getBateauxRestants();
		tailleMax = modele.getCurrentPlayer().getTailleMax();
		boutonsTaille = new JButton[tailleMax];
		text = new JLabel("Selectionner un bateau à placer : ");
		this.add(text);
		for(int i = 0;i != tailleMax;i++){
				String s = "Bateau taille "+ (i+1) + " : "+bateauxRestants[i];
				boutonsTaille[i] = new JButton(s);
				boutonsTaille[i].addActionListener(new ListenerTailleBateau(modele, i + 1));
				if (bateauxRestants[i] == 0)
					boutonsTaille[i].setVisible(false);
				this.add(boutonsTaille[i]);
		}
		setFloatable( false);
	}

	@Override
	public void update(Observable o, Object arg) {
		Modele modele = (Modele) o;
		EtatPartie e = modele.getEtat();
		if (e == EtatPartie.Positioning)
			affichagePos(modele);
		if (e == EtatPartie.ShipSelection)
			text.setText("Selectionner un bateau");
		if (e == EtatPartie.ShipShoot)
			text.setText("Selectionner où tirer");
	}

	private void affichagePos(Modele modele) {
		bateauxRestants = modele.getCurrentPlayer().getBateauxRestants();
		for (int i = 0; i != tailleMax; i++){
			String s = "Bateau taille "+ (i+1) + " : "+bateauxRestants[i];
			boutonsTaille[i].setText(s);
			if (bateauxRestants[i] == 0)
				boutonsTaille[i].setVisible(false);
		}
	}

}
