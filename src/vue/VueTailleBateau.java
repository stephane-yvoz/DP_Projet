package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ListenerTailleBateau;

import modele.EtatPartie;
import modele.Modele;

public class VueTailleBateau extends JPanel implements Observer{
	
	private Modele modele;
	private JButton[] boutonsTaille;
	
	public VueTailleBateau(Modele m){
		modele = m;
		modele.addObserver(this);
		for(int i=1;i<=5;i++){
			boutonsTaille[i-1] = new JButton(Integer.toString(i));
			boutonsTaille[i-1].addActionListener(new ListenerTailleBateau(modele,i));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(modele.getEtat() == EtatPartie.Positioning){
			this.setVisible(true);
		}else{
			this.setVisible(false);
		}
		
	}
	

}
