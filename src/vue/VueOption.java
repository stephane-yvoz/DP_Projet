package vue;

import controller.ControllerChoixEpoque;
import modele.Option;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueOption extends JPanel implements Observer {
    private Option option;
    private JPanel epoque;
    JButton[] epoques;
    private final int NB = 2;

    private void makeEpoque(){
        epoques = new JButton[NB];
        JLabel text = new JLabel("Choisir une époque : ");
        epoque.add(text);
        for (int i = 0; i != NB; i++) {
            epoques[i] = new JButton();
            epoques[i].addActionListener(new ControllerChoixEpoque(option));
            this.epoque.add(epoques[i]);
        }
        epoques[0].setText("III");
        epoques[1].setText("null");
    }

    public VueOption(Option opt){
        option = opt;
        this.epoque = new JPanel();
        this.setLayout(new BorderLayout());
        makeEpoque();
        this.add(epoque, BorderLayout.NORTH);
        option.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        for (int i = 0; i != NB; i++) {
            if (epoques[i].getText().equals(option.getEpoque())){
                epoques[i].setBorder(new LineBorder(Color.RED));
            }
            else {
               epoques[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
}
