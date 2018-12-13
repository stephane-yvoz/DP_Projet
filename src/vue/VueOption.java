package vue;

import controller.controllerOption.ControllerChoixEpoque;
import controller.controllerOption.ControllerDefault;
import controller.controllerOption.ControllerValidate;
import modele.Option;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueOption extends JPanel implements Observer {
    private Option option;
    private JPanel epoque;
    private JButton valider;
    private JButton defaultConf;
    JButton[] epoques;
    private final int NB = 2;

    private void makeEpoque(){
        epoques = new JButton[NB];
        JLabel text = new JLabel("Choisir une époque : ");
        epoque.add(text);
        for (int i = 0; i != NB; i++) {
            epoques[i] = new JButton();
            epoques[i].addActionListener(new ControllerChoixEpoque(option));
            epoques[i].setFocusPainted(false);
            this.epoque.add(epoques[i]);
        }
        epoques[0].setText("III");
        epoques[1].setText("autre");
    }

    public VueOption(Option opt){
        option = opt;
        this.epoque = new JPanel();
        this.setLayout(new BorderLayout());
        makeEpoque();
        makeValider();
        this.add(epoque, BorderLayout.NORTH);
        option.addObserver(this);
    }

    private void makeValider() {
        JPanel cadreValidation = new JPanel();
        valider = new JButton("Validate");
        valider.addActionListener(new ControllerValidate(option));
        defaultConf = new JButton("Defaut");
        defaultConf.addActionListener(new ControllerDefault(option));
        cadreValidation.add(valider);
        cadreValidation.add(defaultConf);
        this.add(cadreValidation, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!option.getDisplayOption()){
            Thread.currentThread().interrupt();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        }
        for (int i = 0; i != NB; i++) {
            if (epoques[i].getText().equals(option.getEpoque())){
                epoques[i].setEnabled(false);
            }
            else {
               epoques[i].setEnabled(true);
            }
        }
    }
}
