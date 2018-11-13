import modele.Modele;
import modele.Option;
import vue.VueGrilleJoueur1;
import vue.VueGrilleJoueur2;
import vue.VueOption;

import javax.swing.*;

public class BatailleNavale {
    public static int NUMBER = 2;
    JFrame[] windows;

    private void makeGame(Modele modele){
        windows = new JFrame[NUMBER];
        String s;
        for (int i = 0; i != NUMBER; i++){
            if (i == 0)
                s = "Votre plateau";
            else
                s = "Plateau de tir";
            windows[i] = new JFrame(s);
            windows[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windows[i].setSize(500, 500);
            windows[i].setVisible(true);
        }
        windows[1].setVisible(false);
        VueGrilleJoueur1 vj1 = new VueGrilleJoueur1(modele);
        VueGrilleJoueur2 vj2 = new VueGrilleJoueur2(modele);
        windows[0].add(vj1);
        windows[1].add(vj2);
    }

    public BatailleNavale(){
        Option option = new Option();
        makeFrameOption(option);
        //Modele modele = new Modele();
        //makeGame();
    }

    private void makeFrameOption(Option option) {
        JFrame frame = new JFrame("Option");
        frame.add(new VueOption(option));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 200);
    }

    public static void main(String[] args) {
        BatailleNavale bn = new BatailleNavale();
    }
}
